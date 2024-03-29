package Service;

import java.util.ArrayList;

import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import Model.Account;

public class AccountServiceImpl implements AccountService {
    // state
    private AccountDAO accountDAO;
    
    // constructor
    public AccountServiceImpl() {
        this.accountDAO = new AccountDAOImpl();
    }

    // create account
    @Override
    public Account addAccount(Account account) {
        if(account.getUsername().isBlank() || account.getPassword().length() < 4){
            return null;
        } else {
            return accountDAO.addAccount(account);
        }
        
    }

    // login to account
    @Override
    public Account getAccount(String username, String password) {
        return accountDAO.getAccount(username, password);
    }

    // get account by id
    @Override
    public Account getAccountById(int id) {
        return accountDAO.getAccountById(id);
    }

    // get all accounts
    @Override
    public ArrayList<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
    
}
