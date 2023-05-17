package pro.sky.course2lesson6listshomework;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService = new EmployeeService(2);

    @GetMapping()
    public String welcome() {
        return employeeService.welcome();
    }

    @GetMapping(path = "/add")
    public String add(@RequestParam(required = false, name = "firstName") String f,
                      @RequestParam(required = false, name = "lastName") String l) {
        return employeeService.addEmployeeJSON(f, l);
    }

    @GetMapping(path = "/remove")
    public Employee remove(
            @RequestParam(required = false, name = "firstName") String firstName,
            @RequestParam(required = false, name = "lastName") String lastName
    ) {
        return new Employee(firstName, lastName);
    }

    @GetMapping(path = "/list")
    public Set<Employee> list() {
        return employeeService.getEmployeeList();
    }

    @GetMapping(path = "/employee")
    public String employee() {
        return "employee";
    }

}
