package DAO;

import Model.Account;

public interface AccountDAO {
    // create
    public abstract Account addAccount(Account account);

    // read/ LOGIN
    public abstract Account getAccount(String username, String password);
}
