package pro.sky.course2lesson6listshomework;

public class EmployeeAlreadyAddedException extends RuntimeException {

    public EmployeeAlreadyAddedException() {
        super("EmployeeAlreadyAddedException");
    }

    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
