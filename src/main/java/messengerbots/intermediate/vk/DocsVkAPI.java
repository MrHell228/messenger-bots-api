package messengerbots.intermediate.vk;

import java.util.List;

import com.vk.api.sdk.actions.Docs;
import com.vk.api.sdk.queries.docs.DocsGetByIdQuery;
import com.vk.api.sdk.queries.docs.DocsGetMessagesUploadServerQuery;
import com.vk.api.sdk.queries.docs.DocsGetWallUploadServerQuery;

public class DocsVkAPI {
	
	private final VkAPIClient api;
	private final Docs internal;
	
	public DocsVkAPI(VkAPIClient api) {
		this.api = api;
		this.internal = api.client().docs();
	}
	
	public VkAPIClient api() {
		return this.api;
	}
	
	public Docs internal() {
		return this.internal;
	}
	
	public DocsGetByIdQuery getById() {
		return this.api.actor().map(
				group -> this.internal.getById(group),
				user -> this.internal.getById(user));
	}
	
	public DocsGetByIdQuery getById(final List<String> docs) {
		return this.api.actor().map(
				group -> this.internal.getById(group, docs),
				user -> this.internal.getById(user, docs));
	}
	
	public DocsGetByIdQuery getById(final String... docs) {
		return this.api.actor().map(
				group -> this.internal.getById(group, docs),
				user -> this.internal.getById(user, docs));
	}
	
	public DocsGetMessagesUploadServerQuery getMessagesUploadServer() {
		return this.api.actor().map(
				group -> this.internal.getMessagesUploadServer(group),
				user -> this.internal.getMessagesUploadServer(user));
	}
	
	public DocsGetWallUploadServerQuery getWallUploadServer() {
		return this.api.actor().map(
				group -> this.internal.getWallUploadServer(group),
				user -> this.internal.getWallUploadServer(user));
	}
}
