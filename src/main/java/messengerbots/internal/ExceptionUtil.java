package messengerbots.internal;

import java.io.IOException;
import java.net.MalformedURLException;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import messengerbots.api.MessengerBotsAPIException;
import messengerbots.api.media.IMedia;

class ExceptionUtil {
	
	public static MessengerBotsAPIException of(Exception e) {
		return new MessengerBotsAPIException(e);
	}
	
	public static MessengerBotsAPIException url(MalformedURLException e) {
		return new MessengerBotsAPIException("Failed to retrieve url of external media", e);
	}
	
	public static MessengerBotsAPIException tgExecute(TelegramApiException e) {
		return new MessengerBotsAPIException("Failed to execute TG request", e);
	}
	
	public static MessengerBotsAPIException vkExecute(Exception e) {
		return new MessengerBotsAPIException("Failed to execute VK request", e);
	}
	
	public static MessengerBotsAPIException wrongMedia(IMedia media) {
		return new MessengerBotsAPIException("Media must implement at least one internal interface");
	}
	
	public static MessengerBotsAPIException close(Exception e) {
		return new MessengerBotsAPIException("", e);
	}
	
	public static MessengerBotsAPIException invalidFile(String type, IOException e) {
		return new MessengerBotsAPIException("Provided file is not of the required type: " + type, e);
	}
	
	private ExceptionUtil() {
	}
}
