package pro.sky.course2lesson6listshomework;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private int maxPersonnelNumber;

    private Set<Employee> employeeList;

    public EmployeeService() {
        employeeList = new HashSet<>();
        maxPersonnelNumber = 100;
    }

    public EmployeeService(int maxPersonnelNumber) {
        employeeList = new HashSet<>();
        this.maxPersonnelNumber = maxPersonnelNumber;
    }

    public int getMaxPersonnelNumber() {
        return maxPersonnelNumber;
    }

    public int getPersonnelNumber() {
        return employeeList.size();
    }

    public boolean addEmployee(String firstName, String lastName) {
        if (getPersonnelNumber() >= maxPersonnelNumber) {
            throw new EmployeeStorageIsFullException("No vacant position at the moment");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException("This person is already hired");
        }
        employeeList.add(employee);
        return true;
    }

    public boolean findEmployee(String firstname, String lastname) {
        if (employeeList.contains(new Employee(firstname, lastname))) {
            return true;
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public boolean removeEmployee(String firstName, String lastname) {
        boolean b = false;
        if (findEmployee(firstName, lastname)) {
            b = employeeList.remove(new Employee(firstName, lastname));
        }
        return b;
    }

    public String welcome() {

        return "<h2>Welcome to homework Sets for Course 2 Lesson 6 ))</h2><br><br>";

    }

    public String addEmployeeJSON(String firstName, String lastName) {
        String message = "Add Employee" + " " + firstName + " " + lastName + ": ";
        Boolean result = true;

        NameCheck nameCheck = new NameCheck(firstName, lastName);

        message = (new Employee(firstName, lastName)).toJSON();

        if (nameCheck.getCode() != 0) {
            message = message + nameCheck.getMessage();
            return message;
        }

        try {
            result = addEmployee(firstName, lastName);
        } catch (EmployeeStorageIsFullException employeeStorageIsFullException) {
            message = message + employeeStorageIsFullException.getMessage();
        } catch (EmployeeAlreadyAddedException employeeAlreadyAddedException) {
            message = message + employeeAlreadyAddedException.getMessage();

        } finally {
            return message;
        }
    }


    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public String removeEmployeeJson(String firstname, String lastName) {
        NameCheck nameCheck = new NameCheck(firstname, lastName);
        if (nameCheck.getCode() != 0) {
            return nameCheck.getMessage();
        }
        Employee employee = new Employee(firstname, lastName);
        if (removeEmployee(firstname, lastName)) {
            return employee.toJSON();
        } else {
            return "... whether it is OK?";
        }

    }

    class NameCheck {

        private String firstName;
        private String lastName;
        private int code;
        private String message;

        public NameCheck(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;

            /*
        0 - OK
        1 - both null
        2 - first OK, last null
        3 - first null, last OK
         */
            if (firstName != null && lastName != null) {
                code = 0;
                message = (new Employee(firstName, lastName)).toJSON();
            }
            if (firstName == null && lastName == null) {
                code = 1;
                message = "first and last names are missing";
            }
            if (firstName != null && lastName == null) {
                code = 2;
                message = "last name is missing";
            }
            if (firstName == null && lastName != null) {
                code = 3;
                message = "first name is missing";
            }
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getCode() {
            return code;
        }
        
        public String getMessage() {
            return message;
        }

        public Employee getEmployee() {
            return new Employee(firstName, lastName);
        }
    }

}