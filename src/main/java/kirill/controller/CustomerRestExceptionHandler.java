package kirill.controller;


import kirill.entity.errors.CustomerErrorResponse;
import kirill.entity.errors.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler  {

  @ExceptionHandler
  public ResponseEntity<CustomerErrorResponse> hanldeException(CustomerException ex)  {
    CustomerErrorResponse response =
            new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<CustomerErrorResponse> handleAllExceptions(Exception ex)  {
    CustomerErrorResponse response =
            new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
