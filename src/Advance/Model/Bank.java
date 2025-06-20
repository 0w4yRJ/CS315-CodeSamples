package Advance.Model;

import Advance.Exceptions.InsufficientFundsException;

import java.util.*;

/* -------------------------------
   Bank Class: Manages accounts,
   transactions (Stack & Queue),
   and a BST for sorted accounts.
   ------------------------------- */
public class Bank {
    private List<Account> accounts = new ArrayList<>();
    private Stack<Transaction> transactionHistory = new Stack<>();
    private Queue<Transaction> pendingTransactions = new LinkedList<>();
    private AccountBST accountBST = new AccountBST();

    public void addAccount(Account account) {
        accounts.add(account);
        accountBST.insert(account);
    }

    // Lookup account by owner name (case-insensitive)
    public Account getAccount(String owner) {
        for (Account account : accounts) {
            if (account.getOwner().equalsIgnoreCase(owner)) {
                return account;
            }
        }
        System.out.println("Account for " + owner + " not found.");
        return null;
    }

    public void performDeposit(String owner, double amount) {
        Account account = getAccount(owner);
        if (account != null) {
            account.deposit(amount);
            Transaction t = new Transaction(owner, "Deposit", amount);
            transactionHistory.push(t);
            pendingTransactions.offer(t);
        }
    }

    public void performWithdrawal(String owner, double amount) {
        Account account = getAccount(owner);
        if (account != null) {
            try {
                account.withdraw(amount);
                Transaction t = new Transaction(owner, "Withdrawal", amount);
                transactionHistory.push(t);
                pendingTransactions.offer(t);
            } catch (InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void printAccountDetails() {
        for (Account account : accounts) {
            System.out.println(account.getDetails());
        }
    }

    public void printAccountsBST() {
        accountBST.inOrder();
    }

    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        for (int i = transactionHistory.size() - 1; i >= 0; i--) {
            System.out.println(transactionHistory.get(i));
        }
    }

    public void processPendingTransactions() {
        if (pendingTransactions.isEmpty()) {
            System.out.println("No pending transactions.");
            return;
        }
        while (!pendingTransactions.isEmpty()) {
            Transaction t = pendingTransactions.poll();
            System.out.println("Processing: " + t);
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}