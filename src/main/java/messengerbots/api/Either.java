package messengerbots.api;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Either<L, R> {
	
	private Either() {
	}
	
	public abstract boolean isLeft();
	
	public abstract boolean isRight();
	
	public abstract Optional<L> left();
	
	public abstract Optional<R> right();
	
	public abstract <T> T map(Function<L, T> leftMapper, Function<R, T> rightMapper);
	
	public abstract <T> Either<T, R> mapLeft(Function<L, T> mapper);
	
	public abstract <T> Either<L, T> mapRight(Function<R, T> mapper);
	
	public abstract void apply(Consumer<L> leftAction, Consumer<R> rightAction);
	
	public abstract void applyLeft(Consumer<L> action);
	
	public abstract void applyRight(Consumer<R> action);
	
	public abstract boolean test(Predicate<L> leftTest, Predicate<R> rightTest);
	
	public static <L, R> Either<L, R> left(final L left) {
		return new Left<>(Objects.requireNonNull(left, "left"));
	}
	
	public static <L, R> Either<L, R> right(final R right) {
		return new Right<>(Objects.requireNonNull(right, "right"));
	}
	
	private static class Left<L, R> extends Either<L, R> {
		
		private final L value;
		
		private Left(final L value) {
			this.value = value;
		}
		
		@Override
		public boolean isLeft() {
			return true;
		}
		
		@Override
		public boolean isRight() {
			return false;
		}
		
		@Override
		public Optional<L> left() {
			return Optional.of(this.value);
		}
		
		@Override
		public Optional<R> right() {
			return Optional.empty();
		}
		
		@Override
		public <T> T map(final Function<L, T> leftMapper, final Function<R, T> rightMapper) {
			return leftMapper.apply(this.value);
		}
		
		@Override
		public <T> Either<T, R> mapLeft(final Function<L, T> mapper) {
			return new Left<>(mapper.apply(this.value));
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public <T> Either<L, T> mapRight(final Function<R, T> mapper) {
			return (Either<L, T>) this;
		}
		
		@Override
		public void apply(final Consumer<L> leftAction, final Consumer<R> rightAction) {
			leftAction.accept(this.value);
		}
		
		@Override
		public void applyLeft(final Consumer<L> action) {
			action.accept(this.value);
		}
		
		@Override
		public void applyRight(final Consumer<R> action) {
		}
		
		@Override
		public boolean test(final Predicate<L> leftTest, final Predicate<R> rightTest) {
			return leftTest.test(this.value);
		}
	}
	
	private static class Right<L, R> extends Either<L, R> {
		
		private final R value;
		
		private Right(final R value) {
			this.value = value;
		}
		
		@Override
		public boolean isLeft() {
			return false;
		}
		
		@Override
		public boolean isRight() {
			return true;
		}
		
		@Override
		public Optional<L> left() {
			return Optional.empty();
		}
		
		@Override
		public Optional<R> right() {
			return Optional.of(this.value);
		}
		
		@Override
		public <T> T map(final Function<L, T> leftMapper, final Function<R, T> rightMapper) {
			return rightMapper.apply(this.value);
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public <T> Either<T, R> mapLeft(final Function<L, T> mapper) {
			return (Either<T, R>) this;
		}
		
		@Override
		public <T> Either<L, T> mapRight(final Function<R, T> mapper) {
			return new Right<>(mapper.apply(this.value));
		}
		
		@Override
		public void apply(final Consumer<L> leftAction, final Consumer<R> rightAction) {
			rightAction.accept(this.value);
		}
		
		@Override
		public void applyLeft(final Consumer<L> action) {
		}
		
		@Override
		public void applyRight(final Consumer<R> action) {
			action.accept(this.value);
		}
		
		@Override
		public boolean test(final Predicate<L> leftTest, final Predicate<R> rightTest) {
			return rightTest.test(this.value);
		}
	}
}
