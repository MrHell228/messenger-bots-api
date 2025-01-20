package messengerbots.intermediate.vk;

import com.vk.api.sdk.actions.Groups;
import com.vk.api.sdk.objects.groups.GetMembersFilter;
import com.vk.api.sdk.objects.groups.TagBindAct;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.queries.groups.GroupsAddAddressQuery;
import com.vk.api.sdk.queries.groups.GroupsAddCallbackServerQuery;
import com.vk.api.sdk.queries.groups.GroupsDeleteAddressQuery;
import com.vk.api.sdk.queries.groups.GroupsDeleteCallbackServerQuery;
import com.vk.api.sdk.queries.groups.GroupsDisableOnlineQuery;
import com.vk.api.sdk.queries.groups.GroupsEditAddressQuery;
import com.vk.api.sdk.queries.groups.GroupsEditCallbackServerQuery;
import com.vk.api.sdk.queries.groups.GroupsEditQuery;
import com.vk.api.sdk.queries.groups.GroupsEnableOnlineQuery;
import com.vk.api.sdk.queries.groups.GroupsGetBannedQuery;
import com.vk.api.sdk.queries.groups.GroupsGetByIdQueryWithObject;
import com.vk.api.sdk.queries.groups.GroupsGetCallbackConfirmationCodeQuery;
import com.vk.api.sdk.queries.groups.GroupsGetCallbackServersQuery;
import com.vk.api.sdk.queries.groups.GroupsGetCallbackSettingsQuery;
import com.vk.api.sdk.queries.groups.GroupsGetLongPollServerQuery;
import com.vk.api.sdk.queries.groups.GroupsGetLongPollSettingsQuery;
import com.vk.api.sdk.queries.groups.GroupsGetMembersQuery;
import com.vk.api.sdk.queries.groups.GroupsGetMembersQueryWithFields;
import com.vk.api.sdk.queries.groups.GroupsGetMembersQueryWithFilter;
import com.vk.api.sdk.queries.groups.GroupsGetOnlineStatusQuery;
import com.vk.api.sdk.queries.groups.GroupsGetTagListQuery;
import com.vk.api.sdk.queries.groups.GroupsIsMemberQuery;
import com.vk.api.sdk.queries.groups.GroupsIsMemberQueryWithExtended;
import com.vk.api.sdk.queries.groups.GroupsIsMemberQueryWithUserIds;
import com.vk.api.sdk.queries.groups.GroupsIsMemberQueryWithUserIdsExtended;
import com.vk.api.sdk.queries.groups.GroupsSetCallbackSettingsQuery;
import com.vk.api.sdk.queries.groups.GroupsSetLongPollSettingsQuery;
import com.vk.api.sdk.queries.groups.GroupsSetSettingsQuery;
import com.vk.api.sdk.queries.groups.GroupsSetUserNoteQuery;
import com.vk.api.sdk.queries.groups.GroupsTagAddQuery;
import com.vk.api.sdk.queries.groups.GroupsTagBindQuery;
import com.vk.api.sdk.queries.groups.GroupsTagDeleteQuery;
import com.vk.api.sdk.queries.groups.GroupsTagUpdateQuery;

public class GroupsVkAPI {
	
	private final VkAPIClient api;
	private final Groups internal;
	
	public GroupsVkAPI(VkAPIClient api) {
		this.api = api;
		this.internal = api.client().groups();
	}
	
	public VkAPIClient api() {
		return this.api;
	}
	
	public Groups internal() {
		return this.internal;
	}
	
	public GroupsAddAddressQuery addAddress() {
		return this.api.actor().map(
				group -> this.internal.addAddress(group),
				user -> this.internal.addAddress(user));
	}
	
	public GroupsAddAddressQuery addAddress(
			final Long groupId, final String title,
			final String address, final Integer cityId,
			final Number latitude, final Number longitude) {
		return this.api.actor().map(
				group -> this.internal.addAddress(group, groupId, title, address, cityId, latitude, longitude),
				user -> this.internal.addAddress(user, groupId, title, address, cityId, latitude, longitude));
	}
	
	public GroupsAddCallbackServerQuery addCallbackServer() {
		return this.api.actor().map(
				group -> this.internal.addCallbackServer(group),
				user -> this.internal.addCallbackServer(user));
	}
	
	public GroupsAddCallbackServerQuery addCallbackServer(
			final Long groupId, final String url, final String title) {
		return this.api.actor().map(
				group -> this.internal.addCallbackServer(group, groupId, url, title),
				user -> this.internal.addCallbackServer(user, groupId, url, title));
	}
	
	public GroupsDeleteAddressQuery deleteAddress() {
		return this.api.actor().map(
				group -> this.internal.deleteAddress(group),
				user -> this.internal.deleteAddress(user));
	}
	
	public GroupsDeleteAddressQuery deleteAddress(final Long groupId, final Integer addressId) {
		return this.api.actor().map(
				group -> this.internal.deleteAddress(group, groupId, addressId),
				user -> this.internal.deleteAddress(user, groupId, addressId));
	}
	
	public GroupsDeleteCallbackServerQuery deleteCallbackServer() {
		return this.api.actor().map(
				group -> this.internal.deleteCallbackServer(group),
				user -> this.internal.deleteCallbackServer(user));
	}
	
	public GroupsDeleteCallbackServerQuery deleteCallbackServer(final Long groupId, final Integer addressId) {
		return this.api.actor().map(
				group -> this.internal.deleteCallbackServer(group, groupId, addressId),
				user -> this.internal.deleteCallbackServer(user, groupId, addressId));
	}
	
	public GroupsDisableOnlineQuery disableOnline() {
		return this.api.actor().map(
				group -> this.internal.disableOnline(group),
				user -> this.internal.disableOnline(user));
	}
	
	public GroupsDisableOnlineQuery disableOnline(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.disableOnline(group, groupId),
				user -> this.internal.disableOnline(user, groupId));
	}
	
	public GroupsEditQuery edit() {
		return this.api.actor().map(
				group -> this.internal.edit(group),
				user -> this.internal.edit(user));
	}
	
	public GroupsEditQuery edit(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.edit(group, groupId),
				user -> this.internal.edit(user, groupId));
	}
	
	public GroupsEditAddressQuery editAddress() {
		return this.api.actor().map(
				group -> this.internal.editAddress(group),
				user -> this.internal.editAddress(user));
	}
	
	public GroupsEditAddressQuery editAddress(final Long groupId, final Integer addressId) {
		return this.api.actor().map(
				group -> this.internal.editAddress(group, groupId, addressId),
				user -> this.internal.editAddress(user, groupId, addressId));
	}
	
	public GroupsEditCallbackServerQuery editCallbackServer() {
		return this.api.actor().map(
				group -> this.internal.editCallbackServer(group),
				user -> this.internal.editCallbackServer(user));
	}
	
	public GroupsEditCallbackServerQuery editCallbackServer(
			final Long groupId, final Integer serverId, final String url, final String title) {
		return this.api.actor().map(
				group -> this.internal.editCallbackServer(group, groupId, serverId, url, title),
				user -> this.internal.editCallbackServer(user, groupId, serverId, url, title));
	}
	
	public GroupsEnableOnlineQuery enableOnline() {
		return this.api.actor().map(
				group -> this.internal.enableOnline(group),
				user -> this.internal.enableOnline(user));
	}
	
	public GroupsEnableOnlineQuery enableOnline(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.enableOnline(group, groupId),
				user -> this.internal.enableOnline(user, groupId));
	}
	
	public GroupsGetBannedQuery getBanned() {
		return this.api.actor().map(
				group -> this.internal.getBanned(group),
				user -> this.internal.getBanned(user));
	}
	
	public GroupsGetBannedQuery getBanned(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.getBanned(group, groupId),
				user -> this.internal.getBanned(user, groupId));
	}
	
	public GroupsGetByIdQueryWithObject getByIdObject() {
		return this.api.actor().map(
				group -> this.internal.getByIdObject(group),
				user -> this.internal.getByIdObject(user));
		
	}
	
	public GroupsGetCallbackConfirmationCodeQuery getCallbackConfirmationCode() {
		return this.api.actor().map(
				group -> this.internal.getCallbackConfirmationCode(group),
				user -> this.internal.getCallbackConfirmationCode(user));
	}
	
	public GroupsGetCallbackConfirmationCodeQuery getCallbackConfirmationCode(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.getCallbackConfirmationCode(group, groupId),
				user -> this.internal.getCallbackConfirmationCode(user, groupId));
	}
	
	public GroupsGetCallbackServersQuery getCallbackServers() {
		return this.api.actor().map(
				group -> this.internal.getCallbackServers(group),
				user -> this.internal.getCallbackServers(user));
	}
	
	public GroupsGetCallbackServersQuery getCallbackServers(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.getCallbackServers(group, groupId),
				user -> this.internal.getCallbackServers(user, groupId));
	}
	
	public GroupsGetCallbackSettingsQuery getCallbackSettings() {
		return this.api.actor().map(
				group -> this.internal.getCallbackSettings(group),
				user -> this.internal.getCallbackSettings(user));
	}
	
	public GroupsGetCallbackSettingsQuery getCallbackSettings(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.getCallbackSettings(group, groupId),
				user -> this.internal.getCallbackSettings(user, groupId));
	}
	
	public GroupsGetLongPollServerQuery getLongPollServer() {
		return this.api.actor().map(
				group -> this.internal.getLongPollServer(group),
				user -> this.internal.getLongPollServer(user));
	}
	
	public GroupsGetLongPollServerQuery getLongPollServer(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.getLongPollServer(group, groupId),
				user -> this.internal.getLongPollServer(user, groupId));
	}
	
	public GroupsGetLongPollSettingsQuery getLongPollSettings() {
		return this.api.actor().map(
				group -> this.internal.getLongPollSettings(group),
				user -> this.internal.getLongPollSettings(user));
	}
	
	public GroupsGetLongPollSettingsQuery getLongPollSettings(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.getLongPollSettings(group, groupId),
				user -> this.internal.getLongPollSettings(user, groupId));
	}
	
	public GroupsGetMembersQuery getMembers() {
		return this.api.actor().map(
				group -> this.internal.getMembers(group),
				user -> this.internal.getMembers(user));
	}
	
	public GroupsGetMembersQueryWithFields getMembersWithFields(final Fields... fields) {
		return this.api.actor().map(
				group -> this.internal.getMembersWithFields(group, fields),
				user -> this.internal.getMembersWithFields(user, fields));
	}
	
	public GroupsGetMembersQueryWithFilter getMembersWithFilter(final GetMembersFilter filter) {
		return this.api.actor().map(
				group -> this.internal.getMembersWithFilter(group, filter),
				user -> this.internal.getMembersWithFilter(user, filter));
	}
	
	public GroupsGetOnlineStatusQuery getOnlineStatus() {
		return this.api.actor().map(
				group -> this.internal.getOnlineStatus(group),
				user -> this.internal.getOnlineStatus(user));
	}
	
	public GroupsGetOnlineStatusQuery getOnlineStatus(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.getOnlineStatus(group, groupId),
				user -> this.internal.getOnlineStatus(user, groupId));
	}
	
	public GroupsGetTagListQuery getTagList() {
		return this.api.actor().map(
				group -> this.internal.getTagList(group),
				user -> this.internal.getTagList(user));
	}
	
	public GroupsGetTagListQuery getTagList(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.getTagList(group, groupId),
				user -> this.internal.getTagList(user, groupId));
	}
	
	public GroupsIsMemberQuery isMember() {
		return this.api.actor().map(
				group -> this.internal.isMember(group),
				user -> this.internal.isMember(user));
	}
	
	public GroupsIsMemberQuery isMember(final String groupId) {
		return this.api.actor().map(
				group -> this.internal.isMember(group, groupId),
				user -> this.internal.isMember(user, groupId));
	}
	
	public GroupsIsMemberQueryWithExtended isMemberExtended(final String groupId) {
		return this.api.actor().map(
				group -> this.internal.isMemberExtended(group, groupId),
				user -> this.internal.isMemberExtended(user, groupId));
	}
	
	public GroupsIsMemberQueryWithUserIdsExtended isMemberUserIdsExtended(final String groupId) {
		return this.api.actor().map(
				group -> this.internal.isMemberUserIdsExtended(group, groupId),
				user -> this.internal.isMemberUserIdsExtended(user, groupId));
	}
	
	public GroupsIsMemberQueryWithUserIds isMemberWithUserIds(final String groupId) {
		return this.api.actor().map(
				group -> this.internal.isMemberWithUserIds(group, groupId),
				user -> this.internal.isMemberWithUserIds(user, groupId));
	}
	
	public GroupsSetCallbackSettingsQuery setCallbackSettings() {
		return this.api.actor().map(
				group -> this.internal.setCallbackSettings(group),
				user -> this.internal.setCallbackSettings(user));
	}
	
	public GroupsSetCallbackSettingsQuery setCallbackSettings(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.setCallbackSettings(group, groupId),
				user -> this.internal.setCallbackSettings(user, groupId));
	}
	
	public GroupsSetLongPollSettingsQuery setLongPollSettings() {
		return this.api.actor().map(
				group -> this.internal.setLongPollSettings(group),
				user -> this.internal.setLongPollSettings(user));
	}
	
	public GroupsSetLongPollSettingsQuery setLongPollSettings(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.setLongPollSettings(group, groupId),
				user -> this.internal.setLongPollSettings(user, groupId));
	}
	
	public GroupsSetSettingsQuery setSettings() {
		return this.api.actor().map(
				group -> this.internal.setSettings(group),
				user -> this.internal.setSettings(user));
	}
	
	public GroupsSetSettingsQuery setSettings(final Long groupId) {
		return this.api.actor().map(
				group -> this.internal.setSettings(group, groupId),
				user -> this.internal.setSettings(user, groupId));
	}
	
	public GroupsSetUserNoteQuery setUserNote() {
		return this.api.actor().map(
				group -> this.internal.setUserNote(group),
				user -> this.internal.setUserNote(user));
	}
	
	public GroupsSetUserNoteQuery setUserNote(final Long groupId, final Long userId) {
		return this.api.actor().map(
				group -> this.internal.setUserNote(group, groupId, userId),
				user -> this.internal.setUserNote(user, groupId, userId));
	}
	
	public GroupsTagAddQuery tagAdd() {
		return this.api.actor().map(
				group -> this.internal.tagAdd(group),
				user -> this.internal.tagAdd(user));
	}
	
	public GroupsTagAddQuery tagAdd(final Long groupId, final String tagName) {
		return this.api.actor().map(
				group -> this.internal.tagAdd(group, groupId, tagName),
				user -> this.internal.tagAdd(user, groupId, tagName));
	}
	
	public GroupsTagBindQuery tagBind() {
		return this.api.actor().map(
				group -> this.internal.tagBind(group),
				user -> this.internal.tagBind(user));
	}
	
	public GroupsTagBindQuery tagBind(
			final Long groupId, final Integer tagId, final Long userId, final TagBindAct act) {
		return this.api.actor().map(
				group -> this.internal.tagBind(group, groupId, tagId, userId, act),
				user -> this.internal.tagBind(user, groupId, tagId, userId, act));
	}
	
	public GroupsTagDeleteQuery tagDelete() {
		return this.api.actor().map(
				group -> this.internal.tagDelete(group),
				user -> this.internal.tagDelete(user));
	}
	
	public GroupsTagDeleteQuery tagDelete(final Long groupId, final Integer tagId) {
		return this.api.actor().map(
				group -> this.internal.tagDelete(group, groupId, tagId),
				user -> this.internal.tagDelete(user, groupId, tagId));
	}
	
	public GroupsTagUpdateQuery tagUpdate() {
		return this.api.actor().map(
				group -> this.internal.tagUpdate(group),
				user -> this.internal.tagUpdate(user));
	}
	
	public GroupsTagUpdateQuery tagUpdate(final Long groupId, final Integer tagId, final String tagName) {
		return this.api.actor().map(
				group -> this.internal.tagUpdate(group, groupId, tagId, tagName),
				user -> this.internal.tagUpdate(user, groupId, tagId, tagName));
	}
}
