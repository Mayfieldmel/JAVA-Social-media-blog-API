package Controller;

import java.util.ArrayList;
import java.util.Map;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.AccountServiceImpl;
import Service.MessageService;
import Service.MessageServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class SocialMediaController {
    // state 
    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountServiceImpl();
        this.messageService = new MessageServiceImpl();
    }


    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);

        // account handler paths
        app.post("/register", this::addAccountHandler);
        app.post("/login", this::loginAccountHandler);
        app.get("/accounts/{account_id}", this::getAccountByIdHandler);
        app.get("/accounts", this::getAllAccountsHandler);

        // message handler paths
        app.post("/messages", this::addMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUserHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        return app;
    }

   
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    // create account
    private void addAccountHandler(Context ctx) {
        
        // get request information
        Account account = ctx.bodyAsClass(Account.class);
        // call service method
        Account accountAdded = accountService.addAccount(account);
        // send result to client
        if(accountAdded != null) {
            ctx.json(accountAdded);
        } else {
            ctx.status(400);
        }
    }

    // login to account
    private void loginAccountHandler(Context ctx) {
        // get request information
        Account account = ctx.bodyAsClass(Account.class);
        // call service method
        Account accountLogin = accountService.getAccount(account.getUsername(), account.getPassword());
        // send result to client
        if(accountLogin != null) {
            ctx.json(accountLogin);
        } else {
            ctx.status(401);
        }

    }

    // get account by id
    private void getAccountByIdHandler(Context ctx) {
        // get request information
        String idString = ctx.pathParam("account_id");
        int id = Integer.parseInt(idString);
        // call service method
        Account account = accountService.getAccountById(id);
        // send result to client
        if(account != null) {
            ctx.json(account);
        } else {
            ctx.status(400);
        }
    }

    // get all accounts
    private void getAllAccountsHandler(Context ctx) {
        // call service method
        ArrayList<Account> accounts = accountService.getAllAccounts();
        // send result to client
        if(accounts != null) {
            ctx.json(accounts);
        } else {
            ctx.status(400);
        }
    }

    // create message
    private void addMessageHandler(Context ctx) {
        // get request information
        Message message = ctx.bodyAsClass(Message.class);
        // call service method
        Message messageAdded = messageService.addMessage(message);
        // send result to client
        if (messageAdded != null) {
            ctx.json(messageAdded);
        } else {
            ctx.status(400);
        }
    }

    // get all messages
    private void getAllMessagesHandler(Context ctx) {
        // call service method
        ArrayList<Message> messages = messageService.getAllMessages();
        // send result to client
        if(messages != null) {
            ctx.json(messages);
        } else {
            ctx.status(500);
        }
    }

    // get message by id
    private void getMessageByIdHandler(Context ctx) {
        // get request information
        String idString = ctx.pathParam("message_id");
        int id = Integer.parseInt(idString);
        // call service method
        Message message = messageService.getMessageById(id);
        // send result to client
        if(message != null) {
            ctx.json(message);
        } else {
            ctx.status(200);
            ctx.json("");
        }
    }

    // get all user messages
    private void getAllMessagesByUserHandler(Context ctx) {
        // get request information
        String idString = ctx.pathParam("account_id");
        int id = Integer.parseInt(idString);
        // call service metod
        ArrayList<Message> userMessages = messageService.getAllMessagesByUser(id);
        // send results to client
        if(userMessages != null) {
            ctx.json(userMessages);
        } else {
            ctx.status(500);
        }
    }

    // update message text
    private void updateMessageHandler(Context ctx) {
        // get request information
        Map<String, String> messageMap = ctx.bodyAsClass(Map.class);
        String messageText = messageMap.get("message_text");
        String idString = ctx.pathParam("message_id");
        int id = Integer.parseInt(idString);
        // call service method
        Message message = messageService.updateMessage(id, messageText);
        // send result to client
        if(message != null) {
            ctx.json(message);
        } else {
            ctx.status(400);
        }
    }

    // delete message
    private void deleteMessageHandler(Context ctx) {
        // get request information
        String idString = ctx.pathParam("message_id");
        int id = Integer.parseInt(idString);
        // check if message to be deleted exists
        Message deletedMessage = messageService.getMessageById(id);
        if(deletedMessage != null) {
             // call service method
            messageService.deleteMessage(id);
            // send result to client
            ctx.json(deletedMessage);
        } else {
            ctx.status(200);
            ctx.json("");
        }
    }
}