package com.wissem.ecommerce.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

    private User user;
    private String jwtToken;
}
