package pro.sky.course2lesson6listshomework;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;

    public String toString() {
        return firstName + " " + lastName;
    }

    public String toJSON() {
//        JSON { "firstName": "Ivan", "lastName": "Ivanov" }
        return "{\"firstName\": \"" + firstName + "\", \"lastName\": \"" + lastName + "\"}";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }
        ;
        return Objects.equals(firstName, ((Employee) other).getFirstName())
                && Objects.equals(lastName, ((Employee) other).getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
