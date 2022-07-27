package mx.cyphex07.usermngt.exception;

public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 6410787109430968820L;

  public BadRequestException() {
    super();
  }

  public BadRequestException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public BadRequestException(final String message) {
    super(message);
  }

  public BadRequestException(final Throwable cause) {
    super(cause);
  }
}
