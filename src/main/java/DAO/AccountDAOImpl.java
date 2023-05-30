package DAO;

import java.sql.*;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

    // create account
    @Override
    public Account addAccount(Account account) {
        // open connection
        Connection con = ConnectionUtil.getConnection();
        try {
        // create statement
        String sql = "INSERT INTO account VALUES(default, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, account.getUsername());
        ps.setString(2, account.getPassword());

        // generate result set
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();

        // return generated account object
        if(rs.next()) {
            int generated_account_id = (int) rs.getLong(1);
            return new Account(generated_account_id, account.getUsername(), account.getPassword());
        }
    } catch (SQLException e) {
        // handle exception
        System.out.println(e.getMessage());
    }
        return null;
    }

    // login to account
    @Override
    public Account getAccount(String username, String password) {
           // open connection
        Connection con = ConnectionUtil.getConnection();
        try {
        // create statement
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);

        // generate result set
        ResultSet rs = ps.executeQuery();
        // process results
        while(rs.next()) {
            int id = rs.getInt("account_id");
            String usernameResult = rs.getString("username");
            String passwordResult = rs.getString("password");
            Account login = new Account(id, usernameResult, passwordResult);
            return login;
        }

    } catch (SQLException e) {
        // handle exception
        System.out.println(e.getMessage());
    }
        return null;
       
    }
}