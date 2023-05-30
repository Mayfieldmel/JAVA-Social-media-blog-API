package Service;

import Model.Account;

public interface AccountService {
    // Create
    public abstract Account addAccount(Account account);

    // read/ LOGIN
    public abstract Account geAccount(String username, String password);

}
