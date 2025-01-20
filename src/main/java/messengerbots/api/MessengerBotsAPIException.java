package messengerbots.api;

import java.io.Serial;

public class MessengerBotsAPIException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 5540924348456296322L;
	
	public MessengerBotsAPIException() {
		super();
	}
	
	public MessengerBotsAPIException(String message) {
		super(message);
	}
	
	public MessengerBotsAPIException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MessengerBotsAPIException(Throwable cause) {
		super(cause);
	}
	
	protected MessengerBotsAPIException(String message, Throwable cause,
										boolean enableSuppression,
										boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
