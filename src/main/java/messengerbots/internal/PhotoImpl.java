package messengerbots.internal;

import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoSizes;

import messengerbots.api.BotAPI;
import messengerbots.api.PlatformAPI;
import messengerbots.api.media.IPhoto;

abstract class PhotoImpl extends MediaImpl implements IPhoto {
	
	protected PhotoImpl(final BotAPI api) {
		super(api);
	}
	
	protected static IPhoto of(final BotAPI api, final PhotoSetImpl.Vk owner, final PhotoSizes size) {
		return new Vk(api, owner, size);
	}
	
	protected static IPhoto of(final BotAPI api, final PhotoSize size) {
		return new Tg(api, size);
	}
	
	public static class Local extends PhotoImpl implements MediaImpl.Local {
		
		private final java.io.File file;
		private final int width;
		private final int height;
		private @MonotonicNonNull String vkString;
		
		private final Object lockVk = new Object();
		
		protected Local(final BotAPI api, final java.io.File file) {
			super(api);
			this.file = file;
			try {
				final Dimension dimension = Utils.imageDimension(file);
				this.width = dimension.width;
				this.height = dimension.height;
			} catch (IOException e) {
				throw ExceptionUtil.invalidFile("image", e);
			}
		}

		@Override
		public int width() {
			return this.width;
		}

		@Override
		public int height() {
			return this.height;
		}

		@Override
		public java.io.File file() {
			return this.file;
		}
		
		@Override
		public String toVkString() {
			synchronized (this.lockVk) {
				if (this.vkString == null) {
					final PlatformAPI.VkAPI api = this.api().requireVk();
					final Photo photo = ((VkAPIImpl) api).uploadPhoto(this.file());
					this.vkString = Utils.vkPhotoToString(photo);
				}
				
				return this.vkString;
			}
		}
	}
	
	public static class Vk extends PhotoImpl implements MediaImpl.External, VkLikeMedia {
		
		private final PhotoSetImpl.Vk owner;
		private final PhotoSizes size;
		
		private Vk(final BotAPI api, final PhotoSetImpl.Vk owner, final PhotoSizes size) {
			super(api);
			this.owner = owner;
			this.size = size;
		}
		
		@Override
		public int width() {
			return this.size.getWidth();
		}
		
		@Override
		public int height() {
			return this.size.getHeight();
		}
		
		@Override
		public URL url() {
			return Utils.toURL(this.size.getUrl());
		}
		
		@Override
		public String toVkString() {
			return this.owner.toVkString();
		}
	}
	
	public static class Tg extends PhotoImpl implements MediaImpl.External {
		
		private final PhotoSize size;
		
		private URL url;
		private long urlNextRequest;
		private String fileExtension;
		private @MonotonicNonNull String vkString;
		
		private final Object lockUrl = new Object();
		private final Object lockVk = new Object();
		
		private Tg(final BotAPI api, final PhotoSize size) {
			super(api);
			this.size = size;
		}
		
		@Override
		public int width() {
			return this.size.getWidth();
		}
		
		@Override
		public int height() {
			return this.size.getHeight();
		}
		
		@Override
		public URL url() {
			synchronized (this.lockUrl) {
				if (this.url == null || System.currentTimeMillis() >= this.urlNextRequest) {
					try {
						final PlatformAPI.TgAPI tg = this.api().requireTg();
						final File file = tg.client().execute(GetFile.builder().fileId(this.size.getFileId()).build());
						this.url = new URL(file.getFileUrl(tg.token()));
						this.urlNextRequest = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1);
						this.fileExtension = FilenameUtils.getExtension(file.getFilePath());
					} catch (TelegramApiException e) {
						throw ExceptionUtil.tgExecute(e);
					} catch (MalformedURLException e) {
						throw ExceptionUtil.url(e);
					}
				}
				
				return this.url;
			}
		}

		@Override
		public String toVkString() {
			synchronized (this.lockVk) {
				if (this.vkString == null) {
					try {
						final URL url = this.url();
						final java.io.File file = java.io.File.createTempFile("photo-", "." + this.fileExtension).getAbsoluteFile();
						FileUtils.copyURLToFile(url, file);
						final PlatformAPI.VkAPI vkApi = this.api().requireVk();
						final Photo photo = ((VkAPIImpl) vkApi).uploadPhoto(file);
						file.delete();
						this.vkString = Utils.vkPhotoToString(photo);
					} catch (IOException e) {
						throw ExceptionUtil.of(e);
					}
				}
				
				return this.vkString;
			}
			
		}
		
		@Override
		public String toTgString() {
			return this.size.getFileId();
		}
	}
}
