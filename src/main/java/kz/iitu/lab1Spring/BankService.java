package kz.iitu.lab1Spring;

public interface BankService {
    void withdrawal(double sum, int id);
    void deposit(double sum, int id);
    void checkBalance(int cash);
    boolean changePin(int id, int pin, int newPin);
}
