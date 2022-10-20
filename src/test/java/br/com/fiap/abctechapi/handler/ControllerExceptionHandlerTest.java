package br.com.fiap.abctechapi.handler;

import br.com.fiap.abctechapi.handler.exception.MaxAssistException;
import br.com.fiap.abctechapi.handler.exception.MinAssistRequiredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@SpringBootTest
class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new ControllerExceptionHandler();
    }

    @DisplayName("MinAssistRequiredException :: success scenario")
    @Test
    public void MinAssistRequiredException() {
        MinAssistRequiredException exception = new MinAssistRequiredException("msg","description");

        ErrorMessageResponse error = new ErrorMessageResponse();
        error.setMessage(exception.getMessage());
        error.setDescription(exception.getDescription());
        error.setTimestamp(new Date());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());

        ResponseEntity<ErrorMessageResponse> result = controller.errorMaxAssist(exception);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getBody().getStatusCode());
        Assertions.assertEquals(error.getMessage(), result.getBody().getMessage());
        Assertions.assertEquals(error.getDescription(), result.getBody().getDescription());
    }

    @DisplayName("MaxAssistException :: success scenario")
    @Test
    public void MaxAssistException() {
        MaxAssistException exception = new MaxAssistException("msg","description");

        ErrorMessageResponse error = new ErrorMessageResponse();
        error.setMessage(exception.getMessage());
        error.setDescription(exception.getDescription());
        error.setTimestamp(new Date());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());

        ResponseEntity<ErrorMessageResponse> result = controller.errorMaxAssist(exception);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getBody().getStatusCode());
        Assertions.assertEquals(error.getMessage(), result.getBody().getMessage());
        Assertions.assertEquals(error.getDescription(), result.getBody().getDescription());
        Assertions.assertEquals(error.getTimestamp(), result.getBody().getTimestamp());
    }

}