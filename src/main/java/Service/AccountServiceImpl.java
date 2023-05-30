package Service;

import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import Model.Account;

public class AccountServiceImpl implements AccountService {
    // state
    private AccountDAO accountDAO;
    public AccountServiceImpl() {
        this.accountDAO = new AccountDAOImpl();
    }

    // create account
    @Override
    public Account addAccount(Account account) {
        if(account.getUsername() != null && account.getPassword().length() >= 4){
            return accountDAO.addAccount(account);
        } else {
            return null;
        }
        
    }

    @Override
    public Account getAccount(String username, String password) {
        return accountDAO.getAccount(username, password);
    }
    
}
