package messengerbots.intermediate.vk;

import com.vk.api.sdk.actions.Users;
import com.vk.api.sdk.queries.users.UsersGetQuery;

public class UsersVkAPI {
	
	private final VkAPIClient api;
	private final Users internal;
	
	public UsersVkAPI(VkAPIClient api) {
		this.api = api;
		this.internal = api.client().users();
	}
	
	public VkAPIClient api() {
		return this.api;
	}
	
	public Users internal() {
		return this.internal;
	}
	
	public UsersGetQuery get() {
		return this.api.actor().map(
				group -> this.internal.get(group),
				user -> this.internal.get(user));
	}
}
