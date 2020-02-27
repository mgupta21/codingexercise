package exception;

/**
 * Created by mgupta on 10/22/17.
 */
public class InvalidEventException extends RuntimeException {

    public InvalidEventException(String msg) {
        super(msg);
    }
}
