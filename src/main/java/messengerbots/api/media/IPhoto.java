package messengerbots.api.media;

public interface IPhoto extends IMedia {
	
	int width();
	
	int height();
	
	@Override
	default MediaType type() {
		return MediaType.PHOTO;
	}
}
