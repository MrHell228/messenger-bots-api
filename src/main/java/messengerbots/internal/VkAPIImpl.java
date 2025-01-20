package messengerbots.internal;

import java.io.File;
import java.net.URL;
import java.util.concurrent.Executor;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.responses.MessageUploadResponse;

import messengerbots.api.PlatformAPI.VkAPI;
import messengerbots.intermediate.vk.VkAPIClient;

class VkAPIImpl implements VkAPI {
	
	private final Executor executor;
	private final VkAPIClient api;
	private final GroupActor actor;
	private final int waitTime;
	
	public VkAPIImpl(
		final Executor executor,
		final VkApiClient api,
		final GroupActor actor,
		final int waitTime
	) {
		this.executor = executor;
		this.api = new VkAPIClient(api, actor);
		this.actor = actor;
		this.waitTime = waitTime;
		
		try {
			this.api.groups()
				.setLongPollSettings()
				.apiVersion("5.199")
				.enabled(true)
				.messageNew(true)
				.execute();
			
			this.api.groups()
				.getLongPollServer()
				.execute();
		} catch (ApiException | ClientException e) {
			throw ExceptionUtil.vkExecute(e);
		}
	}
	
	@Override
	public Executor executor() {
		return this.executor;
	}
	
	@Override
	public VkAPIClient client() {
		return this.api;
	}
	
	@Override
	public GroupActor actor() {
		return this.actor;
	}
	
	@Override
	public int waitTime() {
		return this.waitTime;
	}
	
	@Override
	public void close() throws Exception {
	}
	
	public Photo uploadPhoto(final File file) {
		try {
			System.out.print(file.toString());
			
			final URL url = Utils.toURL(this.client().photos()
					.getMessagesUploadServer()
					.execute().getUploadUrl());
			
			final MessageUploadResponse response = this.client().upload()
					.photoMessage(url.toString(), file)
					.execute();
			
			System.out.print(response.toPrettyString());
			
			final Photo photo = this.client().photos()
					.saveMessagesPhoto(response.getPhoto())
					.server(response.getServer())
					.hash(response.getHash())
					.execute().get(0);
			
			return photo;
		} catch (ApiException | ClientException e) {
			throw ExceptionUtil.vkExecute(e);
		}
	}
}
