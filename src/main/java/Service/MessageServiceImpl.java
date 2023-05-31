package Service;

import java.util.ArrayList;
import java.util.Objects;

import DAO.MessageDAO;
import DAO.MessageDAOImpl;
import Model.Message;

public class MessageServiceImpl implements MessageService {
    // state
    private MessageDAO messageDAO;
    public MessageServiceImpl() {
        this.messageDAO = new MessageDAOImpl();
    }

    // create message
    @Override
    public Message addMessage(Message message) {
        // add || messageDAO.getAccountById(message.getPosted_by()).isNull
        if (message.getMessage_text().isBlank() || message.getMessage_text().length() >= 255) { 
        return null;
        } else  {
       return messageDAO.addMessage(message);
    } 
    }

    @Override
    public Message getMessageById(int id) {
       return messageDAO.getMessageById(id);
    }

    @Override
    public ArrayList<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    @Override
    public ArrayList<Message> getAllMessagesByUser(int id) {
        ArrayList<Message> allMessages = messageDAO.getAllMessages();
        ArrayList<Message> userMessages = new ArrayList<>();
        for (Message message : allMessages) {
            if(message.getPosted_by() == id) {
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    @Override
    public Message updateMessage(int id, String messageText) {
        Message message = messageDAO.getMessageById(id);
        if (messageText.isBlank() || Objects.isNull(message) || messageText.length() >= 255) { 
            return null;
        } else {
            return messageDAO.updateMessage(id, messageText);
        } 
       
    }

    @Override
    public Message deleteMessage(int id) {
        Message message = messageDAO.getMessageById(id);
        if(Objects.isNull(message)) {
            return null;
        } else {
        return messageDAO.deleteMessage(id);
    }
    }
    
}