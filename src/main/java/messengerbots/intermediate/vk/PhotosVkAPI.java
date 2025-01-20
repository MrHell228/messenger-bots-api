package messengerbots.intermediate.vk;

import com.vk.api.sdk.actions.Photos;
import com.vk.api.sdk.queries.photos.PhotosGetChatUploadServerQuery;
import com.vk.api.sdk.queries.photos.PhotosGetMessagesUploadServerQuery;
import com.vk.api.sdk.queries.photos.PhotosGetOwnerCoverPhotoUploadServerQuery;
import com.vk.api.sdk.queries.photos.PhotosSaveMessagesPhotoQuery;
import com.vk.api.sdk.queries.photos.PhotosSaveOwnerCoverPhotoQuery;

public class PhotosVkAPI {
	
	private final VkAPIClient api;
	private final Photos internal;
	
	public PhotosVkAPI(VkAPIClient api) {
		this.api = api;
		this.internal = api.client().photos();
	}
	
	public VkAPIClient api() {
		return this.api;
	}
	
	public Photos internal() {
		return this.internal;
	}
	
	public PhotosGetChatUploadServerQuery getChatUploadServer() {
		return this.api.actor().map(
				group -> this.internal.getChatUploadServer(group),
				user -> this.internal.getChatUploadServer(user));
	}
	
	public PhotosGetChatUploadServerQuery getChatUploadServer(final Integer chatId) {
		return this.api.actor().map(
				group -> this.internal.getChatUploadServer(group, chatId),
				user -> this.internal.getChatUploadServer(user, chatId));
	}
	
	public PhotosGetMessagesUploadServerQuery getMessagesUploadServer() {
		return this.api.actor().map(
				group -> this.internal.getMessagesUploadServer(group),
				user -> this.internal.getMessagesUploadServer(user));
	}
	
	public PhotosGetOwnerCoverPhotoUploadServerQuery getOwnerCoverPhotoUploadServer() {
		return this.api.actor().map(
				group -> this.internal.getOwnerCoverPhotoUploadServer(group),
				user -> this.internal.getOwnerCoverPhotoUploadServer(user));
	}
	
	public PhotosSaveMessagesPhotoQuery saveMessagesPhoto() {
		return this.api.actor().map(
				group -> this.internal.saveMessagesPhoto(group),
				user -> this.internal.saveMessagesPhoto(user));
	}
	
	public PhotosSaveMessagesPhotoQuery saveMessagesPhoto(final String photo) {
		return this.api.actor().map(
				group -> this.internal.saveMessagesPhoto(group, photo),
				user -> this.internal.saveMessagesPhoto(user, photo));
	}
	
	public PhotosSaveOwnerCoverPhotoQuery saveOwnerCoverPhoto() {
		return this.api.actor().map(
				group -> this.internal.saveOwnerCoverPhoto(group),
				user -> this.internal.saveOwnerCoverPhoto(user));
	}
}
