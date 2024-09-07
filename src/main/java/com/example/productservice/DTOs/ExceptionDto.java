package com.example.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDto extends Exception{
    private String message;
    private Long id;
    private String details;
}
