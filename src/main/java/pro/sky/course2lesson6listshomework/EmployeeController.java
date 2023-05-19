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
    public Employee add(@RequestParam(required = false, name = "firstName") String f,
                        @RequestParam(required = false, name = "lastName") String l) {
        try {
            return employeeService.addEmployeeO(f, l);
        } catch (EmployeeAlreadyAddedException alreadyAdded) {
            return new Employee(f, l + " " + alreadyAdded.getMessage());
        } catch (EmployeeStorageIsFullException arrayIsFull) {
            return new Employee(f, l + " " + arrayIsFull.getMessage());
        } catch (WrongNameFormatException wrongNameFormat) {
            return new Employee(f, l + " " + wrongNameFormat.getMessage());
        }
    }

    @GetMapping(path = "/remove")
    public Employee remove(
            @RequestParam(required = false, name = "firstName") String firstName,
            @RequestParam(required = false, name = "lastName") String lastName
    ) {
        return employeeService.removeEmployeeO(firstName, lastName);
    }

    @GetMapping(path = "/list")
    public Set<Employee> list() {
        return employeeService.getEmployeeList();
    }

    @GetMapping(path = "/find")
    public Employee find(
            @RequestParam(required = false, name = "firstName") String first,
            @RequestParam(required = false, name = "lastName") String last) {
        return employeeService.findAndReturnEmployee(first, last);
    }

    @GetMapping(path = "/employee")
    public String employee() {
        return employeeService.welcome();
    }

}
