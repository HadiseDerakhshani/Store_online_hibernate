package exceptions;

public class InvalidNameExp extends RuntimeException {
    public InvalidNameExp(String message) {
        super(message);
    }
}
