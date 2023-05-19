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

    public boolean addEmployeeB(String firstName, String lastName) {
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

    public Employee findAndReturnEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (findEmployee(firstName, lastName)) {
            return new Employee(firstName, lastName);
        } // findEmployee never returns 'false' but throws NotFound exception instead

        return new Employee("-", "-");

    }


    public boolean removeEmployeeB(String firstName, String lastname) {
        boolean b = false;
        if (findEmployee(firstName, lastname)) {
            b = employeeList.remove(new Employee(firstName, lastname));
        }
        return b;
    }

    public Employee removeEmployeeO(String firstName, String lastname) {
        Employee employee = new Employee(firstName, lastname);
        if (employeeList.remove(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("... this person has not been hired");
        }
    }


    public String welcome() {

        return "<h2>Welcome to homework Sets for Course 2 Lesson 6 ))</h2><br><br>";

    }

    public String addEmployeeJson(String firstName, String lastName) {
        String message = "Add Employee" + " " + firstName + " " + lastName + ": ";
        Boolean result = true;

        NameCheck nameCheck = new NameCheck(firstName, lastName);

        message = (new Employee(firstName, lastName)).toJson();

        if (nameCheck.getCode() != 0) {
            message = message + nameCheck.getMessage();
            return message;
        }

        result = addEmployeeB(firstName, lastName);

        return message;

    }

    public Employee addEmployeeO(String firstName, String lastName) {
        String message = "Add Employee" + " " + firstName + " " + lastName + ": ";

        NameCheck nameCheck = new NameCheck(firstName, lastName);

        if (nameCheck.getCode() != 0) {
            throw new WrongNameFormatException(nameCheck.getMessage());
        }

        Boolean result = addEmployeeB(firstName, lastName);

        if (result) {
            return new Employee(firstName, lastName);
        }

        return new Employee("-", "- !! looks like something went wrong (( ...");

    }


    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    /*
    public Employee removeEmployeeO(String firstname, String lastName) {
        NameCheck nameCheck = new NameCheck(firstname, lastName);
        if (nameCheck.getCode() != 0) {
            return nameCheck.getMessage();
        }
        Employee employee = new Employee(firstname, lastName);
        if (removeEmployee(firstname, lastName)) {
            return employee.toJson();
        } else {
            return "... whether it is OK?";
        }

    }

     */

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
                message = (new Employee(firstName, lastName)).toJson();
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


    }

}