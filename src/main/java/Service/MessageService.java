package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message createMessage(Message message) {
        String messageText = message.getMessage_text();
        if (messageText.length() == 0) {
            return null;
        }
        if (messageText.length() > 255) {
            return null;
        }
        int postedBy = message.getPosted_by();
        AccountDAO accountDAO = new AccountDAO();
        if (accountDAO.getAccountByAccountId(postedBy) == null) {
            return null;
        }
        return messageDAO.createMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageByMessageId(int messageId) {
        return messageDAO.getMessageByMessageId(messageId);
    }

    public Message deleteMessageByMessageId(int messageId) {
        Message messageToDelete = messageDAO.getMessageByMessageId(messageId);
        if (messageToDelete == null) {
            return null;
        }
        int rowsUpdated = messageDAO.deleteMessageByMessageId(messageId);
        if (rowsUpdated == 0) {
            return null;
        }
        return messageToDelete;
    }

    public Message updateMessageByMessageId(int messageId, String messageText) {
        if (messageDAO.getMessageByMessageId(messageId) == null) {
            return null;
        }
        if (messageText.length() == 0) {
            return null;
        }
        if (messageText.length() > 255) {
            return null;
        }
        int rowsUpdated = messageDAO.updateMessageByMessageId(messageId, messageText);
        if (rowsUpdated == 0) {
            return null;
        }
        return messageDAO.getMessageByMessageId(messageId);
    }

    public List<Message> getAllMessagesByAccountId(int accountId) {
        return messageDAO.getAllMessagesByAccountId(accountId);
    }
}
