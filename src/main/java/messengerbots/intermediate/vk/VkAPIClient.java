package messengerbots.intermediate.vk;

import com.vk.api.sdk.actions.Upload;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;

import messengerbots.api.Either;

public class VkAPIClient {
	
	private final VkApiClient client;
	private final Either<GroupActor, UserActor> actor;
	
	public VkAPIClient(final VkApiClient client, final GroupActor group) {
		this.client = client;
		this.actor = Either.left(group);
	}
	
	public VkAPIClient(final VkApiClient client, final UserActor user) {
		this.client = client;
		this.actor = Either.right(user);
	}
	
	public VkApiClient client() {
		return this.client;
	}
	
	public Either<GroupActor, UserActor> actor() {
		return this.actor;
	}
	
	public DocsVkAPI docs() {
		return new DocsVkAPI(this);
	}
	
	public GroupsVkAPI groups() {
		return new GroupsVkAPI(this);
	}
	
	public MessagesVkAPI messages() {
		return new MessagesVkAPI(this);
	}
	
	public PhotosVkAPI photos() {
		return new PhotosVkAPI(this);
	}
	
	public PodcastsVkAPI podcasts() {
		return new PodcastsVkAPI(this);
	}
	
	public StorageVkAPI storage() {
		return new StorageVkAPI(this);
	}
	
	public StoriesVkAPI stories() {
		return new StoriesVkAPI(this);
	}
	
	public Upload upload() {
		return this.client.upload();
	}
	
	public UsersVkAPI users() {
		return new UsersVkAPI(this);
	}
	
	public UtilsVkAPI utils() {
		return new UtilsVkAPI(this);
	}
}
