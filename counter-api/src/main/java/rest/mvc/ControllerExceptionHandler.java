package rest.mvc;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler
extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value={IOException.class})
    protected ResponseEntity<Object> handleIOException(Exception ex, WebRequest request) {
        String bodyOfResponse = "An error has occured while processing the request";
        return this.handleExceptionInternal(ex, (Object)bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value={Exception.class})
    protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        String bodyOfResponse = "An error has occured while processing the request";
        return this.handleExceptionInternal(ex, (Object)bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
