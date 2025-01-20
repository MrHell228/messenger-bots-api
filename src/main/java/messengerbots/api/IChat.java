package messengerbots.api;

import java.util.OptionalLong;

public interface IChat extends APISource, PlatformSource {
	
	boolean isPrivate();
	
	default boolean isPublic() {
		return !this.isPrivate();
	}
	
	String id();
	
	OptionalLong longId();
	
	//List<IUser> users();
	
	default void send(final IMessageTemplate.Builder builder) {
		this.send(builder.build());
	}
	
	void send(IMessageTemplate message);
}
