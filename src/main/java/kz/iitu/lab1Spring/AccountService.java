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
    private List<Account> accounts = new ArrayList<>();

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
            connection = DriverManager
                    .getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement();
            String queryString = "create table if not exists public.account (serial id, integer pin, real cash)";
            ResultSet rs = statement.executeQuery(queryString);
            setAccounts();
            System.out.println("UserService.createDbConnection");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
    }

    public void setAccounts() throws SQLException {
        for (Account account: accounts){
            Statement statement = connection.createStatement();
            String queryString = "insert into account(id, pin, cash) values ("+ account.getId() + "," + account.getPin() + "," +account.getCash() + ")";
            ResultSet rs = statement.executeQuery(queryString);
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

    public void setAccounts(List accounts) {
        this.accounts = accounts;
    }
}
