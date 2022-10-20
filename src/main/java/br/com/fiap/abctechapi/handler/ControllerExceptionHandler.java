package br.com.fiap.abctechapi.handler;

import br.com.fiap.abctechapi.handler.exception.MaxAssistException;
import br.com.fiap.abctechapi.handler.exception.MinAssistRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MinAssistRequiredException.class)
    public ResponseEntity<ErrorMessageResponse> errorMaxAssist(MinAssistRequiredException exception) {
        return getErrorMessageResponseEntity(exception, exception.getDescription());
    }

    @ExceptionHandler(MaxAssistException.class)
    public ResponseEntity<ErrorMessageResponse> errorMaxAssist(MaxAssistException exception) {
        return getErrorMessageResponseEntity(exception, exception.getDescription());
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponseEntity(Exception exception, String description) {
        ErrorMessageResponse error = new ErrorMessageResponse();
        error.setMessage(exception.getMessage());
        error.setDescription(description);
        error.setTimestamp(new Date());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<ErrorMessageResponse>(error, HttpStatus.BAD_REQUEST);
    }
}
