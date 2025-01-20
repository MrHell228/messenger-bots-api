package messengerbots.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalLong;
import java.util.Random;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChat;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import messengerbots.api.BotAPI;
import messengerbots.api.IChat;
import messengerbots.api.IMessageTemplate;
import messengerbots.api.Platform;
import messengerbots.api.PlatformAPI.TgAPI;
import messengerbots.api.PlatformAPI.VkAPI;
import messengerbots.api.media.IMedia;
import messengerbots.api.media.IPhoto;
import messengerbots.api.media.MediaType;

public abstract class ChatImpl implements IChat {
	
	protected final BotAPI api;
	
	protected ChatImpl(final BotAPI api) {
		this.api = Objects.requireNonNull(api, "api");
	}
	
	@Override
	public BotAPI api() {
		return this.api;
	}
	
	public static class Vk extends ChatImpl {
		
		private final VkAPI vk;
		private final long id;
		
		public Vk(final BotAPI api, final long id) {
			super(api);
			this.vk = api.requireVk();
			this.id = id;
		}
		
		@Override
		public Platform platform() {
			return Platform.VK;
		}

		@Override
		public boolean isPrivate() {
			return this.id > 0 && this.id <= 2000000000;
		}
		
		@Override
		public String id() {
			return Long.toString(this.id);
		}
		
		@Override
		public OptionalLong longId() {
			return OptionalLong.of(this.id);
		}
		
		@Override
		public void send(final IMessageTemplate message) {
			try {
				this.vk.client().messages()
					.sendDeprecated()
					.randomId(new Random().nextInt())
					.peerId(this.id)
					.message(message.text())
					.attachment(String.join(",", message.media().stream().map(this::toVkString).toList()))
					.execute();
			} catch (ApiException | ClientException e) {
				throw ExceptionUtil.vkExecute(e);
			}
		}
		
		private String toVkString(final IMedia media) {
			if (media instanceof VkLikeMedia) {
				return ((VkLikeMedia) media).toVkString();
			} else if (media instanceof MediaImpl.External) {
				// TODO
				return null;
			} else if (media instanceof MediaImpl.Local) {
				// TODO
				return null;
			} else {
				throw ExceptionUtil.wrongMedia(media);
			}
		}
	}
	
	public static class Tg extends ChatImpl {
		
		private final TgAPI tg;
		private final String id;
		private final OptionalLong longId;
		
		public Tg(final BotAPI api, final String id) {
			super(api);
			this.tg = api.requireTg();
			this.id = id;
			
			OptionalLong longId;
			try {
				longId = OptionalLong.of(Long.valueOf(id));
			} catch (NumberFormatException e) {
				longId = OptionalLong.empty();
			}
			this.longId = longId;
		}

		@Override
		public Platform platform() {
			return Platform.TG;
		}

		@Override
		public boolean isPrivate() {
			return this.chat().getType().equals("private");
		}

		@Override
		public String id() {
			return this.id;
		}

		@Override
		public OptionalLong longId() {
			return this.longId;
		}

		@Override
		public void send(final IMessageTemplate message) {
			try {
				final TelegramClient client = this.tg.client();
				final List<IMedia> medias = message.media();
				if (medias.isEmpty()) {
					client.execute(SendMessage.builder()
							.chatId(this.id)
							.text(message.text())
							.build());
				} else if (medias.size() == 1) {
					final IMedia media = medias.get(0);
					if (media.type() == MediaType.PHOTO) {
						client.execute(SendPhoto.builder()
								.chatId(this.id)
								.photo(toInputFile(media))
								.caption(message.text())
								.build());
					} else {
						// TODO
					}
				} else {
					// TODO logic to split medias into valid groups (e.g. separate audio, group size < 10)
					
					 final SendMediaGroup.SendMediaGroupBuilder<?, ?> builder = SendMediaGroup.builder()
							.chatId(this.id);
					 
					 final List<InputMedia> inputs = new ArrayList<>();
					 inputs.add(toInputMedia(medias.get(0)).caption(message.text()).build());
					 medias.subList(1, medias.size()).forEach(media -> inputs.add(toInputMedia(media).build()));
					 builder.medias(inputs);
					 
					 client.execute(builder.build());
				}
			} catch (TelegramApiException e) {
				throw ExceptionUtil.tgExecute(e);
			}
		}
		
		private @MonotonicNonNull Chat chat;
		private Chat chat() {
			if (this.chat == null) {
				try {
					this.chat = this.tg.client().execute(GetChat.builder()
							.chatId(this.id)
							.build());
				} catch (TelegramApiException e) {
					throw ExceptionUtil.tgExecute(e);
				}
			}
			return this.chat;
		}
		
		private static InputFile toInputFile(final IMedia media) {
			if (media instanceof MediaImpl.TgLike) {
				return new InputFile(((MediaImpl.TgLike) media).toTgString());
			} else if (media instanceof MediaImpl.Local) {
				final File file = ((MediaImpl.Local) media).file();
				return new InputFile(file);
			} else {
				throw ExceptionUtil.wrongMedia(media);
			}
		}
		
		private static InputMedia.InputMediaBuilder<?, ?> toInputMedia(final IMedia media) {
			final InputMedia.InputMediaBuilder<?, ?> builder;
			if (media instanceof IPhoto) {
				builder = InputMediaPhoto.builder();
			} else {
				// TODO;
				builder = null;
			}
			
			if (media instanceof MediaImpl.TgLike) {
				return builder.media(((MediaImpl.TgLike) media).toTgString());
			} else if (media instanceof MediaImpl.Local) {
				final File file = ((MediaImpl.Local) media).file();
				return builder.media(file, file.getName());
			} else {
				throw ExceptionUtil.wrongMedia(media);
			}
		}
	}
}
