package Controller;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        accountService = new AccountService();
        messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        // #1. Register a user
        app.post("/register", this::registrationHandler);

        // #2. Log in a user
        app.post("/login", this::loginHandler);

        // #3. Create a new message
        app.post("/messages", this::createMessageHandler);

        // #4. Read all messages
        app.get("/messages", this::getAllMessagesHandler);

        // #5. Read a specific message
        app.get("/messages/{message_id}", this::getMessageByMessageIdHandler);

        // #6. Delete a specific message
        app.delete("/messages/{message_id}", this::deleteMessageByMessageIdHandler);

        // #7. Update a specific message
        app.patch("/messages/{message_id}", this::updateMessageByMessageIdHandler);

        // #8. Read all messages by a specific user
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountIdHandler);

        return app;
    }

    private void registrationHandler(Context context) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Account accountToRegister = objectMapper.readValue(context.body(), Account.class);
        Account registeredAccount = accountService.register(accountToRegister);
        if (registeredAccount != null) {
            String registeredAccountAsString = objectMapper.writeValueAsString(registeredAccount);
            context.json(registeredAccountAsString);
        } else {
            context.status(400);
        }
    }

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Account accountToLogin = objectMapper.readValue(context.body(), Account.class);
        Account loggedInAccount = accountService.login(accountToLogin);
        if (loggedInAccount != null) {
            String loggedInAccountAsString = objectMapper.writeValueAsString(loggedInAccount);
            context.json(loggedInAccountAsString);
        } else {
            context.status(401);
        }
    }

    private void createMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Message messageToCreate = objectMapper.readValue(context.body(), Message.class);
        Message createdMessage = messageService.createMessage(messageToCreate);
        if (createdMessage != null) {
            String createdMessageAsString = objectMapper.writeValueAsString(createdMessage);
            context.json(createdMessageAsString);
        } else {
            context.status(400);
        }
    }

    private void getAllMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
    }

    private void getMessageByMessageIdHandler(Context context) {
        int messageId = Integer.parseInt(Objects.requireNonNull(context.pathParam("message_id")));
        Message message = messageService.getMessageByMessageId(messageId);
        if (message != null) {
            context.json(message);
        } else {
            context.status(200);
        }
    }

    private void deleteMessageByMessageIdHandler(Context context) {
        int messageId = Integer.parseInt(Objects.requireNonNull(context.pathParam("message_id")));
        Message message = messageService.deleteMessageByMessageId(messageId);
        if (message != null) {
            context.json(message);
        } else {
            context.status(200);
        }
    }

    private void updateMessageByMessageIdHandler(Context context) throws JsonProcessingException {
        int messageId = Integer.parseInt(Objects.requireNonNull(context.pathParam("message_id")));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(context.body());
        String messageText = jsonNode.get("message_text").asText();
        Message message = messageService.updateMessageByMessageId(messageId, messageText);
        if (message != null) {
            context.json(message);
        } else {
            context.status(400);
        }
    }

    private void getAllMessagesByAccountIdHandler(Context context) {
        int accountId = Integer.parseInt(Objects.requireNonNull(context.pathParam("account_id")));
        List<Message> messages = messageService.getAllMessagesByAccountId(accountId);
        context.json(messages);
    }
}
