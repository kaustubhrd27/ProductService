package com.example.productservice.Advices;

import com.example.productservice.DTOs.ArithmeticExceptionDto;
import com.example.productservice.DTOs.ArrayIndexOutOfBoundExceptionDto;
import com.example.productservice.DTOs.ExceptionDto;
import com.example.productservice.DTOs.InvalidProductIdResponseDto;
import com.example.productservice.Exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmaticException() {
        ArithmeticExceptionDto dto = new ArithmeticExceptionDto();
        dto.setMessage("Arithmetic Exception");
        dto.setDetails("Arithmetic Exception");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    /*public ResponseEntity<ArrayIndexOutOfBoundExceptionDto> handleArrayIndexOutOfBoundException() {
        return null;
    }
*/
    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<InvalidProductIdResponseDto> handleInvalidProductIdException(InvalidProductIdException invalidProductIdException) {
        InvalidProductIdResponseDto dto = new InvalidProductIdResponseDto();
        dto.setProductId(invalidProductIdException.getId());
        dto.setMessage(invalidProductIdException.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
