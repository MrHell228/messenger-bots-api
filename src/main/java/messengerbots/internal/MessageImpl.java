package messengerbots.internal;

import java.util.List;

import messengerbots.api.BotAPI;
import messengerbots.api.IChat;
import messengerbots.api.IMessage;
import messengerbots.api.IMessageTemplate;
import messengerbots.api.Platform;
import messengerbots.api.media.IMedia;

abstract class MessageImpl implements IMessage {
	
	private final IChat chat;
	private final IMessageTemplate template;
	
	protected MessageImpl(final IChat chat, final IMessageTemplate template) {
		this.chat = chat;
		this.template = template;
	}
	
	@Override
	public IChat chat() {
		return this.chat;
	}
	
	@Override
	public IMessageTemplate template() {
		return this.template;
	}
	
	public static class Vk extends MessageImpl {
		
		private final com.vk.api.sdk.objects.messages.Message internal;

		public Vk(
			final BotAPI api,
			final com.vk.api.sdk.objects.messages.Message message
		) {
			super(api.chat(Platform.VK, message.getPeerId()), toAPI(api, message));
			this.internal = message;
		}
		
		private static IMessageTemplate toAPI(
			final BotAPI api,
			final com.vk.api.sdk.objects.messages.Message message
		) {
			return IMessageTemplate.builder()
					.text(message.getText())
					.media(message.getAttachments().stream()
							.map(attachment -> MediaImpl.of(api, attachment))
							.filter(media -> media != null)
							::iterator)
					.build();
		}
		
		public com.vk.api.sdk.objects.messages.Message internal() {
			return this.internal;
		}
	}
	
	public static class Tg extends MessageImpl {
		
		private final List<org.telegram.telegrambots.meta.api.objects.message.Message> internal;

		public Tg(
			final BotAPI api,
			final org.telegram.telegrambots.meta.api.objects.message.Message message
		) {
			super(api.chat(Platform.TG, message.getChatId()), toAPI(api, message));
			this.internal = List.of(message);
		}
		
		public Tg(
			final BotAPI api,
			final List<org.telegram.telegrambots.meta.api.objects.message.Message> messages
		) {
			super(api.chat(Platform.TG, messages.get(0).getChatId()), toAPI(api, messages));
			this.internal = List.copyOf(messages);
		}
		
		private static IMessageTemplate toAPI(
			final BotAPI api,
			final org.telegram.telegrambots.meta.api.objects.message.Message message
		) {
			final Builder builder = IMessageTemplate.builder()
					.text(extractText(message));
			
			final IMedia media = MediaImpl.of(api, message);
			if (media != null) {
				builder.media(media);
			}
			
			return builder.build();
		}
		
		private static IMessageTemplate toAPI(
			final BotAPI api,
			final List<org.telegram.telegrambots.meta.api.objects.message.Message> messages
		) {
			final org.telegram.telegrambots.meta.api.objects.message.Message first = messages.get(0);
			return IMessageTemplate.builder()
					.text(extractText(first))
					.media(messages.stream()
							.map(message -> MediaImpl.of(api, message))
							.filter(media -> media != null)
							::iterator)
					.build();
		}
		
		private static String extractText(final org.telegram.telegrambots.meta.api.objects.message.Message message) {
			return message.getText() != null ? message.getText() : message.getCaption() != null ? message.getCaption() : "";
		}
		
		public List<org.telegram.telegrambots.meta.api.objects.message.Message> internal() {
			return this.internal;
		}
	}
}
