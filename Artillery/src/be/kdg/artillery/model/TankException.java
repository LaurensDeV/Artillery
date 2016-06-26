package be.kdg.artillery.model;

/**
 * Robby Dirix & Laurens de Voogd
 * 18-Feb-16
 */
public class TankException extends RuntimeException {
    String message;

    public TankException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
