package messengerbots.internal;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import messengerbots.api.BotAPI;
import messengerbots.api.PlatformAPI;
import messengerbots.api.PlatformAPI.TgAPI;
import messengerbots.api.PlatformAPI.VkAPI;
import messengerbots.api.PlatformAPI.TgAPI.Builder;

public class BotAPIBuilder implements BotAPI.Builder {
	
	private Supplier<Executor> executor;
	private @Nullable Consumer<VkAPI.Builder> vk;
	private @Nullable Consumer<TgAPI.Builder> tg;
	
	public BotAPIBuilder() {
		this.reset();
	}
	
	@Override
	public BotAPI.Builder executor(Supplier<Executor> executor) {
		this.executor = Objects.requireNonNull(executor, "executor");
		return this;
	}
	
	@Override
	public BotAPI.Builder vk(final Consumer<VkAPI.Builder> builder) {
		Objects.requireNonNull(builder, "builder");
		this.vk = this.vk == null ? builder : this.vk.andThen(builder);
		return this;
	}
	
	@Override
	public BotAPI.Builder tg(final Consumer<TgAPI.Builder> builder) {
		Objects.requireNonNull(builder, "builder");
		this.tg = this.tg == null ? builder : this.tg.andThen(builder);
		return this;
	}
	
	@Override
	public BotAPI.Builder reset() {
		this.executor(Executors.newSingleThreadExecutor());
		this.vk = null;
		this.tg = null;
		return this;
	}
	
	@Override
	public BotAPI build() {
		final PlatformAPI.VkAPI vk;
		if (this.vk == null) {
			vk = null;
		} else {
			final PlatformAPI.VkAPI.Builder builder = new VkAPIBuilder();
			this.vk.accept(builder);
			vk = builder.build();
		}
		
		final PlatformAPI.TgAPI tg;
		if (this.tg == null) {
			tg = null;
		} else {
			final PlatformAPI.TgAPI.Builder builder = new TgAPIBuilder();
			this.tg.accept(builder);
			tg = builder.build();
		}
		
		return new BotAPIImpl(vk, tg);
	}
	
	private class VkAPIBuilder implements VkAPI.Builder {
		
		private @Nullable Supplier<Executor> executor;
		private Supplier<VkApiClient> api;
		private Supplier<GroupActor> actor;
		private int waitTime;
		
		private VkAPIBuilder() {
			this.reset();
		}
		
		@Override
		public VkAPI.Builder executor(Supplier<Executor> executor) {
			this.executor = Objects.requireNonNull(executor, "executor");
			return this;
		}
		
		@Override
		public VkAPI.Builder api(final Supplier<VkApiClient> api) {
			this.api = Objects.requireNonNull(api, "api");
			return this;
		}
		
		@Override
		public VkAPI.Builder actor(final Supplier<GroupActor> actor) {
			this.actor = Objects.requireNonNull(actor, "actor");
			return this;
		}
		
		@Override
		public VkAPI.Builder waitTime(final int waitTime) {
			if (waitTime <= 0) {
				throw new IllegalArgumentException("waitTime must be positive");
			}
			
			this.waitTime = waitTime;
			return this;
		}
		
		@Override
		public VkAPI.Builder reset() {
			this.executor = null;
			this.api = () -> new VkApiClient(new HttpTransportClient());
			this.actor = null;
			this.waitTime = 10;
			return this;
		}
		
		@Override
		public VkAPI build() {
			if (this.actor == null) {
				throw new IllegalStateException("actor is not set");
			}
			
			final Executor executor = this.executor != null
					? this.executor.get()
					: BotAPIBuilder.this.executor.get();
			
			return new VkAPIImpl(
					Objects.requireNonNull(executor, "executor"),
					Objects.requireNonNull(this.api.get(), "api"),
					Objects.requireNonNull(this.actor.get(), "actor"),
					this.waitTime
					);
		}
	}
	
	private class TgAPIBuilder implements TgAPI.Builder {
		
		private @Nullable Supplier<Executor> executor;
		private Supplier<TelegramBotsLongPollingApplication> api;
		private Function<String, TelegramClient> client;
		private String token;
		
		public TgAPIBuilder() {
			this.reset();
		}
		
		@Override
		public Builder executor(final Supplier<Executor> executor) {
			this.executor = Objects.requireNonNull(executor, "executor");
			return this;
		}
		
		@Override
		public TgAPI.Builder api(final Supplier<TelegramBotsLongPollingApplication> api) {
			this.api = Objects.requireNonNull(api, "api");
			return this;
		}
		
		@Override
		public TgAPI.Builder client(final Function<String, TelegramClient> client) {
			this.client = Objects.requireNonNull(client, "client");
			return this;
		}
		
		@Override
		public TgAPI.Builder token(final String token) {
			this.token = Objects.requireNonNull(token, "token");
			return this;
		}
		
		@Override
		public TgAPI.Builder reset() {
			this.api = TelegramBotsLongPollingApplication::new;
			this.client = OkHttpTelegramClient::new;
			this.token = null;
			return this;
		}
		
		@Override
		public TgAPI build() {
			if (this.token == null) {
				throw new IllegalStateException("token is not set");
			}
			
			final Executor executor = this.executor != null
					? this.executor.get()
					: BotAPIBuilder.this.executor.get();
			
			return new TgAPIImpl(
					Objects.requireNonNull(executor, "executor"),
					Objects.requireNonNull(this.api.get(), "api"),
					Objects.requireNonNull(this.client.apply(this.token), "client"),
					this.token
					);
		}
	}
}
