package mx.cyphex07.usermngt.exception;

public class InvalidTokenConfirmationException extends RuntimeException {

  public InvalidTokenConfirmationException() {
    super();
  }

  public InvalidTokenConfirmationException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public InvalidTokenConfirmationException(final String message) {
    super(message);
  }

  public InvalidTokenConfirmationException(final Throwable cause) {
    super(cause);
  }
}
