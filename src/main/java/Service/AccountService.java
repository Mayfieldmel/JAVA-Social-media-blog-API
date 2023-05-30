package Service;

import Model.Account;

public interface AccountService {
    // Create
    public abstract Account addAccount(Account account);

    // read/ LOGIN
    public abstract Account getAccount(String username, String password);

}
