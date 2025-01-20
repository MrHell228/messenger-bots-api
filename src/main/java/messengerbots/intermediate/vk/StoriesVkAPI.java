package messengerbots.intermediate.vk;

import java.util.List;

import com.vk.api.sdk.actions.Stories;
import com.vk.api.sdk.queries.stories.StoriesDeleteQuery;
import com.vk.api.sdk.queries.stories.StoriesGetByIdQueryWithExtended;
import com.vk.api.sdk.queries.stories.StoriesGetPhotoUploadServerQuery;
import com.vk.api.sdk.queries.stories.StoriesGetQueryWithV5113;
import com.vk.api.sdk.queries.stories.StoriesGetRepliesQueryWithV5113;
import com.vk.api.sdk.queries.stories.StoriesGetStatsQuery;
import com.vk.api.sdk.queries.stories.StoriesGetVideoUploadServerQuery;
import com.vk.api.sdk.queries.stories.StoriesGetViewersQueryWithExtendedV5115;
import com.vk.api.sdk.queries.stories.StoriesHideAllRepliesQuery;
import com.vk.api.sdk.queries.stories.StoriesHideReplyQuery;
import com.vk.api.sdk.queries.stories.StoriesSaveQuery;

public class StoriesVkAPI {
	
	private final VkAPIClient api;
	private final Stories internal;
	
	public StoriesVkAPI(VkAPIClient api) {
		this.api = api;
		this.internal = api.client().stories();
	}
	
	public VkAPIClient api() {
		return this.api;
	}
	
	public Stories internal() {
		return this.internal;
	}
	
	public StoriesDeleteQuery delete() {
		return this.api.actor().map(
				group -> this.internal.delete(group),
				user -> this.internal.delete(user));
	}
	
	public StoriesGetByIdQueryWithExtended getByIdExtended(final String... stories) {
		return this.api.actor().map(
				group -> this.internal.getByIdExtended(group, stories),
				user -> this.internal.getByIdExtended(user, stories));
	}
	
	public StoriesGetByIdQueryWithExtended getByIdExtended(final List<String> stories) {
		return this.api.actor().map(
				group -> this.internal.getByIdExtended(group, stories),
				user -> this.internal.getByIdExtended(user, stories));
	}
	
	public StoriesGetPhotoUploadServerQuery getPhotoUploadServer() {
		return this.api.actor().map(
				group -> this.internal.getPhotoUploadServer(group),
				user -> this.internal.getPhotoUploadServer(user));
	}
	
	public StoriesGetRepliesQueryWithV5113 getRepliesV5113(final Long ownerId, final Integer storyId) {
		return this.api.actor().map(
				group -> this.internal.getRepliesV5113(group, ownerId, storyId),
				user -> this.internal.getRepliesV5113(user, ownerId, storyId));
	}
	
	public StoriesGetStatsQuery getStats() {
		return this.api.actor().map(
				group -> this.internal.getStats(group),
				user -> this.internal.getStats(user));
	}
	
	public StoriesGetStatsQuery getStats(final Long ownerId, final Integer storyId) {
		return this.api.actor().map(
				group -> this.internal.getStats(group, ownerId, storyId),
				user -> this.internal.getStats(user, ownerId, storyId));
	}
	
	public StoriesGetQueryWithV5113 getV5113() {
		return this.api.actor().map(
				group -> this.internal.getV5113(group),
				user -> this.internal.getV5113(user));
	}
	
	public StoriesGetVideoUploadServerQuery getVideoUploadServer() {
		return this.api.actor().map(
				group -> this.internal.getVideoUploadServer(group),
				user -> this.internal.getVideoUploadServer(user));
	}
	
	public StoriesGetViewersQueryWithExtendedV5115 getViewersExtendedV5115(final Integer storyId) {
		return this.api.actor().map(
				group -> this.internal.getViewersExtendedV5115(group,storyId),
				user -> this.internal.getViewersExtendedV5115(user, storyId));
	}
	
	public StoriesHideAllRepliesQuery hideAllReplies() {
		return this.api.actor().map(
				group -> this.internal.hideAllReplies(group),
				user -> this.internal.hideAllReplies(user));
	}
	
	public StoriesHideAllRepliesQuery hideAllReplies(final Long ownerId) {
		return this.api.actor().map(
				group -> this.internal.hideAllReplies(group, ownerId),
				user -> this.internal.hideAllReplies(user, ownerId));
	}
	
	public StoriesHideReplyQuery hideReply() {
		return this.api.actor().map(
				group -> this.internal.hideReply(group),
				user -> this.internal.hideReply(user));
	}
	
	public StoriesHideReplyQuery hideReply(final Long ownerId, final Integer storyId) {
		return this.api.actor().map(
				group -> this.internal.hideReply(group, ownerId, storyId),
				user -> this.internal.hideReply(user, ownerId, storyId));
	}
	
	public StoriesSaveQuery save() {
		return this.api.actor().map(
				group -> this.internal.save(group),
				user -> this.internal.save(user));
	}
}
