package basicV2;

import java.util.Scanner;

public class BasicEmployeeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ----- Input Phase -----

        // 1. Get a free-form welcome message.
        System.out.print("Enter a welcome message: ");
        String welcomeMsg = scanner.nextLine();

        // 2. Select bonus amounts via preset options.
        System.out.println("\nSelect base bonus amount:");
        System.out.println("1: $1000");
        System.out.println("2: $1500");
        System.out.println("3: $2000");
        System.out.print("Input: ");
        int bonusChoice = readInt(scanner, 1);
        double baseBonus = (bonusChoice == 2) ? 1500.0 : (bonusChoice == 3 ? 2000.0 : 1000.0);

        System.out.println("\nSelect extra bonus amount:");
        System.out.println("1: $250");
        System.out.println("2: $500");
        System.out.println("3: $750");
        System.out.print("Input: ");
        int extraChoice = readInt(scanner, 1);
        double extraBonus = (extraChoice == 2) ? 500.0 : (extraChoice == 3 ? 750.0 : 250.0);

        // 3. Choose a preset performance score set.
        System.out.println("\nSelect a performance score set:");
        System.out.println("1: [80, 90, 75, 85, 100]");
        System.out.println("2: [70, 85, 90, 78, 82]");
        System.out.print("Input: ");
        int perfChoice = readInt(scanner, 1);
        int[] performanceScores;
        if (perfChoice == 2) {
            performanceScores = new int[] {70, 85, 90, 78, 82};
        } else {
            performanceScores = new int[] {80, 90, 75, 85, 100};
        }

        scanner.close();

        // ----- Employees are Preset in an Array -----
        Employee[] employees = new Employee[] {
                new Developer("Alice", "Smith", 75000.0),
                new Manager("Bob", "Johnson", 90000.0),
                new Employee("Charlie", "Brown", 50000.0)
        };

        // ----- Output Phase -----

        // Normalize the welcome message.
        String normalizedMsg = StringUtils.normalizeText(welcomeMsg);

        // Calculate bonus value.
        double totalBonus = baseBonus + extraBonus;

        // Compute the average performance score.
        int totalScore = 0;
        for (int score : performanceScores) {
            totalScore += score;
        }
        double avgPerformance = (double) totalScore / performanceScores.length;

        // Process employee details and total payroll.
        double totalPayroll = 0;
        StringBuilder unsortedDetails = new StringBuilder("Unsorted Employee Details:\n");
        for (Employee emp : employees) {
            unsortedDetails.append(emp.getDetails()).append("\n");
            totalPayroll += emp.getSalary();
        }

        // Sort employees by last name using bubble sort.
        bubbleSortEmployees(employees);
        StringBuilder sortedDetails = new StringBuilder("Employees Sorted by Last Name:\n");
        for (Employee emp : employees) {
            sortedDetails.append(emp.getDetails()).append("\n");
        }

        // Let each employee perform their task (demonstrates polymorphism).
        StringBuilder taskOutput = new StringBuilder("Employee Tasks:\n");
        for (Employee emp : employees) {
            taskOutput.append(emp.performTask()).append("\n");
        }

        // Check if payroll exceeds a set threshold.
        String payrollMessage = (totalPayroll > 200000)
                ? "Note: Total payroll exceeds $200,000. Consider a budget review."
                : "Payroll is within budget.";

        // ----- Final Consolidated Output -----
        System.out.println("\n===== APPLICATION OUTPUT =====");
        System.out.println("Team Welcome Message: " + normalizedMsg);
        System.out.printf("Bonus Budget: Base $%.2f + Extra $%.2f = Total $%.2f%n", baseBonus, extraBonus, totalBonus);
        System.out.printf("Average Performance Score: %.2f%n", avgPerformance);
        System.out.println(unsortedDetails);
        System.out.printf("Total Payroll: $%.2f%n", totalPayroll);
        System.out.println(sortedDetails);
        System.out.println(taskOutput);
        System.out.println(payrollMessage);
        System.out.println("\nApplication complete.");
    }

    // Utility method for safe integer input.
    private static int readInt(Scanner scanner, int defaultValue) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Defaulting to " + defaultValue);
            return defaultValue;
        }
    }

    // Bubble sort to order employees by last name.
    public static void bubbleSortEmployees(Employee[] employees) {
        int n = employees.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (employees[j].getLastName().compareTo(employees[j + 1].getLastName()) > 0) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}



// Define an interface to enforce a common task for all employees.
interface Taskable {
    String doTask();
}

// Base Employee class implements Taskable.
class Employee implements Taskable {
    protected String firstName;
    protected String lastName;
    protected double salary;

    public Employee(String firstName, String lastName, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public String getDetails() {
        return "Employee: " + firstName + " " + lastName + " | Salary: $" + salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getLastName() {
        return lastName;
    }

    public String doTask() {
        return getDetails() + " performs general tasks.";
    }

    public String performTask() {
        return doTask();
    }
}

// Developer subclass.
class Developer extends Employee {
    public Developer(String firstName, String lastName, double salary) {
        super(firstName, lastName, salary);
    }

    @Override
    public String getDetails() {
        return "Developer: " + firstName + " " + lastName + " | Salary: $" + salary;
    }

    @Override
    public String doTask() {
        return getDetails() + " is coding.";
    }
}

// Manager subclass.
class Manager extends Employee {
    public Manager(String firstName, String lastName, double salary) {
        super(firstName, lastName, salary);
    }

    @Override
    public String getDetails() {
        return "Manager: " + firstName + " " + lastName + " | Salary: $" + salary;
    }

    @Override
    public String doTask() {
        return getDetails() + " is managing.";
    }
}

// Utility class for string manipulation.
class StringUtils {
    public static String normalizeText(String input) {
        return (input == null) ? "" : input.trim().toUpperCase();
    }

    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) return "";
        String lower = input.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}
