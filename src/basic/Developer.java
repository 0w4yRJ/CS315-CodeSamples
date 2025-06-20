package basic;

// basic.Developer subclass shows inheritance and overrides behavior.
public class Developer extends Employee {
    public Developer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String getDetails() {
        return "basic.Developer: " + getFullName();
    }

    @Override
    public void doTask() {
        System.out.println(getFullName() + " is writing code.");
    }
}