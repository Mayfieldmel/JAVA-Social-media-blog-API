package Model;

public class Account {
    // state
    public int account_id;
    public String username;
    public String password;

    // no-args constructor
    public Account(){
    }
  
    // constructor for creating account
    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    // constructor for retrieving account
    public Account(int account_id, String username, String password) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
    }
    
    // getters
    public int getAccount_id() {
        return account_id;
    }
    
    public String getUsername() {
        return username;
    }

     public String getPassword() {
        return password;
    }
   
    // setters
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    

    // override inherited methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return account_id == account.account_id && username.equals(account.username) && password.equals(account.password);
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
