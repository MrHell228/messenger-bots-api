package messengerbots.internal;

import java.util.concurrent.Executor;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import messengerbots.api.PlatformAPI.TgAPI;

class TgAPIImpl implements TgAPI {
	
	private final Executor executor;
	private final TelegramBotsLongPollingApplication api;
	private final TelegramClient client;
	private final String token;
	
	public TgAPIImpl(
		final Executor executor,
		final TelegramBotsLongPollingApplication api,
		final TelegramClient client,
		final String token
	) {
		this.executor = executor;
		this.api = api;
		this.client = client;
		this.token = token;
	}
	
	@Override
	public Executor executor() {
		return this.executor;
	}
	
	@Override
	public TelegramBotsLongPollingApplication api() {
		return this.api;
	}
	
	@Override
	public TelegramClient client() {
		return this.client;
	}
	
	@Override
	public String token() {
		return this.token;
	}
	
	@Override
	public void close() throws Exception {
		this.api().close();
	}
}
