package kz.iitu.lab1Spring;

public interface BankService {
    double withdrawal(double sum, int id);
    double deposit(double sum, int id);
    void checkBalance(int cash);
}
