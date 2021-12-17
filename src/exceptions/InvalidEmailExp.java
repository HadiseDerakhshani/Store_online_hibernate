package exceptions;

public class InvalidEmailExp extends RuntimeException {
    public InvalidEmailExp(String message) {
        super(message);
    }
}
