package pro.sky.course2lesson6listshomework;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException() {
        super("EmployeeStorageIsFullException");
    }

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }

}
