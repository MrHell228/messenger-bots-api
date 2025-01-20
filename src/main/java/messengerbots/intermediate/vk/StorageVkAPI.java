package messengerbots.intermediate.vk;

import com.vk.api.sdk.actions.Storage;
import com.vk.api.sdk.queries.storage.StorageGetKeysQuery;
import com.vk.api.sdk.queries.storage.StorageGetQuery;
import com.vk.api.sdk.queries.storage.StorageSetQuery;

public class StorageVkAPI {
	
	private final VkAPIClient api;
	private final Storage internal;
	
	public StorageVkAPI(VkAPIClient api) {
		this.api = api;
		this.internal = api.client().storage();
	}
	
	public VkAPIClient api() {
		return this.api;
	}
	
	public Storage internal() {
		return this.internal;
	}
	
	public StorageGetQuery get() {
		return this.api.actor().map(
				group -> this.internal.get(group),
				user -> this.internal.get(user));
	}
	
	public StorageGetKeysQuery getKeys() {
		return this.api.actor().map(
				group -> this.internal.getKeys(group),
				user -> this.internal.getKeys(user));
	}
	
	public StorageSetQuery set() {
		return this.api.actor().map(
				group -> this.internal.set(group),
				user -> this.internal.set(user));
	}
	
	public StorageSetQuery set(final String key) {
		return this.api.actor().map(
				group -> this.internal.set(group, key),
				user -> this.internal.set(user, key));
	}
}
