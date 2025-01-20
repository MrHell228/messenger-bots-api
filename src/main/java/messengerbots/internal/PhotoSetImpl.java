package messengerbots.internal;

import java.net.URL;
import java.util.List;

import messengerbots.api.BotAPI;
import messengerbots.api.media.IPhoto;
import messengerbots.api.media.IPhotoSet;

abstract class PhotoSetImpl extends PhotoImpl implements IPhotoSet, MediaImpl.External {
	
	protected PhotoSetImpl(final BotAPI api) {
		super(api);
	}
	
	@Override
	public IPhoto max() {
		return this.sizes().get(this.sizes().size() - 1);
	}
	
	@Override
	public IPhoto min() {
		return this.sizes().get(0);
	}
	
	@Override
	public URL url() {
		return ((MediaImpl.External) this.max()).url();
	}
	
	protected static Vk of(final BotAPI api, final com.vk.api.sdk.objects.photos.Photo photo) {
		return new Vk(api, photo);
	}
	
	public static class Vk extends PhotoSetImpl {
		
		private final com.vk.api.sdk.objects.photos.Photo photo;
		private final List<IPhoto> sizes;
		
		protected Vk(final BotAPI api, final com.vk.api.sdk.objects.photos.Photo photo) {
			super(api);
			this.photo = photo;
			this.sizes = photo.getSizes().stream().map(size -> PhotoImpl.of(api, this, size)).toList();
		}
		
		@Override
		public List<IPhoto> sizes() {
			return this.sizes;
		}
		
		@Override
		public String toVkString() {
			return Utils.vkPhotoToString(this.photo);
		}
	}
	
	public static class Tg extends PhotoSetImpl implements TgLike {
		
		private final List<IPhoto> sizes;
		
		protected Tg(final BotAPI api, final List<org.telegram.telegrambots.meta.api.objects.PhotoSize> photo) {
			super(api);
			this.sizes = photo.stream().map(size -> PhotoImpl.of(api, size)).toList();
		}
		
		@Override
		public List<IPhoto> sizes() {
			return this.sizes;
		}
		
		@Override
		public String toVkString() {
			return ((PhotoImpl.Tg) this.max()).toVkString();
		}
		
		@Override
		public String toTgString() {
			return ((PhotoImpl.Tg) this.max()).toTgString();
		}
	}
}
