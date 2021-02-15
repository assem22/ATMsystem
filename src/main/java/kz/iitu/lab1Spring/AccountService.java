package kz.iitu.lab1Spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    Connection connection;
    Statement statement;
    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void updateAccounts(Account account) {
        try {
            String query = "UPDATE account SET pin = '" + account.getPin() + "', cash = '" + account.getCash() +"'  WHERE id = " + account.getId();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    @PostConstruct
    public void init() throws SQLException {
        this.createDbConnection();
    }

    public void createDbConnection() throws SQLException {
        // init connection
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            statement = connection.createStatement();
            String queryString = "select * from account";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()){
                Account account = new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                accounts.add(account);
            }
            System.out.println("UserService.createDbConnection");
            System.out.println(accounts);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
    }

    @PreDestroy
    public void destroyCustom() throws SQLException {
        this.closeConnections();
    }

    public void closeConnections() throws SQLException {
        connection.close();
        System.out.println("UserService.closeConnections");
    }
}
