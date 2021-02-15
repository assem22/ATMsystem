package kz.iitu.lab1Spring;

public class Account {
    private int id;
    private int pin;
    private double cash;

    public Account(){}

    public Account(int id, int pin, double cash) {
        this.id = id;
        this.pin = pin;
        this.cash = cash;
    }

    public Account(int id, int pin) {
        this.id = id;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", pin=" + pin +
                ", cash=" + cash +
                '}';
    }
}
