package Advance.Model;

import Advance.Exceptions.InsufficientFundsException;

public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String owner, double initialBalance, double overdraftLimit) {
        super(owner, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public synchronized void withdraw(double amount) throws InsufficientFundsException {
        if (balance - amount >= -overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from " +
                    owner + "'s checking account. New balance: $" + balance);
        } else {
            throw new InsufficientFundsException("Overdraft limit exceeded in checking account for " + owner);
        }
    }

    @Override
    public String getDetails() {
        return "CheckingAccount [" + owner + "]: Balance = $" + balance +
                ", Overdraft Limit = $" + overdraftLimit;
    }
}