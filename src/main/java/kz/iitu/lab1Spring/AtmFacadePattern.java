package kz.iitu.lab1Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class AtmFacadePattern {
    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//    static ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    private static final Scanner in = new Scanner(System.in);
    private static Bank bank = new Bank();

    private static AccountService accountService;

    public static void start(){
//        accountService = context.getBean("accountService", AccountService.class);
        context.scan("kz.iitu.lab1Spring");
        context.refresh();
        accountService = context.getBean("accountService", AccountService.class);
        bank.setAccounts(accountService.getAccounts());
        startMenu();
    }
    public static void startMenu(){
//        accountService = context.getBean("accountService", AccountService.class);
//        bank.setAccounts(accountService.getAccounts());
        System.out.println("Enter card id:");
        int id = in.nextInt();
        System.out.println("Enter pin: ");
        int pin = in.nextInt();
        Account account = bank.checkPin(id, pin);
        if (account != null){
            while(true){
                menu(account);
            }
        }else{
            System.out.println("Wrong pin or card id");
            startMenu();
        }
//        ((ClassPathXmlApplicationContext) context).close();
        context.close();
    }

    private static void menu(Account account) {
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
                if (bank.deposit(sum, account.getId())){
                    accountService.updateAccounts(account);
                }
                break;
            case 2:
                System.out.println("Enter the sum: ");
                sum = in.nextDouble();
                if (bank.withdrawal(sum, account.getId())){
                    accountService.updateAccounts(account);
                }
                break;
            case 3:
                bank.checkBalance(account.getId());
                break;
            case 4:
                if (changePin(account.getId())){
                    accountService.updateAccounts(account);
                }
                break;
            case 5:
                startMenu();
                break;
        }
    }

    private static boolean changePin(int id) {
        System.out.println("Enter your old pin:");
        int old = in.nextInt();
        System.out.println("Enter your new pin:");
        int newPin = in.nextInt();
        System.out.println("Repeat new pin:");
        int repeat = in.nextInt();
        if (newPin == repeat && bank.changePin(id, old, newPin)){
            System.out.println("Your pin was successfully changed!");
            return true;
        }else if(newPin == repeat && !bank.changePin(id, old, newPin)){
            System.out.println("Your old id incorrect. Please, try again!");
            changePin(id);
        }else{
            System.out.println("Password doesn't match. Please, try again!");
            changePin(id);
        }
        return false;
    }
}
