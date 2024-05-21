package ex05;

public class IllegalTransactionException extends RuntimeException {
    public IllegalTransactionException(String errorMessage) {
        super(errorMessage);
    }
}
