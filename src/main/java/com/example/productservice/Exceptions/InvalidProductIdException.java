package com.example.productservice.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidProductIdException extends Exception{
    private Long id;
    public InvalidProductIdException(Long productId, String message){
        super(message);
        this.id = productId;
    }
}
