package com.electronics.store.electronocs_Store.exception;

import com.electronics.store.electronocs_Store.dto.APIResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
    logger.info("Exception Handler Invoke");
    APIResponseMessage response  = APIResponseMessage.builder()
            .message(ex.getMessage())
            .httpStatus(HttpStatus.NOT_FOUND)
            .success(true)
            .build();
    return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

}
//MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    List<ObjectError> allError = ex.getBindingResult().getAllErrors();
    Map<String,Object> response = new HashMap<>();
    allError.stream().forEach(objectError -> {
        String message = objectError.getDefaultMessage();
        String field = ((FieldError)objectError).getField();
        response.put(field,message);
    });
    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    //handle Bad Request Exception
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<APIResponseMessage> handleBadRequestException(BadRequestException ex){
        logger.info("Bad API Request ");
        APIResponseMessage response  = APIResponseMessage.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .success(false)
                .build();
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }
}
