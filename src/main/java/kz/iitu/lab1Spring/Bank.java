package kz.iitu.lab1Spring;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


//@Entity
//@Table(name = "bank")
public class Bank implements BankService{
//    @OneToMany(mappedBy = "bank")
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
    public void withdrawal(double sum, int id) {
        for (Account account: this.accounts){
            if (account.getId() == id){
                double total = account.getCash() - sum;
                account.setCash(total);
                System.out.println("The transaction was successful!");
            }
        }
    }

    @Override
    public void deposit(double sum, int id) {
        for (Account account: this.accounts){
            if (account.getId() == id){
                double total = account.getCash() + sum;
                account.setCash(total);
                System.out.println("The transaction was successful!");
            }
        }
    }

    @Override
    public void checkBalance(int id) {
        for (Account account: this.accounts){
            if (account.getId() == id){
                System.out.println("Your balance: " + account.getCash());
            }
        }
    }

    @Override
    public boolean changePin(int id, int pin, int newPin) {
        for (Account account: this.accounts){
            if (account.getPin() == pin && account.getId() == id){
                account.setPin(newPin);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "accounts=" + accounts +
                '}';
    }
}
