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

    public static void startMenu(){
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
//        double resultOfDeposit = 0;
        System.out.println("[1] top up\n" +
                "[2] withdrawal\n" +
                "[3] check balance\n" +
                "[4] change pin\n" +
                "[5] exit");
        int choice = in.nextInt();
        double sum = 0;
        switch (choice){
            case 1:
                System.out.println("Enter the sum: ");
                sum = in.nextDouble();
                bank.deposit(sum, id);
                break;
            case 2:
                System.out.println("Enter the sum: ");
                sum = in.nextDouble();
                bank.withdrawal(sum, id);
                break;
            case 3:
                bank.checkBalance(id);
                break;
            case 4:
                changePin(id);
                break;
            case 5:
                startMenu();
                break;
        }
    }

    private static void changePin(int id) {
        System.out.println("Enter your old pin:");
        int old = in.nextInt();
        System.out.println("Enter your new pin:");
        int newPin = in.nextInt();
        System.out.println("Repeat new pin:");
        int repeat = in.nextInt();
        if (newPin == repeat && bank.changePin(id, old, newPin)){
            System.out.println("Your pin was successfully changed!");
        }else if(newPin == repeat && !bank.changePin(id, old, newPin)){
            System.out.println("Your old id incorrect. Please, try again!");
            changePin(id);
        }else{
            System.out.println("Password doesn't match. Please, try again!");
            changePin(id);
        }
    }
}
