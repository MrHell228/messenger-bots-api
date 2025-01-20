package messengerbots.intermediate.tg;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.ChatJoinRequest;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.boost.ChatBoostRemoved;
import org.telegram.telegrambots.meta.api.objects.boost.ChatBoostUpdated;
import org.telegram.telegrambots.meta.api.objects.business.BusinessConnection;
import org.telegram.telegrambots.meta.api.objects.business.BusinessMessagesDeleted;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.payments.PaidMediaPurchased;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import org.telegram.telegrambots.meta.api.objects.reactions.MessageReactionCountUpdated;
import org.telegram.telegrambots.meta.api.objects.reactions.MessageReactionUpdated;

public interface TelegramSingleUpdateHandler {
	
	default void handle(final Update update) {
		if (update.hasMessage()) {
			this.onMessage(update.getMessage());
		} else if (update.hasEditedMessage()) {
			this.onMessageEdit(update.getEditedMessage());
		} else if (update.hasInlineQuery()) {
			this.onInlineQuery(update.getInlineQuery());
		} else if (update.hasChosenInlineQuery()) {
			this.onChosenInlineQuery(update.getChosenInlineQuery());
		} else if (update.hasCallbackQuery()) {
			this.onCallbackQuery(update.getCallbackQuery());
		} else if (update.hasChannelPost()) {
			this.onChannelPost(update.getChannelPost());
		} else if (update.hasEditedChannelPost()) {
			this.onChannelPostEdit(update.getEditedChannelPost());
		} else if (update.hasShippingQuery()) {
			this.onShippingQuery(update.getShippingQuery());
		} else if (update.hasPreCheckoutQuery()) {
			this.onPreCheckoutQuery(update.getPreCheckoutQuery());
		} else if (update.hasPoll()) {
			this.onPoll(update.getPoll());
		} else if (update.hasPollAnswer()) {
			this.onPollAnswer(update.getPollAnswer());
		} else if (update.hasMyChatMember()) {
			this.onMyChatMemberUpdated(update.getMyChatMember());
		} else if (update.hasChatMember()) {
			this.onChatMemberUpdated(update.getChatMember());
		} else if (update.hasChatJoinRequest()) {
			this.onChatJoinRequest(update.getChatJoinRequest());
		} else if (update.getMessageReaction() != null) {
			this.onMessageReaction(update.getMessageReaction());
		} else if (update.getMessageReactionCount() != null) {
			this.onMessageReactionCountUpdated(update.getMessageReactionCount());
		} else if (update.getChatBoost() != null) {
			this.onChatBoostUpdate(update.getChatBoost());
		} else if (update.getRemovedChatBoost() != null) {
			this.onChatBoostRemove(update.getRemovedChatBoost());
		} else if (update.hasBusinessConnection()) {
			this.onBusinessConnection(update.getBusinessConnection());
		} else if (update.hasBusinessMessage()) {
			this.onBusinessMessage(update.getBusinessMessage());
		} else if (update.hasEditedBusinessMessage()) {
			this.onBusinessMessageEdit(update.getEditedBuinessMessage());
		} else if (update.hasDeletedBusinessMessage()) {
			this.onBusinessMessageDelete(update.getDeletedBusinessMessages());
		} else if (update.hasPaidMediaPurchased()) {
			this.onPaidMediaPurchase(update.getPaidMediaPurchased());
		}
	}
	
	default void onMessage(Message message) {
	}
	
	default void onMessageEdit(Message message) {
	}
	
	default void onInlineQuery(InlineQuery query) {
	}
	
	default void onChosenInlineQuery(ChosenInlineQuery query) {
	}
	
	default void onCallbackQuery(CallbackQuery query) {
	}
	
	default void onChannelPost(Message message) {
	}
	
	default void onChannelPostEdit(Message message) {
	}
	
	default void onShippingQuery(ShippingQuery query) {
	}
	
	default void onPreCheckoutQuery(PreCheckoutQuery query) {
	}
	
	default void onPoll(Poll poll) {
	}
	
	default void onPollAnswer(PollAnswer pollAnswer) {
	}
	
	default void onMyChatMemberUpdated(ChatMemberUpdated chatMember) {
	}
	
	default void onChatMemberUpdated(ChatMemberUpdated chatMember) {
	}
	
	default void onChatJoinRequest(ChatJoinRequest request) {
	}
	
	default void onMessageReaction(MessageReactionUpdated reaction) {
	}
	
	default void onMessageReactionCountUpdated(MessageReactionCountUpdated reaction) {
	}
	
	default void onChatBoostUpdate(ChatBoostUpdated boost) {
	}
	
	default void onChatBoostRemove(ChatBoostRemoved boost) {
	}
	
	default void onBusinessConnection(BusinessConnection connection) {
	}
	
	default void onBusinessMessage(Message message) {
	}
	
	default void onBusinessMessageEdit(Message message) {
	}
	
	default void onBusinessMessageDelete(BusinessMessagesDeleted message) {
	}
	
	default void onPaidMediaPurchase(PaidMediaPurchased purchase) {
	}
}
