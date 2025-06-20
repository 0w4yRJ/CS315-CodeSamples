package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BasicJava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display a welcome message.
        System.out.println("\nWelcome to our interactive employee system!");

        // Ask the user how many employees they want to enter.
        System.out.print("How many employees do you want to enter? ");
        int count;

        try {
            count = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default count of 2.");
            count = 2;
        }

        // Collect a team message and apply string manipulation.
        System.out.print("\nEnter a message for your team: ");
        String teamMessage = scanner.nextLine();

        List<Employee> employees = new ArrayList<>();

        // Collect employee details from the user.
        for (int i = 0; i < count; i++) {
            System.out.println("\nEntering details for basic.Employee #" + (i + 1) + ": ");
            System.out.print("Enter employee type (basic.Developer/basic.Manager/Generic): ");
            String type = scanner.nextLine().trim();

            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine().trim();

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine().trim();

            // Create an employee instance based on the input type.
            if (type.equalsIgnoreCase("basic.Developer")) {
                employees.add(new Developer(firstName, lastName));
            } else if (type.equalsIgnoreCase("basic.Manager")) {
                employees.add(new Manager(firstName, lastName));
            } else {
                employees.add(new Employee(firstName, lastName));
            }
        }

        // Print unsorted employee details.
        System.out.println("basic.Employee details (unsorted):");
        for (Employee emp : employees) {
            System.out.println(emp.getDetails());
        }

        // Sorting employees using a custom bubble sort algorithm.
        bubbleSortEmployees(employees);
        System.out.println("\nEmployees sorted by last name:");
        for (Employee emp : employees) {
            System.out.println(emp.getDetails());
        }

        // Demonstrate polymorphism and interface usage: each employee performs their work.
        System.out.println("\nEmployees doing their work:");
        for (Employee emp : employees) {
            emp.doTask();  // Polymorphic call based on employee type.
        }

        // The normalized message is used directly in the output.
        System.out.println("\nTeam Message: " + StringUtils.normalizeText(teamMessage));

        scanner.close();
    }

    /**
     * Custom bubble sort algorithm to sort employees by their last name.
     * This method iterates through the list repeatedly, comparing adjacent
     * elements and swapping them if they're in the wrong order.
     */
    public static void bubbleSortEmployees(List<Employee> employees) {
        int n = employees.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (employees.get(j).getLastName().compareTo(employees.get(j + 1).getLastName()) > 0) {
                    // Swap employees at positions j and j+1.
                    Employee temp = employees.get(j);
                    employees.set(j, employees.get(j + 1));
                    employees.set(j + 1, temp);
                    swapped = true;
                }
            }
            // If no two elements were swapped during the inner loop, the list is sorted.
            if (!swapped) {
                break;
            }
        }
    }
}