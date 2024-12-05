package Controller;

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
        app.get("/messages/{message_id}", this::getMessageByMessageIDHandler);

        // #6. Delete a specific message
        app.delete("/messages/{message_id}", this::deleteMessageByMessageIDHandler);

        // #7. Update a specific message
        app.patch("/messages/{message_id}", this::updateMessageByMessageIDHandler);

        // #8. Read all messages by a specific user
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountIDHandler);

        return app;
    }

    /**
     * 
     * @param context the Javalin context object
     */
    private void registrationHandler(Context context) {

    }

    /**
     * 
     * @param context the Javalin context object
     */
    private void loginHandler(Context context) {

    }

    /**
     * 
     * @param context the Javalin context object
     */
    private void createMessageHandler(Context context) {

    }

    /**
     * 
     * @param context the Javalin context object
     */
    private void getAllMessagesHandler(Context context) {

    }

    /**
     * 
     * @param context the Javalin context object
     */
    private void getMessageByMessageIDHandler(Context context) {

    }

    /**
     * 
     * @param context the Javalin context object
     */
    private void deleteMessageByMessageIDHandler(Context context) {

    }

    /**
     * 
     * @param context the Javalin context object
     */
    private void updateMessageByMessageIDHandler(Context context) {

    }

    /**
     * 
     * @param context the Javalin context object
     */
    private void getAllMessagesByAccountIDHandler(Context context) {

    }
}