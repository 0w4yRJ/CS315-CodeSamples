package Advance.Application;

import java.sql.*;
import java.util.Scanner;

public class Employees {

    static Connection connection;

    public static void main(String[] args) {

        // Establish connection
        connectToDB();

        do {
            System.out.println("""
                    What would you like to do?
                    [1] Create an employee
                    [2] Display all employees
                    [3] Update employee details
                    [4] Delete employee
                    [5] Exit
                    """);
            System.out.print("Input: ");
            switch (new Scanner(System.in).nextLine()) {
                case "1":
                    createEmployee();
                    break;
                case "2":
                    displayEmployees();
                    break;
                case "3":
                    updateSalary();
                    break;
                case "4":
                    deleteEmployee();
                    break;
                case "5":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }

        } while (true);

    }

    public static void createEmployee() {
        System.out.println("~ ~ Creating an employee ~ ~");

        System.out.print("Enter the employee's full name: ");
        String name = new Scanner(System.in).nextLine();

        System.out.print("Enter the employee's salary: ");
        double salary = new Scanner(System.in).nextDouble();

        try (CallableStatement statement = connection.prepareCall("{CALL create_employee(?,?)}")) {
            statement.setString(1, name);
            statement.setDouble(2, salary);
            statement.execute();
            System.out.println("Successfully added the employee");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void displayEmployees() {
        System.out.println("~ ~ Employees ~ ~");
        try (CallableStatement statement = connection.prepareCall("{CALL get_employees()}");
             ResultSet employeeList = statement.executeQuery();
        ) {


            System.out.printf("%-5s%-20s%-15s%-20s\n", "ID", "Name", "Salary", "Date Hired");
            while (employeeList.next()) {
                System.out.printf("%-5s%-20s%-15.2f%-20s\n",
                        employeeList.getString("id"),
                        employeeList.getString("name"),
                        employeeList.getDouble("salary"),
                        employeeList.getString("date_hired")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updateSalary() {
        try (CallableStatement statement = connection.prepareCall("{CALL update_salary(?,?)}")) {

            System.out.print("Enter the employee's ID: ");
            String id = new Scanner(System.in).nextLine();

            System.out.print("Enter the new salary: ");
            double newSalary = new Scanner(System.in).nextDouble();

            statement.setString(1, id);
            statement.setDouble(2, newSalary);
            statement.execute();
            System.out.println("Successfully updated the employee's salary");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteEmployee() {
        try (CallableStatement statement = connection.prepareCall("{CALL delete_employee(?)}")) {

            System.out.print("Enter the employee's ID: ");
            String id = new Scanner(System.in).nextLine();

            statement.setString(1, id);
            statement.execute();
            System.out.println("Successfully deleted employee with the ID " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void connectToDB() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bank", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
