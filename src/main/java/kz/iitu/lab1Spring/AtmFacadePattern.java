package kz.iitu.lab1Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class AtmFacadePattern {
    private static ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    private static final Scanner in = new Scanner(System.in);
    private static Bank bank = new Bank();

//    private static Account account;

    public void startMenu(){
        bank = context.getBean("accounts", Bank.class);
//        System.out.println(bank.getAccounts());
        System.out.println("Enter card id:");
        int id = in.nextInt();
        System.out.println("Enter pin: ");
        int pin = in.nextInt();
        if (bank.checkPin(id, pin)){
            while(true){
                menu(id);
            }
        }else{
            System.out.println("Wrong pin or card id");
            startMenu();
        }
    }

    private static void menu(int id) {
        double resultOfDeposit = 0;
        System.out.println("[1] deposit\n" +
                "[2] withdrawal\n" +
                "[3] check balance\n" +
                "[4] exit");
        int choice = in.nextInt();
        double sum = 0;
        switch (choice){
            case 1:
                System.out.println("Enter the sum: ");
                sum = in.nextDouble();
                resultOfDeposit = bank.deposit(sum, id);
                break;
            case 2:
                System.out.println("Enter the sum: ");
                sum = in.nextDouble();
                resultOfDeposit = bank.withdrawal(sum, id);
                break;
            case 3:
                bank.checkBalance(id);
                break;
            case 4:
                System.exit(0);
                break;
        }
    }
}