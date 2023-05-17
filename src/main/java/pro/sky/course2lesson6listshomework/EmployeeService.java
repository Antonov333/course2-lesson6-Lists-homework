package pro.sky.course2lesson6listshomework;

import java.util.*;

public class EmployeeService {

    private static final int MAX_PERSONNEL_NUMBER = 100;

    private Set<Employee> employeeList;

    public EmployeeService() {
        employeeList = new HashSet<>();
    }

    public static int getMaxPersonnelNumber() {
        return MAX_PERSONNEL_NUMBER;
    }

    public int getPersonnelNumber() {
        return employeeList.size();
    }

    public String addEmployee(String firstName, String lastName) {
        if (getPersonnelNumber() >= MAX_PERSONNEL_NUMBER) {
            return "Personnel is full. Here should be exception";
        }
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            return employee.toString() + " already hired";
        }
        employeeList.add(employee);
        return employee.toString() + " is successfully hired to the company";
    }

    public boolean findEmployee(String firstname, String lastname) {
        return employeeList.contains(new Employee(firstname, lastname));
    }

    public boolean removeEmployee(String firstName, String lastname) {
        return employeeList.remove(new Employee(firstName, lastname));
    }
}