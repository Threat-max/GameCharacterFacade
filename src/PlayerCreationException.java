package facade;

// The only exception the client of the Facade has to know about.
public class PlayerCreationException extends RuntimeException {
    public PlayerCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
