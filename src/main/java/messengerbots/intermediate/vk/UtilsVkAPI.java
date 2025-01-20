package messengerbots.intermediate.vk;

import com.vk.api.sdk.actions.Utils;
import com.vk.api.sdk.queries.utils.UtilsCheckLinkQuery;
import com.vk.api.sdk.queries.utils.UtilsGetLinkStatsQuery;
import com.vk.api.sdk.queries.utils.UtilsGetLinkStatsQueryWithExtended;
import com.vk.api.sdk.queries.utils.UtilsGetServerTimeQuery;
import com.vk.api.sdk.queries.utils.UtilsGetShortLinkQuery;
import com.vk.api.sdk.queries.utils.UtilsResolveScreenNameQuery;

public class UtilsVkAPI {
	
	private final VkAPIClient api;
	private final Utils internal;
	
	public UtilsVkAPI(VkAPIClient api) {
		this.api = api;
		this.internal = api.client().utils();
	}
	
	public VkAPIClient api() {
		return this.api;
	}
	
	public Utils internal() {
		return this.internal;
	}
	
	public UtilsCheckLinkQuery checkLink() {
		return this.api.actor().map(
				group -> this.internal.checkLink(group),
				user -> this.internal.checkLink(user));
	}
	
	public UtilsCheckLinkQuery checkLink(final String url) {
		return this.api.actor().map(
				group -> this.internal.checkLink(group, url),
				user -> this.internal.checkLink(user, url));
	}
	
	public UtilsGetLinkStatsQuery getLinkStats() {
		return this.api.actor().map(
				group -> this.internal.getLinkStats(group),
				user -> this.internal.getLinkStats(user));
	}
	
	public UtilsGetLinkStatsQuery getLinkStats(final String key) {
		return this.api.actor().map(
				group -> this.internal.getLinkStats(group, key),
				user -> this.internal.getLinkStats(user, key));
	}
	
	public UtilsGetLinkStatsQueryWithExtended getLinkStatsExtended(final String key) {
		return this.api.actor().map(
				group -> this.internal.getLinkStatsExtended(group, key),
				user -> this.internal.getLinkStatsExtended(user, key));
	}
	
	public UtilsGetServerTimeQuery getServerTime() {
		return this.api.actor().map(
				group -> this.internal.getServerTime(group),
				user -> this.internal.getServerTime(user));
	}
	
	public UtilsGetShortLinkQuery getShortLink() {
		return this.api.actor().map(
				group -> this.internal.getShortLink(group),
				user -> this.internal.getShortLink(user));
	}
	
	public UtilsGetShortLinkQuery getShortLink(final String url) {
		return this.api.actor().map(
				group -> this.internal.getShortLink(group, url),
				user -> this.internal.getShortLink(user, url));
	}
	
	public UtilsResolveScreenNameQuery resolveScreenName() {
		return this.api.actor().map(
				group -> this.internal.resolveScreenName(group),
				user -> this.internal.resolveScreenName(user));
	}
	
	public UtilsResolveScreenNameQuery resolveScreenName(final String screenName) {
		return this.api.actor().map(
				group -> this.internal.resolveScreenName(group, screenName),
				user -> this.internal.resolveScreenName(user, screenName));
	}
}
