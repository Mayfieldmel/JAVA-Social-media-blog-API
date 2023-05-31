package Controller;

import java.util.ArrayList;

import Model.Account;
import Service.AccountService;
import Service.AccountServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    // state 
    private AccountService accountService;

    public SocialMediaController() {
        this.accountService = new AccountServiceImpl();
    }


    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/", this::addAccountHandler);
        app.post("/login", this::loginAccountHandler);
        app.get("/accounts/{account_id}", this::getAccountByIdHandler);
        app.get("/accounts", this::getAllAccountsHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
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
        ArrayList<Account> account = accountService.getAllAccounts();
        // send result to client
        if(account != null) {
            ctx.json(account);
        } else {
            ctx.status(400);
        }
    }


}