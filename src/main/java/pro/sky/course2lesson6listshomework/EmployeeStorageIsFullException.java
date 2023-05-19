package pro.sky.course2lesson6listshomework;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
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
