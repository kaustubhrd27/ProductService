package com.example.productservice.commons;

import com.example.productservice.DTOs.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AunthenticationCommons {

    @Autowired
    private RestTemplate restTemplate;

    public UserDto validateToken(String token) {
        // now we need to call userService
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity("http://localhost:3030/users/validate/" + token,null, UserDto.class);

        if (responseEntity.getBody() == null) {
            //token is invalid
            return null;
        }

        return responseEntity.getBody();
    }

}
