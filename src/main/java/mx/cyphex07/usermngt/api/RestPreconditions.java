package mx.cyphex07.usermngt.api;

import mx.cyphex07.usermngt.exception.BadRequestException;
import mx.cyphex07.usermngt.exception.ResourceNotFoundException;

/**
 *
 * Simple static methods to be called at the start of your own methods to verify
 * correct arguments and state. If the Precondition fails, an {@link org.springframework.http.HttpStatus}
 * code is thrown
 *
 * @author Hector Silvar
 *
 */
public class RestPreconditions {
  private RestPreconditions() {
    throw new AssertionError();
  }

  // API

  /**
   * Ensures that an object reference passed as a parameter to the calling
   * method is not null.
   *
   * @param reference
   *            an object reference
   *
   * @return the non-null reference that was validated
   *
   * @throws ResourceNotFoundException
   *             if {@code reference} is null
   */
  public static <T> T checkNotNull(final T reference) {
    return checkNotNull(reference, null);
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling
   * method is not null.
   *
   * @param reference
   *            an object reference
   * @param message
   *            the message of the exception if the check fails
   *
   * @return the non-null reference that was validated
   *
   * @throws ResourceNotFoundException
   *             if {@code reference} is null
   */
  public static <T> T checkNotNull(final T reference, final String message) {
    if (reference == null) {
      throw new ResourceNotFoundException(message);
    }
    return reference;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling
   * method is not null.
   *
   * @param reference
   *            an object reference
   * @return the non-null reference that was validated
   *
   * @throws BadRequestException
   *             if {@code reference} is null
   */
  public static <T> T checkRequestElementNotNull(final T reference) {
    return checkRequestElementNotNull(reference, null);
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling
   * method is not null.
   *
   * @param reference
   *            an object reference
   * @param message
   *            the message of the exception if the check fails
   *
   * @return the non-null reference that was validated
   *
   * @throws BadRequestException
   *             if {@code reference} is null
   */
  public static <T> T checkRequestElementNotNull(final T reference, final String message) {
    if (reference == null) {
      throw new BadRequestException(message);
    }
    return reference;
  }

  /**
   * Ensures the truth of an expression related to the validity of the request
   *
   * @param expression
   *            a boolean expression
   *
   * @throws BadRequestException
   *             if {@code expression} is false
   */
  public static void checkIfBadRequest(final boolean expression) {
    checkIfBadRequest(expression, null);
  }

  /**
   * Ensures the truth of an expression related to the validity of the request
   *
   * @param expression
   *            a boolean expression
   * @param message
   *            the message of the exception if the check fails
   *
   * @throws BadRequestException
   *             if {@code expression} is false
   */
  public static void checkIfBadRequest(final boolean expression, final String message) {
    if (!expression) {
      throw new BadRequestException(message);
    }
  }

  /**
   * Check if some value was found, otherwise throw exception.
   *
   * @param expression
   *            has value true if found, otherwise false
   *
   * @throws ResourceNotFoundException
   *             if expression is false, means value not found.
   */
  public static void checkFound(final boolean expression) {
    checkFound(expression, null);
  }

  /**
   * Check if some value was found, otherwise throw exception.
   *
   * @param expression
   *            has value true if found, otherwise false
   * @param message
   *            the message of the exception if the check fails
   *
   * @throws ResourceNotFoundException
   *             if expression is false, means value not found.
   */
  public static void checkFound(final boolean expression, final String message) {
    if (!expression) {
      throw new ResourceNotFoundException(message);
    }
  }

  /**
   * Check if some value was found, otherwise throw exception.
   *
   * @param resource
   *            has value true if found, otherwise false
   *
   * @throws ResourceNotFoundException
   *             if expression is false, means value not found.
   */
  public static <T> T checkFound(final T resource) {
    return checkFound(resource, null);
  }

  /**
   * Check if some value was found, otherwise throw exception.
   *
   * @param resource
   *            has value true if found, otherwise false
   * @param message
   *            the message of the exception if the check fails
   *
   * @throws ResourceNotFoundException
   *             if expression is false, means value not found.
   */
  public static <T> T checkFound(final T resource, final String message) {
    if (resource == null) {
      throw new ResourceNotFoundException(message);
    }

    return resource;
  }

}
