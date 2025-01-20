package messengerbots.internal;

import java.io.File;
import java.net.URL;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Video;
import org.telegram.telegrambots.meta.api.objects.games.Animation;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import com.vk.api.sdk.objects.messages.MessageAttachment;

import messengerbots.api.BotAPI;
import messengerbots.api.media.IMedia;

abstract class MediaImpl implements IMedia, VkLikeMedia {
	
	private final BotAPI api;
	
	protected MediaImpl(final BotAPI api) {
		this.api = api;
	}
	
	@Override
	public BotAPI api() {
		return this.api;
	}
	
	//public abstract String toVkString();
	
	public static @Nullable IMedia of(final BotAPI api, final MessageAttachment attachment) {
		switch (attachment.getType()) {
		case PHOTO: {
			return PhotoSetImpl.of(api, attachment.getPhoto());
		}
		case VIDEO: {
			// TODO
			
			break;
		}
		case AUDIO: {
			// TODO
			
			break;
		}
		case AUDIO_MESSAGE: {
			// TODO
			
			break;
		}
		case DOC: {
			// TODO
			
			break;
		}
		
		case ARTICLE:
			break;
		case CALL:
			break;
		case GIFT:
			break;
		case GRAFFITI:
			break;
		case LINK:
			break;
		case MARKET:
			break;
		case POLL:
			break;
		case STICKER:
			break;
		case VIDEO_PLAYLIST:
			break;
		case WALL:
			break;
		case WALL_REPLY:
			break;
		}
		
		return null;
	}
	
	public static @Nullable IMedia of(final BotAPI api, final Message message) {
		if (message.hasPhoto()) {
			return new PhotoSetImpl.Tg(api, message.getPhoto());
		} else if (message.hasVideo()) {
			final Video video = message.getVideo();
			// TODO
			
		} else if (message.hasAudio()) {
			final Audio audio = message.getAudio();
			// TODO
			
		} else if (message.hasDocument()) {
			final Document document = message.getDocument();
			// TODO
			
		} else if (message.hasAnimation()) {
			final Animation animation = message.getAnimation();
			// TODO
			
		}
		
		return null;
	}
	
	public static interface TgLike {
		
		String toTgString();
	}
	
	public static interface External extends TgLike {
		
		URL url();
		
		@Override
		default String toTgString() {
			return this.url().toString();
		}
	}
	
	public static interface Local {
		
		File file();
	}
}
