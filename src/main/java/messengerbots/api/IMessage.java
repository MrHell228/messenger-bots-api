package messengerbots.api;

import java.util.List;

import messengerbots.api.media.IMedia;

public interface IMessage extends IMessageTemplate, APISource, PlatformSource {
	
	IChat chat();
	
	//IUser user();
	
	IMessageTemplate template();
	
	@Override
	default BotAPI api() {
		return this.chat().api();
	}
	
	@Override
	default Platform platform() {
		return this.chat().platform();
	}
	
	@Override
	default String text() {
		return this.template().text();
	}
	
	@Override
	default List<IMedia> media() {
		return this.template().media();
	}
}
