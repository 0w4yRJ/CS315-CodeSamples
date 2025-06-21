package advancedV2;

import java.util.ArrayList;
import java.util.List;

// XML handling imports (internal DOM & Transformer classes)
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

// --- Advanced Banking Demo ---
public class AdvancedBankingDemo {

    public static void main(String[] args) {
        // --- Data Structures: Dynamic Collection ---
        // Use an ArrayList to hold bank accounts dynamically
        List<BankAccount> accountList = new ArrayList<>();
        BankAccount acc1 = new BankAccount("A001", 100.0);
        accountList.add(acc1);
        System.out.println("Initial Account List: " + accountList);

        // --- Exception Handling Demo ---
        try {
            System.out.println("\nAttempting withdrawal of 50.0 from account A001...");
            acc1.withdraw(50.0);
            System.out.println("Withdrawal successful. New Balance: " + acc1.getBalance());

            // This withdrawal triggers the custom exception
            System.out.println("Attempting withdrawal of 100.0 from account A001...");
            acc1.withdraw(100.0);
        } catch (InsufficientFundsException ex) {
            System.out.println("Exception caught: " + ex.getMessage());
        }

        // --- Threads/Concurrency: Start the Interest Accrual Thread ---
        InterestAccrualThread interestThread = new InterestAccrualThread(acc1);
        interestThread.start();

        // Let the interest thread run for 10 seconds to observe updates
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        interestThread.shutdown();

        // --- XML File Manipulation using Internal Libraries ---
        saveAccountToXMLInternal(acc1);

        // --- Wrap-Up ---
        System.out.println("\nAdvanced demo complete.");
    }

    // Using internal XML libraries to convert a BankAccount to XML and print to console.
    public static void saveAccountToXMLInternal(BankAccount account) {
        try {
            // Create a new Document for XML generation
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Root element: BankAccount
            Element rootElement = doc.createElement("BankAccount");
            doc.appendChild(rootElement);

            // Account number element
            Element accountNumber = doc.createElement("AccountNumber");
            accountNumber.appendChild(doc.createTextNode(account.getAccountNumber()));
            rootElement.appendChild(accountNumber);

            // Balance element
            Element balance = doc.createElement("Balance");
            balance.appendChild(doc.createTextNode(String.valueOf(account.getBalance())));
            rootElement.appendChild(balance);

            // Transform the DOM to XML and print to console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(System.out);
            System.out.println("\nXML Representation of BankAccount:");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// --- Custom Exception for Insufficient Funds ---
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// --- BankAccount Class ---
class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for account " + accountNumber);
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "BankAccount[" + accountNumber + ", Balance=" + balance + "]";
    }
}

// --- Thread for Periodic Interest Accrual ---
class InterestAccrualThread extends Thread {
    private BankAccount account;
    private volatile boolean running = true;

    public InterestAccrualThread(BankAccount account) {
        this.account = account;
    }

    public void run() {
        while (running) {
            try {
                // Simulate waiting period (3 seconds)
                Thread.sleep(3000);
                // Apply 2% interest on the current balance
                double interest = account.getBalance() * 0.02;
                account.deposit(interest);
                System.out.println("Interest applied: " + interest + " | New Balance: " + account.getBalance());
            } catch (InterruptedException e) {
                System.out.println("Interest calculation interrupted.");
                break;
            }
        }
    }

    public void shutdown() {
        running = false;
        this.interrupt();
    }
}