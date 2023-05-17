package pro.sky.course2lesson6listshomework;

import org.springframework.stereotype.Service;

@Service
public class MySpringWebService {

    public String welcome() {

        EmployeeService e = new EmployeeService();

//        Employee john = new Employee("John", "Smith") ;
//        System.out.println("john.equals(new Employee(\"John\", \"Smith\")) = " + john.equals(new Employee("John", "Smith")));

        return "<h2>Welcome to homework Sets for Course 2 Lesson 6 ))</h2><br><br>" +
                "e.getPersonnelNumber() = " + e.getPersonnelNumber();

    }

}
