package messengerbots.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import messengerbots.api.BotAPI;
import messengerbots.api.IBot;
import messengerbots.api.Platform;
import messengerbots.api.PlatformHandler;
import messengerbots.intermediate.tg.TelegramSingleUpdateHandler;

class TgHandler implements PlatformHandler, LongPollingUpdateConsumer {
	
	private final Executor executor;
	private final BotAPI api;
	private final IBot bot;
	private final TelegramSingleUpdateHandler singleUpdateHandler;
	
	public TgHandler(final BotAPI api, final IBot bot) {
		this.executor = api.requireTg().executor();
		this.api = api;
		this.bot = bot;
		this.singleUpdateHandler = new SingleUpdateHandler();
	}
	
	@Override
	public Platform platform() {
		return Platform.TG;
	}
	
	@Override
	public BotAPI api() {
		return this.api;
	}
	
	@Override
	public IBot bot() {
		return this.bot;
	}
	
	@Override
	public void consume(List<Update> updates) {
		Map<String, List<Message>> mediaGroups = new HashMap<>();
		for (final Iterator<Update> iter = updates.iterator(); iter.hasNext();) {
			final Update update = iter.next();
			if (!update.hasMessage()) {
				continue;
			}
			
			final Message message = update.getMessage();
			if (message.getMediaGroupId() == null) {
				continue;
			}
			
			iter.remove();
			mediaGroups.compute(message.getMediaGroupId(), (id, content) -> {
				if (content == null) {
					content = new ArrayList<>();
				}
				content.add(message);
				return content;
			});
		}
		
		updates.forEach(update -> this.executor.execute(() -> this.singleUpdateHandler.handle(update)));
		mediaGroups.forEach((id, content) -> {
			this.executor.execute(() -> this.bot.onMessage(new MessageImpl.Tg(this.api, content)));
		});
	}
	
	private class SingleUpdateHandler implements TelegramSingleUpdateHandler {
		@Override
		public void onMessage(final Message message) {
			TgHandler.this.bot().onMessage(new MessageImpl.Tg(TgHandler.this.api(), message));
		}
	}
}
