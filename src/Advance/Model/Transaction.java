package Advance.Model;

/* -------------------------------
   Transaction: Records deposits and withdrawals.
   ------------------------------- */
public class Transaction {
    private String accountOwner;
    private String type;   // "Deposit" or "Withdrawal"
    private double amount;

    public Transaction(String accountOwner, String type, double amount) {
        this.accountOwner = accountOwner;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "[" + type + "] " + accountOwner + ": $" + amount;
    }
}