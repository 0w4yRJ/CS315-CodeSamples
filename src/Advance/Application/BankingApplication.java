package Advance.Application;

import Advance.Model.Bank;
import Advance.Model.CheckingAccount;
import Advance.Model.InterestAccrualThread;
import Advance.Model.SavingsAccount;

import java.util.Scanner;

public class BankingApplication {
    public static void main(String[] args) {
        // Create a new bank and add some sample accounts.
        Bank bank = new Bank();
        bank.addAccount(new SavingsAccount("Alice", 1000.0, 0.02));
        bank.addAccount(new CheckingAccount("Bob", 500.0, 200.0));
        bank.addAccount(new SavingsAccount("Charlie", 1500.0, 0.03));

        // Start a background thread to accrue interest on savings accounts.
        InterestAccrualThread interestThread = new InterestAccrualThread(bank);
        interestThread.start();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n-----------------------------");
            System.out.println("    Banking Application Menu");
            System.out.println("-----------------------------");
            System.out.println("1. Deposit funds");
            System.out.println("2. Withdraw funds");
            System.out.println("3. View account details");
            System.out.println("4. View sorted account details (BST)");
            System.out.println("5. View transaction history (Stack)");
            System.out.println("6. Process pending transactions (Queue)");
            System.out.println("7. Create a new account");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter account owner's name for deposit: ");
                    String depositOwner = scanner.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    bank.performDeposit(depositOwner, depositAmount);
                    break;
                case 2:
                    System.out.print("Enter account owner's name for withdrawal: ");
                    String withdrawOwner = scanner.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    bank.performWithdrawal(withdrawOwner, withdrawAmount);
                    break;
                case 3:
                    System.out.println("\n--- Account Details ---");
                    bank.printAccountDetails();
                    break;
                case 4:
                    System.out.println("\n--- Sorted Account Details (BST In-Order) ---");
                    bank.printAccountsBST();
                    break;
                case 5:
                    System.out.println("\n--- Transaction History (Most recent first) ---");
                    bank.printTransactionHistory();
                    break;
                case 6:
                    System.out.println("\n--- Processing Pending Transactions (FIFO) ---");
                    bank.processPendingTransactions();
                    break;
                case 7:
                    System.out.print("Enter account type (Savings/Checking): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter account owner's name: ");
                    String owner = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = Double.parseDouble(scanner.nextLine());
                    if (type.equalsIgnoreCase("Savings")) {
                        System.out.print("Enter interest rate (as a decimal, e.g., 0.02): ");
                        double rate = Double.parseDouble(scanner.nextLine());
                        bank.addAccount(new SavingsAccount(owner, initialBalance, rate));
                        System.out.println("Savings account created for " + owner + ".");
                    } else if (type.equalsIgnoreCase("Checking")) {
                        System.out.print("Enter overdraft limit: ");
                        double limit = Double.parseDouble(scanner.nextLine());
                        bank.addAccount(new CheckingAccount(owner, initialBalance, limit));
                        System.out.println("Checking account created for " + owner + ".");
                    } else {
                        System.out.println("Invalid account type. Account not created.");
                    }
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Please select a valid option (1-8).");
            }
        }

        // Shut down the interest thread before exiting.
        interestThread.stopInterest();
        try {
            interestThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        scanner.close();
        System.out.println("Exiting application. Goodbye!");
    }
}