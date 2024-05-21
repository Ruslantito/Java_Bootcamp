package ex04;

public class IllegalTransactionException extends RuntimeException {
    public IllegalTransactionException(String errorMessage) {
        super(errorMessage);
    }
}
