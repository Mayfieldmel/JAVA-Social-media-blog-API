package Service;

import Model.Account;

public interface AccountService {
    // Create
    public abstract Account addAccount(Account account);

    // Read
    // login
    public abstract Account getAccount(String username, String password);
    // get account by id
    public abstract Account getAccountById(int id);

}
