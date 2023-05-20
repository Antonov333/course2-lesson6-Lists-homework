package pro.sky.course2lesson6listshomework;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeService {

    private final int maxPersonnelNumber;

    private final Set<Employee> employeeList;

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

  /**  public boolean addEmployeeB(String firstName, String lastName) {
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
   */

    public boolean findEmployeeBoolean(String firstname, String lastname) {
        if (employeeList.contains(new Employee(firstname, lastname, "error"))) {
            return true;
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName, "error");

        NameCheck nameCheck = new NameCheck(firstName, lastName);

        if (nameCheck.getCode() != 0) {
            throw new WrongNameFormatException(nameCheck.getMessage());
        }

        if (findEmployeeBoolean(firstName, lastName)) {
            return new Employee(firstName, lastName, "enrolled");
        } // findEmployeeBoolean never returns 'false' but throws NotFound exception instead

        return new Employee("-", "- ", "... Looks like something went wrong");

    }

    public Employee removeEmployee(String firstName, String lastname) {
        Employee employee = new Employee(firstName, lastname, "removed");
        NameCheck nameCheck = new NameCheck(firstName, lastname);

        if (nameCheck.getCode() != 0) {
            throw new WrongNameFormatException(nameCheck.getMessage());
        }

        if (employeeList.remove(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("... this person has is not hired yet");
        }
    }

    public String welcome() {
        return "<h2>Welcome to homework Sets for Course 2 Lesson 6 ))</h2><br><br>" +
                "<a href=\"http://localhost:8080/employee/add/?firstName=John&lastName=Smith\"> Add employee John Smith </a> | " +
                "<a href=\"http://localhost:8080/employee/remove/?firstName=John&lastName=Smith\"> Remove employee John Smith </a> | " +
                "<a href=\"http://localhost:8080/employee/find/\"> Find employee </a> | " +
                "<a href=\"http://localhost:8080/employee/list\"> View employee list </a>";
    }

    public Employee addEmployee(String firstName, String lastName) {

        NameCheck nameCheck = new NameCheck(firstName, lastName);

        if (nameCheck.getCode() != 0) {
            throw new WrongNameFormatException(nameCheck.getMessage());
        }

        if (getPersonnelNumber() >= getMaxPersonnelNumber()) {
            throw new EmployeeStorageIsFullException("Personal array is full. No vacant position at the moment");
        }

        try {
            if (findEmployeeBoolean(firstName, lastName)) {
                throw new EmployeeAlreadyAddedException("already hired");
            }
        } catch (EmployeeNotFoundException employeeNotFound) {
            // it is OK if employee not found because we want to add him
        }

        Employee employee = new Employee(firstName, lastName, "enrolled");

        if (employeeList.add(employee)) {
            return employee;
        } else {
            throw new EmployeeAlreadyAddedException("already hired");
        }
    }


    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    class NameCheck {

        private final String firstName;
        private final String lastName;
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
                message = "OK";
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