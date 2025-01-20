package messengerbots.api;

public interface IBuilder<E, B extends IBuilder<E, B>> {
	
	B reset();
	
	E build();
}
