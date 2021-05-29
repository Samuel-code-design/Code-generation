package io.swagger.api;

import io.swagger.model.dto.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value = {Exception.class})
//    protected ResponseEntity<Object> handleResponseException(Exception ex, WebRequest request){
//        ExceptionDTO dto
//    }


}
