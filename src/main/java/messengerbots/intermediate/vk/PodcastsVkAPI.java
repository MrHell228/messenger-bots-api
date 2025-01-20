package messengerbots.intermediate.vk;

import com.vk.api.sdk.actions.Podcasts;
import com.vk.api.sdk.queries.podcasts.PodcastsSearchPodcastQuery;

public class PodcastsVkAPI {
	
	private final VkAPIClient api;
	private final Podcasts internal;
	
	public PodcastsVkAPI(VkAPIClient api) {
		this.api = api;
		this.internal = api.client().podcasts();
	}
	
	public VkAPIClient api() {
		return this.api;
	}
	
	public Podcasts internal() {
		return this.internal;
	}
	
	public PodcastsSearchPodcastQuery searchPodcast() {
		return this.api.actor().map(
				group -> this.internal.searchPodcast(group),
				user -> this.internal.searchPodcast(user));
	}
	
	public PodcastsSearchPodcastQuery searchPodcast(final String searchString) {
		return this.api.actor().map(
				group -> this.internal.searchPodcast(group, searchString),
				user -> this.internal.searchPodcast(user, searchString));
	}
}
