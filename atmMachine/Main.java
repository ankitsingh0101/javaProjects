// import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private double balance;
    private String accountHolderName;

    public Account(int accountNumber, double balance, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolderName = accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit Successful. New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal Successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }
}

class AccountManager {
    private ArrayList<Account> accounts;

    public AccountManager() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}

class TransactionProcessor {
    public void processTransaction(Account account, Scanner scanner) {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.println();
        System.out.print(">>>>Enter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Your current balance: " + account.getBalance());
                break;

            case 2:
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                break;
            case 3:
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                account.withdraw(withdrawAmount);
                break;
            case 4:
                System.out.println("Exiting...");
                System.out.println("Thank you for using our ATM services.");
                System.out.println("Have a great day!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

class ATM {
    private AccountManager accountManager;
    private TransactionProcessor transactionProcessor;
    private Scanner scanner;

    public ATM() {
        this.accountManager = new AccountManager();
        this.transactionProcessor = new TransactionProcessor();
        this.scanner = new Scanner(System.in);
    }

    public void addAccount(Account account) {
        accountManager.addAccount(account);
    }

    public void start() {
        while (true) {
            System.out.print(">>>> Enter your account number: ");
            int accountNumber = scanner.nextInt();
            Account account = accountManager.findAccount(accountNumber);
            if (account == null) {
                System.out.println("Account not found.");
                continue;
            }
            System.out.println();
            System.out.println("Welcome, " + account.getAccountHolderName() + "!");
            System.out.println();

            while (true) {
                transactionProcessor.processTransaction(account, scanner);
                System.out.print("Do you want to continue? (yes/no): ");
                String response = scanner.next();
                if (response.equalsIgnoreCase("no")) {
                    System.out.println();
                    System.out.println("Thank you for using our ATM services.");
                    System.out.println("Have a great day!");
                    System.exit(0);
                }
                System.out.println();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Account account1 = new Account(1234, 1000.0, "John Doe");
        Account account2 = new Account(5678, 500.0, "Jane Smith");
        Account account3 = new Account(9012, 0.0, "Bob Johnson");
        Account account4 = new Account(1111, 70000.0, "Bruce Wayne");
        Account account5 = new Account(7777, 20000.0, "Ankit");
        Account account6 = new Account(9002, 8000.0, "Sunny");

        atm.addAccount(account1);
        atm.addAccount(account2);
        atm.addAccount(account3);
        atm.addAccount(account4);
        atm.addAccount(account5);
        atm.addAccount(account6);
        atm.start();
    }
}