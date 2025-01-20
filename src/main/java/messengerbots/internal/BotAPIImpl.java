package messengerbots.internal;

import java.io.File;
import java.util.Optional;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import messengerbots.api.BotAPI;
import messengerbots.api.IBot;
import messengerbots.api.PlatformAPI.TgAPI;
import messengerbots.api.PlatformAPI.VkAPI;
import messengerbots.api.media.IPhoto;

class BotAPIImpl implements BotAPI {
	
	private final Optional<VkAPI> vk;
	private final Optional<TgAPI> tg;
	
	protected BotAPIImpl(final @Nullable VkAPI vk, final @Nullable TgAPI tg) {
		this.vk = Optional.ofNullable(vk);
		this.tg = Optional.ofNullable(tg);
	}
	
	@Override
	public Optional<VkAPI> vk() {
		return this.vk;
	}
	
	@Override
	public Optional<TgAPI> tg() {
		return this.tg;
	}
	
	@Override
	public void close() {
		this.vk().ifPresent(vk -> {
			try {
				vk.close();
			} catch (Exception e) {
				throw ExceptionUtil.close(e);
			}
		});
		
		this.tg().ifPresent(tg -> {
			try {
				tg.close();
			} catch (Exception e) {
				throw ExceptionUtil.close(e);
			}
		});
	}
	
	@Override
	public void registerBot(final IBot bot) {
		this.vk().ifPresent(vk -> {
			new VkHandler(this, bot).run();
		});
		
		this.tg().ifPresent(tg -> {
			try {
				tg.api().registerBot(tg.token(), new TgHandler(this, bot));
			} catch (TelegramApiException e) {
				throw ExceptionUtil.tgExecute(e);
			}
		});
	}
	
	@Override
	public IPhoto photo(final File file) {
		return new PhotoImpl.Local(this, file);
	}
}
