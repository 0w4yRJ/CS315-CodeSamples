package Advance.Model;

import Advance.Exceptions.InsufficientFundsException;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String owner, double initialBalance, double interestRate) {
        super(owner, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public synchronized void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from " +
                    owner + "'s savings. New balance: $" + balance);
        } else {
            throw new InsufficientFundsException("Insufficient funds in savings account for " + owner);
        }
    }

    // Applies interest to the savings account.
    public synchronized void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        /*
         * Optional Print statement for seeing the detail for live interest gains

        System.out.println(owner + "'s savings account applied interest: $" +
                interest + ". New balance: $" + balance);
         */
    }

    @Override
    public String getDetails() {
        return "SavingsAccount [" + owner + "]: Balance = $" + balance +
                ", Interest Rate = " + (interestRate * 100) + "%";
    }
}