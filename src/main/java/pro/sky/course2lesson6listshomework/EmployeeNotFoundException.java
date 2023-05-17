package pro.sky.course2lesson6listshomework;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException() {
        super("EmployeeNotFoundException");
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
