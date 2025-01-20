package messengerbots.api;

import java.io.File;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

import messengerbots.api.PlatformAPI.TgAPI;
import messengerbots.api.PlatformAPI.VkAPI;
import messengerbots.api.media.IPhoto;
import messengerbots.internal.BotAPIBuilder;

public interface BotAPI extends AutoCloseable {
	
	Optional<VkAPI> vk();
	
	default VkAPI requireVk() {
		return this.vk().orElseThrow(() -> new IllegalStateException("Vk API is not present"));
	}
	
	Optional<TgAPI> tg();
	
	default TgAPI requireTg() {
		return this.tg().orElseThrow(() -> new IllegalStateException("Tg API is not present"));
	}
	
	default IChat chat(final Platform platform, final long id) {
		return Objects.requireNonNull(platform, "platform").chat(this, id);
	}
	
	default IChat chat(final Platform platform, final String id) {
		return Objects.requireNonNull(platform, "platform").chat(this, id);
	}
	
	IPhoto photo(File file);
	
	void registerBot(final IBot bot);
	
	static Builder builder() {
		return new BotAPIBuilder();
	}
	
	interface Builder extends IBuilder<BotAPI, Builder> {
		
		default Builder executor(final Executor executor) {
			Objects.requireNonNull(executor, "executor");
			return this.executor(() -> executor);
		}
		
		Builder executor(Supplier<Executor> executor);
		
		Builder vk(Consumer<VkAPI.Builder> builder);
		
		Builder tg(Consumer<TgAPI.Builder> builder);
	}
}
