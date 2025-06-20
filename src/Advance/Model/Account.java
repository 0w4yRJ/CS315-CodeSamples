package Advance.Model;

import Advance.Exceptions.InsufficientFundsException;

/* -------------------------------
   Abstract Account and Subclasses
   ------------------------------- */
public abstract class Account {
    protected String owner;
    protected double balance;

    public Account(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }
    public String getOwner() { return owner; }
    public double getBalance() { return balance; }

    // Synchronized deposit to ensure thread safety.
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(owner + " deposited $" + amount +
                    ". New balance: $" + balance);
        }
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException;
    public abstract String getDetails();
}