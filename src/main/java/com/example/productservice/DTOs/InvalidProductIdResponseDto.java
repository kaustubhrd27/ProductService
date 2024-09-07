package com.example.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidProductIdResponseDto {
    private Long productId;
    private String message;

    public InvalidProductIdResponseDto(){
    }
    public InvalidProductIdResponseDto(Long productId, String message) {
        this.productId = productId;
        this.message = message;
    }
}