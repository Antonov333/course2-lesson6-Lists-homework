package pro.sky.course2lesson6listshomework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EmployeeService {

    private static final int MAX_PERSONNEL_NUMBER = 100;

    private List<Employee> employeeList;

    public EmployeeService() {
        employeeList = new ArrayList<>();
    }

    public static int getMaxPersonnelNumber() {
        return MAX_PERSONNEL_NUMBER;
    }

    public int getPersonnelNumber() {
        return employeeList.size();
    }

    public boolean addEmployee(String firstName, String lastName) {
        if (getPersonnelNumber() >= MAX_PERSONNEL_NUMBER) {
            System.out.println("Here will be exception because personell is full");
            return false;
        }

        Employee employee = new Employee(firstName, lastName);
        employeeList.add(employee);
        return true;

    }
}
