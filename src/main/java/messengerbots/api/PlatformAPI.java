package messengerbots.api;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.Supplier;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

import messengerbots.intermediate.vk.VkAPIClient;

public interface PlatformAPI extends PlatformSource, AutoCloseable {
	
	Executor executor();
	
	interface Builder<E extends PlatformAPI, B extends Builder<E, B>> extends IBuilder<E, B> {
		
		default B executor(final Executor executor) {
			Objects.requireNonNull(executor, "executor");
			return this.executor(() -> executor);
		}
		
		B executor(Supplier<Executor> executor);
	}
	
	interface VkAPI extends PlatformAPI {
		
		VkAPIClient client();
		
		GroupActor actor();
		
		int waitTime();
		
		@Override
		default Platform platform() {
			return Platform.VK;
		}
		
		interface Builder extends PlatformAPI.Builder<VkAPI, Builder> {
			
			Builder api(Supplier<VkApiClient> api);
			
			default Builder actor(final long groupId, final String token) {
				return this.actor(() -> new GroupActor(groupId, Objects.requireNonNull(token, "token")));
			}
			
			Builder actor(Supplier<GroupActor> actor);
			
			Builder waitTime(int waitTime);
		}
	}
	
	interface TgAPI extends PlatformAPI {
		
		TelegramBotsLongPollingApplication api();
		
		TelegramClient client();
		
		String token();
		
		@Override
		default Platform platform() {
			return Platform.TG;
		}
		
		interface Builder extends PlatformAPI.Builder<TgAPI, Builder> {
			
			Builder api(final Supplier<TelegramBotsLongPollingApplication> api);
			
			Builder client(final Function<String, TelegramClient> client);
			
			Builder token(final String token);
		}
	}
}
