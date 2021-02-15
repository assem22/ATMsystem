package kz.iitu.lab1Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class AtmFacadePattern {
    static ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    private static final Scanner in = new Scanner(System.in);
    private static Bank bank = new Bank();
    private static AccountService accountService = new AccountService();

    public static void startMenu(){
//        AccountService bank1 = context.getBean("accountService", AccountService.class);
//        bank = context.getBean("accounts", Bank.class);
        bank.setAccounts(accountService.getAccounts());
        System.out.println(accountService.getAccounts());
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
        ((ClassPathXmlApplicationContext) context).close();
    }

    private static void menu(Account currentAcc) {
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
                if (bank.deposit(sum, currentAcc.getId())){
                    accountService.updateAccounts(currentAcc);
                }
                break;
            case 2:
                System.out.println("Enter the sum: ");
                sum = in.nextDouble();
                bank.withdrawal(sum, currentAcc.getId());
                break;
            case 3:
                bank.checkBalance(currentAcc.getId());
                break;
            case 4:
                changePin(currentAcc.getId());
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
