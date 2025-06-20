package Advance.Model;

/* -------------------------------
   InterestAccrualThread: Applies interest to savings accounts periodically.
   ------------------------------- */
public class InterestAccrualThread extends Thread {
    private Bank bank;
    private volatile boolean running = true;

    public InterestAccrualThread(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        while (running) {
            for (Account account : bank.getAccounts()) {
                if (account instanceof SavingsAccount) {
                    ((SavingsAccount) account).applyInterest();
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopInterest() {
        running = false;
    }
}