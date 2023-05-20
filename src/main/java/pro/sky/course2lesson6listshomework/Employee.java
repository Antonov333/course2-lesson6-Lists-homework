package pro.sky.course2lesson6listshomework;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private String status;

    public String toString() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee(String firstName, String lastName, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }
        return Objects.equals(firstName, ((Employee) other).getFirstName())
                && Objects.equals(lastName, ((Employee) other).getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
