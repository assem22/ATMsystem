package kz.iitu.lab1Spring;

public interface BankService {
    boolean withdrawal(double sum, int id);
    boolean deposit(double sum, int id);
    void checkBalance(int cash);
    boolean changePin(int id, int pin, int newPin);
}
