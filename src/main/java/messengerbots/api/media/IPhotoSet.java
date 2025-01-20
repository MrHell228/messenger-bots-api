package messengerbots.api.media;

import java.util.List;

public interface IPhotoSet extends IPhoto {
	
	List<IPhoto> sizes();
	
	IPhoto max();
	
	IPhoto min();
	
	@Override
	default int width() {
		return this.max().width();
	}
	
	@Override
	default int height() {
		return this.max().height();
	}
	
	/*@Override
	default File file() {
		return this.max().file();
	}*/
}
