package messengerbots.internal;

import java.util.concurrent.Executor;

import com.vk.api.sdk.events.longpoll.GroupLongPollApi;
import com.vk.api.sdk.objects.callback.MessageNew;

import messengerbots.api.BotAPI;
import messengerbots.api.IBot;
import messengerbots.api.Platform;
import messengerbots.api.PlatformHandler;

class VkHandler extends GroupLongPollApi implements PlatformHandler {
	
	private final Executor executor;
	private final BotAPI api;
	private final IBot bot;
	
	public VkHandler(final BotAPI api, final IBot bot) {
		super(api.requireVk().client().client(), api.requireVk().actor(), api.requireVk().waitTime());
		this.executor = api.requireVk().executor();
		this.api = api;
		this.bot = bot;
	}
	
	@Override
	public Platform platform() {
		return Platform.VK;
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
	public void messageNew(final Integer groupId, final MessageNew message) {
		this.executor.execute(() -> this.bot().onMessage(new MessageImpl.Vk(this.api(), message.getObject().getMessage())));
	}
}
