package basic;

// basic.Manager subclass also demonstrates inheritance and polymorphism.
class Manager extends Employee {
    public Manager(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String getDetails() {
        return "basic.Manager: " + getFullName();
    }

    @Override
    public void doTask() {
        System.out.println(getFullName() + " is overseeing projects.");
    }
}