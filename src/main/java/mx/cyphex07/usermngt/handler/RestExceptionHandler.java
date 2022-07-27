package mx.cyphex07.usermngt.handler;

import mx.cyphex07.usermngt.exception.ApiError;
import mx.cyphex07.usermngt.exception.BadRequestException;
import mx.cyphex07.usermngt.exception.ResourceNotFoundException;
import mx.cyphex07.usermngt.exception.UserAlreadyExistException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {BadRequestException.class})
  public ResponseEntity<ApiError> resourceNotFoundException(BadRequestException ex, WebRequest request) {
    final ApiError apiError = message(HttpStatus.BAD_REQUEST, ex);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {ResourceNotFoundException.class})
  public ResponseEntity<ApiError> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    final ApiError apiError = message(HttpStatus.NOT_FOUND, ex);
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {UserAlreadyExistException.class})
  public ResponseEntity<ApiError> userAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
    final ApiError apiError = message(HttpStatus.BAD_REQUEST, ex);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  protected ApiError message(final HttpStatus httpStatus, final Exception ex) {
    final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
    final String devMessage = ex.getClass().getSimpleName();
    return new ApiError(httpStatus.value(), message, devMessage);
  }
}
