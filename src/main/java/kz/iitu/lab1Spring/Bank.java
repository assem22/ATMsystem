package kz.iitu.lab1Spring;

import java.util.ArrayList;
import java.util.List;

public class Bank implements BankService{
    private List<Account> accounts = new ArrayList<>();

    public Bank() {
    }

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public boolean checkPin(int id, int pin) {
        for (Account account: this.accounts){
            if (account.getId() == id && account.getPin() == pin){
                return true;
            }
        }
        return false;
    }

    @Override
    public double withdrawal(double sum, int id) {
        for (Account account: this.accounts){
            if (account.getId() == id){
                double total = account.getCash() - sum;
                account.setCash(total);
                System.out.println("The transaction was successful!");
                return account.getCash();
            }
        }
        return 0;
    }

    @Override
    public double deposit(double sum, int id) {
        for (Account account: this.accounts){
            if (account.getId() == id){
                double total = account.getCash() + sum;
                account.setCash(total);
                System.out.println("The transaction was successful!");
                return account.getCash();
            }
        }
        return 0;
    }

    @Override
    public void checkBalance(int id) {
        for (Account account: this.accounts){
            if (account.getId() == id){
                System.out.println("The transaction was successful!\n" +
                        "Account balance: " + account.getCash());
            }
        }
    }

    @Override
    public String toString() {
        return "Bank{" +
                "accounts=" + accounts +
                '}';
    }
}
