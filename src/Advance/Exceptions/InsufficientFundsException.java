package Advance.Exceptions;

/* -------------------------------
   Custom Exception for Insufficient Funds
   ------------------------------- */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}