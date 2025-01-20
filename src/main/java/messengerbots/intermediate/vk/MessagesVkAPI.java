package messengerbots.intermediate.vk;

import java.util.List;

import com.vk.api.sdk.actions.Messages;
import com.vk.api.sdk.queries.messages.MessagesCreateChatQueryWithWithpeerids;
import com.vk.api.sdk.queries.messages.MessagesDeleteChatPhotoQuery;
import com.vk.api.sdk.queries.messages.MessagesDeleteConversationQuery;
import com.vk.api.sdk.queries.messages.MessagesDeleteQueryWithFull;
import com.vk.api.sdk.queries.messages.MessagesDeleteReactionQuery;
import com.vk.api.sdk.queries.messages.MessagesEditChatQuery;
import com.vk.api.sdk.queries.messages.MessagesEditQuery;
import com.vk.api.sdk.queries.messages.MessagesGetByConversationMessageIdQuery;
import com.vk.api.sdk.queries.messages.MessagesGetByConversationMessageIdQueryWithExtended;
import com.vk.api.sdk.queries.messages.MessagesGetByIdQuery;
import com.vk.api.sdk.queries.messages.MessagesGetByIdQueryWithExtended;
import com.vk.api.sdk.queries.messages.MessagesGetConversationMembersQuery;
import com.vk.api.sdk.queries.messages.MessagesGetConversationsByIdQuery;
import com.vk.api.sdk.queries.messages.MessagesGetConversationsByIdQueryWithExtended;
import com.vk.api.sdk.queries.messages.MessagesGetConversationsQuery;
import com.vk.api.sdk.queries.messages.MessagesGetHistoryAttachmentsQuery;
import com.vk.api.sdk.queries.messages.MessagesGetHistoryQuery;
import com.vk.api.sdk.queries.messages.MessagesGetHistoryQueryWithExtended;
import com.vk.api.sdk.queries.messages.MessagesGetImportantMessagesQuery;
import com.vk.api.sdk.queries.messages.MessagesGetImportantMessagesQueryWithExtended;
import com.vk.api.sdk.queries.messages.MessagesGetInviteLinkQuery;
import com.vk.api.sdk.queries.messages.MessagesGetInviteLinkQueryWithByOwner;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollServerQuery;
import com.vk.api.sdk.queries.messages.MessagesGetMessagesReactionsQuery;
import com.vk.api.sdk.queries.messages.MessagesGetReactedPeersQuery;
import com.vk.api.sdk.queries.messages.MessagesIsMessagesFromGroupAllowedQuery;
import com.vk.api.sdk.queries.messages.MessagesMarkAsAnsweredConversationQuery;
import com.vk.api.sdk.queries.messages.MessagesMarkAsImportantConversationQuery;
import com.vk.api.sdk.queries.messages.MessagesMarkAsReadQuery;
import com.vk.api.sdk.queries.messages.MessagesPinQuery;
import com.vk.api.sdk.queries.messages.MessagesRemoveChatUserQuery;
import com.vk.api.sdk.queries.messages.MessagesRestoreQuery;
import com.vk.api.sdk.queries.messages.MessagesSearchConversationsQuery;
import com.vk.api.sdk.queries.messages.MessagesSearchConversationsQueryWithExtended;
import com.vk.api.sdk.queries.messages.MessagesSearchQuery;
import com.vk.api.sdk.queries.messages.MessagesSearchQueryWithExtended;
import com.vk.api.sdk.queries.messages.MessagesSendQueryWithDeprecated;
import com.vk.api.sdk.queries.messages.MessagesSendQueryWithUserIds;
import com.vk.api.sdk.queries.messages.MessagesSendReactionQuery;
import com.vk.api.sdk.queries.messages.MessagesSetActivityQuery;
import com.vk.api.sdk.queries.messages.MessagesSetChatPhotoQuery;
import com.vk.api.sdk.queries.messages.MessagesUnpinQuery;

public class MessagesVkAPI {
	
	private final VkAPIClient api;
	private final Messages internal;
	
	public MessagesVkAPI(VkAPIClient api) {
		this.api = api;
		this.internal = api.client().messages();
	}
	
	public VkAPIClient api() {
		return this.api;
	}
	
	public Messages internal() {
		return this.internal;
	}
	
	public MessagesCreateChatQueryWithWithpeerids createChatWithpeerids() {
		return this.api.actor().map(
				group -> this.internal.createChatWithpeerids(group),
				user -> this.internal.createChatWithpeerids(user));
	}
	
	public MessagesDeleteChatPhotoQuery deleteChatPhoto() {
		return this.api.actor().map(
				group -> this.internal.deleteChatPhoto(group),
				user -> this.internal.deleteChatPhoto(user));
	}
	
	public MessagesDeleteChatPhotoQuery deleteChatPhoto(final Integer chatId) {
		return this.api.actor().map(
				group -> this.internal.deleteChatPhoto(group, chatId),
				user -> this.internal.deleteChatPhoto(user, chatId));
	}
	
	public MessagesDeleteConversationQuery deleteConversation() {
		return this.api.actor().map(
				group -> this.internal.deleteConversation(group),
				user -> this.internal.deleteConversation(user));
	}
	
	public MessagesDeleteQueryWithFull deleteFull() {
		return this.api.actor().map(
				group -> this.internal.deleteFull(group),
				user -> this.internal.deleteFull(user));
	}
	
	public MessagesDeleteReactionQuery deleteReaction() {
		return this.api.actor().map(
				group -> this.internal.deleteReaction(group),
				user -> this.internal.deleteReaction(user));
	}
	
	public MessagesDeleteReactionQuery deleteReaction(final Long peerId, final Integer cmid) {
		return this.api.actor().map(
				group -> this.internal.deleteReaction(group, peerId, cmid),
				user -> this.internal.deleteReaction(user, peerId, cmid));
	}
	
	public MessagesEditQuery edit() {
		return this.api.actor().map(
				group -> this.internal.edit(group),
				user -> this.internal.edit(user));
	}
	
	public MessagesEditQuery edit(final Long peerId) {
		return this.api.actor().map(
				group -> this.internal.edit(group, peerId),
				user -> this.internal.edit(user, peerId));
	}
	
	public MessagesEditChatQuery editChat() {
		return this.api.actor().map(
				group -> this.internal.editChat(group),
				user -> this.internal.editChat(user));
	}
	
	public MessagesEditChatQuery editChat(final Integer chatId) {
		return this.api.actor().map(
				group -> this.internal.editChat(group, chatId),
				user -> this.internal.editChat(user, chatId));
	}
	
	public MessagesGetByConversationMessageIdQuery getByConversationMessageId() {
		return this.api.actor().map(
				group -> this.internal.getByConversationMessageId(group),
				user -> this.internal.getByConversationMessageId(user));
	}
	
	public MessagesGetByConversationMessageIdQuery getByConversationMessageId(final Long peerId, final Integer... conversationMessageIds) {
		return this.api.actor().map(
				group -> this.internal.getByConversationMessageId(group, peerId, conversationMessageIds),
				user -> this.internal.getByConversationMessageId(user, peerId, conversationMessageIds));
	}
	
	public MessagesGetByConversationMessageIdQuery getByConversationMessageId(final Long peerId, final List<Integer> conversationMessageIds) {
		return this.api.actor().map(
				group -> this.internal.getByConversationMessageId(group, peerId, conversationMessageIds),
				user -> this.internal.getByConversationMessageId(user, peerId, conversationMessageIds));
	}
	
	public MessagesGetByConversationMessageIdQueryWithExtended getByConversationMessageIdExtended(final Long peerId, final Integer... conversationMessageIds) {
		return this.api.actor().map(
				group -> this.internal.getByConversationMessageIdExtended(group, peerId, conversationMessageIds),
				user -> this.internal.getByConversationMessageIdExtended(user, peerId, conversationMessageIds));
	}
	
	public MessagesGetByConversationMessageIdQueryWithExtended getByConversationMessageIdExtended(final Long peerId, final List<Integer> conversationMessageIds) {
		return this.api.actor().map(
				group -> this.internal.getByConversationMessageIdExtended(group, peerId, conversationMessageIds),
				user -> this.internal.getByConversationMessageIdExtended(user, peerId, conversationMessageIds));
	}
	
	public MessagesGetByIdQuery getById() {
		return this.api.actor().map(
				group -> this.internal.getById(group),
				user -> this.internal.getById(user));
	}
	
	public MessagesGetByIdQueryWithExtended getByIdExtended() {
		return this.api.actor().map(
				group -> this.internal.getByIdExtended(group),
				user -> this.internal.getByIdExtended(user));
	}
	
	public MessagesGetConversationMembersQuery getConversationMembers() {
		return this.api.actor().map(
				group -> this.internal.getConversationMembers(group),
				user -> this.internal.getConversationMembers(user));
	}
	
	public MessagesGetConversationMembersQuery getConversationMembers(final Long peerId) {
		return this.api.actor().map(
				group -> this.internal.getConversationMembers(group, peerId),
				user -> this.internal.getConversationMembers(user, peerId));
	}
	
	public MessagesGetConversationsQuery getConversations() {
		return this.api.actor().map(
				group -> this.internal.getConversations(group),
				user -> this.internal.getConversations(user));
	}
	
	public MessagesGetConversationsByIdQuery getConversationsById() {
		return this.api.actor().map(
				group -> this.internal.getConversationsById(group),
				user -> this.internal.getConversationsById(user));
	}
	
	public MessagesGetConversationsByIdQuery getConversationsById(final Long... peerIds) {
		return this.api.actor().map(
				group -> this.internal.getConversationsById(group, peerIds),
				user -> this.internal.getConversationsById(user, peerIds));
	}
	
	public MessagesGetConversationsByIdQuery getConversationsById(final List<Long> peerIds) {
		return this.api.actor().map(
				group -> this.internal.getConversationsById(group, peerIds),
				user -> this.internal.getConversationsById(user, peerIds));
	}
	
	public MessagesGetConversationsByIdQueryWithExtended getConversationsByIdExtended(final Long... peerIds) {
		return this.api.actor().map(
				group -> this.internal.getConversationsByIdExtended(group, peerIds),
				user -> this.internal.getConversationsByIdExtended(user, peerIds));
	}
	
	public MessagesGetConversationsByIdQueryWithExtended getConversationsByIdExtended(final List<Long> peerIds) {
		return this.api.actor().map(
				group -> this.internal.getConversationsByIdExtended(group, peerIds),
				user -> this.internal.getConversationsByIdExtended(user, peerIds));
	}
	
	public MessagesGetHistoryQuery getHistory() {
		return this.api.actor().map(
				group -> this.internal.getHistory(group),
				user -> this.internal.getHistory(user));
	}
	
	public MessagesGetHistoryAttachmentsQuery getHistoryAttachments() {
		return this.api.actor().map(
				group -> this.internal.getHistoryAttachments(group),
				user -> this.internal.getHistoryAttachments(user));
	}
	
	public MessagesGetHistoryQueryWithExtended getHistoryExtended() {
		return this.api.actor().map(
				group -> this.internal.getHistoryExtended(group),
				user -> this.internal.getHistoryExtended(user));
	}
	
	public MessagesGetImportantMessagesQuery getImportantMessages() {
		return this.api.actor().map(
				group -> this.internal.getImportantMessages(group),
				user -> this.internal.getImportantMessages(user));
	}
	
	public MessagesGetImportantMessagesQueryWithExtended getImportantMessagesExtended() {
		return this.api.actor().map(
				group -> this.internal.getImportantMessagesExtended(group),
				user -> this.internal.getImportantMessagesExtended(user));
	}
	
	public MessagesGetInviteLinkQuery getInviteLink() {
		return this.api.actor().map(
				group -> this.internal.getInviteLink(group),
				user -> this.internal.getInviteLink(user));
	}
	
	public MessagesGetInviteLinkQuery getInviteLink(final Long peerId) {
		return this.api.actor().map(
				group -> this.internal.getInviteLink(group, peerId),
				user -> this.internal.getInviteLink(user, peerId));
	}
	
	public MessagesGetInviteLinkQueryWithByOwner getInviteLinkByOwner(final Long peerId) {
		return this.api.actor().map(
				group -> this.internal.getInviteLinkByOwner(group, peerId),
				user -> this.internal.getInviteLinkByOwner(user, peerId));
	}
	
	public MessagesGetLongPollHistoryQuery getLongPollHistory() {
		return this.api.actor().map(
				group -> this.internal.getLongPollHistory(group),
				user -> this.internal.getLongPollHistory(user));
	}
	
	public MessagesGetLongPollServerQuery getLongPollServer() {
		return this.api.actor().map(
				group -> this.internal.getLongPollServer(group),
				user -> this.internal.getLongPollServer(user));
	}
	
	public MessagesGetMessagesReactionsQuery getMessagesReactions() {
		return this.api.actor().map(
				group -> this.internal.getMessagesReactions(group),
				user -> this.internal.getMessagesReactions(user));
	}
	
	public MessagesGetMessagesReactionsQuery getMessagesReactions(final Long peerId, final Integer... cmids) {
		return this.api.actor().map(
				group -> this.internal.getMessagesReactions(group, peerId, cmids),
				user -> this.internal.getMessagesReactions(user, peerId, cmids));
	}
	
	public MessagesGetMessagesReactionsQuery getMessagesReactions(final Long peerId, final List<Integer> cmids) {
		return this.api.actor().map(
				group -> this.internal.getMessagesReactions(group, peerId, cmids),
				user -> this.internal.getMessagesReactions(user, peerId, cmids));
	}
	
	public MessagesGetReactedPeersQuery getReactedPeers() {
		return this.api.actor().map(
				group -> this.internal.getReactedPeers(group),
				user -> this.internal.getReactedPeers(user));
	}
	
	public MessagesGetReactedPeersQuery getReactedPeers(final Long peerId, final Integer cmid) {
		return this.api.actor().map(
				group -> this.internal.getReactedPeers(group, peerId, cmid),
				user -> this.internal.getReactedPeers(user, peerId, cmid));
	}
	
	public MessagesIsMessagesFromGroupAllowedQuery isMessagesFromGroupAllowed() {
		return this.api.actor().map(
				group -> this.internal.isMessagesFromGroupAllowed(group),
				user -> this.internal.isMessagesFromGroupAllowed(user));
	}
	
	public MessagesIsMessagesFromGroupAllowedQuery isMessagesFromGroupAllowed(final Long groupId, final Long userId) {
		return this.api.actor().map(
				group -> this.internal.isMessagesFromGroupAllowed(group, groupId, userId),
				user -> this.internal.isMessagesFromGroupAllowed(user, groupId, userId));
	}
	
	public MessagesMarkAsAnsweredConversationQuery markAsAnsweredConversation() {
		return this.api.actor().map(
				group -> this.internal.markAsAnsweredConversation(group),
				user -> this.internal.markAsAnsweredConversation(user));
	}
	
	public MessagesMarkAsAnsweredConversationQuery markAsAnsweredConversation(final Long peerId) {
		return this.api.actor().map(
				group -> this.internal.markAsAnsweredConversation(group, peerId),
				user -> this.internal.markAsAnsweredConversation(user, peerId));
	}
	
	public MessagesMarkAsImportantConversationQuery markAsImportantConversation() {
		return this.api.actor().map(
				group -> this.internal.markAsImportantConversation(group),
				user -> this.internal.markAsImportantConversation(user));
	}
	
	public MessagesMarkAsImportantConversationQuery markAsImportantConversation(final Long peerId) {
		return this.api.actor().map(
				group -> this.internal.markAsImportantConversation(group, peerId),
				user -> this.internal.markAsImportantConversation(user, peerId));
	}
	
	public MessagesMarkAsReadQuery markAsRead() {
		return this.api.actor().map(
				group -> this.internal.markAsRead(group),
				user -> this.internal.markAsRead(user));
	}
	
	public MessagesPinQuery pin() {
		return this.api.actor().map(
				group -> this.internal.pin(group),
				user -> this.internal.pin(user));
	}
	
	public MessagesPinQuery pin(final Long peerId) {
		return this.api.actor().map(
				group -> this.internal.pin(group, peerId),
				user -> this.internal.pin(user, peerId));
	}
	
	public MessagesUnpinQuery unpin() {
		return this.api.actor().map(
				group -> this.internal.unpin(group),
				user -> this.internal.unpin(user));
	}
	
	public MessagesUnpinQuery unpin(final Long peerId) {
		return this.api.actor().map(
				group -> this.internal.unpin(group, peerId),
				user -> this.internal.unpin(user, peerId));
	}
	
	public MessagesRemoveChatUserQuery removeChatUser() {
		return this.api.actor().map(
				group -> this.internal.removeChatUser(group),
				user -> this.internal.removeChatUser(user));
	}
	
	public MessagesRemoveChatUserQuery removeChatUser(final Integer chatId) {
		return this.api.actor().map(
				group -> this.internal.removeChatUser(group, chatId),
				user -> this.internal.removeChatUser(user, chatId));
	}
	
	public MessagesRestoreQuery restore() {
		return this.api.actor().map(
				group -> this.internal.restore(group),
				user -> this.internal.restore(user));
	}
	
	public MessagesSearchQuery search() {
		return this.api.actor().map(
				group -> this.internal.search(group),
				user -> this.internal.search(user));
	}
	
	public MessagesSearchQueryWithExtended searchExtended() {
		return this.api.actor().map(
				group -> this.internal.searchExtended(group),
				user -> this.internal.searchExtended(user));
	}
	
	public MessagesSearchConversationsQuery searchConversations() {
		return this.api.actor().map(
				group -> this.internal.searchConversations(group),
				user -> this.internal.searchConversations(user));
	}
	
	public MessagesSearchConversationsQueryWithExtended searchConversationsExtended() {
		return this.api.actor().map(
				group -> this.internal.searchConversationsExtended(group),
				user -> this.internal.searchConversationsExtended(user));
	}
	
	public MessagesSendQueryWithDeprecated sendDeprecated() {
		return this.api.actor().map(
				group -> this.internal.sendDeprecated(group),
				user -> this.internal.sendDeprecated(user));
	}
	
	public MessagesSendReactionQuery sendReaction() {
		return this.api.actor().map(
				group -> this.internal.sendReaction(group),
				user -> this.internal.sendReaction(user));
	}
	
	public MessagesSendReactionQuery sendReaction(final Long peerId, final Integer cmid, final Integer reactionId) {
		return this.api.actor().map(
				group -> this.internal.sendReaction(group, peerId, cmid, reactionId),
				user -> this.internal.sendReaction(user, peerId, cmid, reactionId));
	}
	
	public MessagesSendQueryWithUserIds sendUserIds() {
		return this.api.actor().map(
				group -> this.internal.sendUserIds(group),
				user -> this.internal.sendUserIds(user));
	}
	
	public MessagesSetActivityQuery setActivity() {
		return this.api.actor().map(
				group -> this.internal.setActivity(group),
				user -> this.internal.setActivity(user));
	}
	
	public MessagesSetChatPhotoQuery setChatPhoto() {
		return this.api.actor().map(
				group -> this.internal.setChatPhoto(group),
				user -> this.internal.setChatPhoto(user));
	}
	
	public MessagesSetChatPhotoQuery setChatPhoto(final String file) {
		return this.api.actor().map(
				group -> this.internal.setChatPhoto(group, file),
				user -> this.internal.setChatPhoto(user, file));
	}
}
