package messengerbots.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import messengerbots.api.media.IMedia;

public interface IMessageTemplate {
	
	String text();
	
	List<IMedia> media();
	
	static Builder builder() {
		return new Builder();
	}
	
	class Builder implements IBuilder<IMessageTemplate, Builder> {
		
		private String text;
		private List<IMedia> media = new ArrayList<>();
		
		public Builder() {
			this.reset();
		}
		
		public Builder text(final String text) {
			this.text = Objects.requireNonNull(text, "text");
			return this;
		}
		
		public Builder media(final Iterable<IMedia> medias) {
			for (final IMedia media : Objects.requireNonNull(medias, "medias")) {
				this.media.add(Objects.requireNonNull(media, "media"));
			}
			return this;
		}
		
		public Builder media(final IMedia... medias) {
			for (final IMedia media : Objects.requireNonNull(medias, "medias")) {
				this.media.add(Objects.requireNonNull(media, "media"));
			}
			return this;
		}
		
		@Override
		public Builder reset() {
			this.text = "";
			this.media.clear();
			return this;
		}

		@Override
		public IMessageTemplate build() {
			if (this.text.isEmpty() && this.media.isEmpty()) {
				throw new IllegalStateException("Message must contain at least one media or text");
			}
			
			return new Impl(this.text, this.media);
		}
		
	}
	
	class Impl implements IMessageTemplate {
		
		private final String text;
		private final List<IMedia> media;
		
		Impl(final String text, final List<IMedia> media) {
			this.text = text;
			this.media = List.copyOf(media);
		}
		
		@Override
		public String text() {
			return this.text;
		}
		
		@Override
		public List<IMedia> media() {
			return this.media;
		}
	}
}
