package basic;

// The base class using encapsulation to hold basic employee details.
public class Employee implements WorkAction {
    private String firstName;
    private String lastName;

    // Constructor: Uses basic string manipulation to format names.
    public Employee(String firstName, String lastName) {
        this.firstName = StringUtils.normalizeText(firstName);
        this.lastName  = StringUtils.normalizeText(lastName);
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    // Concatenates first and last name.
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Returns a simple employee detail; can be overridden.
    public String getDetails() {
        return "basic.Employee: " + getFullName();
    }

    // Default implementation for doing a task.
    public void doTask() {
        System.out.println(getFullName() + " is handling general work tasks.");
    }
}