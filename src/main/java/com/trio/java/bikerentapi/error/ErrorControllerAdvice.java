package com.trio.java.bikerentapi.error;

import static com.trio.java.bikerentapi.util.Constants.UNEXPECTED_ERROR_MESSAGE;

import com.trio.java.bikerentapi.exception.BikeNotFoundException;
import com.trio.java.bikerentapi.exception.InvalidCustomerDataException;
import com.trio.java.bikerentapi.exception.MaxBikeLoadExceededException;
import com.trio.java.bikerentapi.exception.UnavailableBikeException;
import com.trio.java.bikerentapi.exception.UserAlreadyExistsException;
import com.trio.java.bikerentapi.exception.UserNotFoundException;
import java.util.HashSet;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler({BikeNotFoundException.class, UserNotFoundException.class})
  public ResponseEntity<ErrorResponse> handleNotFoundExceptions(RuntimeException ex) {
    log.warn(ex.getMessage(), ex);
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler({MaxBikeLoadExceededException.class, InvalidCustomerDataException.class})
  public ResponseEntity<ErrorResponse> handleBadRequests(RuntimeException ex) {
    return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler({UnavailableBikeException.class, UserAlreadyExistsException.class})
  public ResponseEntity<ErrorResponse> handleConflicts(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(ex.getMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    val errors = new HashSet<ErrorResponse>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      val fieldName = ((FieldError) error).getField();
      val errorMessage = error.getDefaultMessage();
      val stringBuilder = new StringBuilder();
      errors.add(new ErrorResponse(
          stringBuilder.append(fieldName).append(" ").append(errorMessage).toString()));
    });
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
    log.error(UNEXPECTED_ERROR_MESSAGE, ex);
    return ResponseEntity.internalServerError()
        .body(new ErrorResponse(UNEXPECTED_ERROR_MESSAGE));
  }
}
