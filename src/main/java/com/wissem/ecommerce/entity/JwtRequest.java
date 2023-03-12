package com.wissem.ecommerce.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JwtRequest {

    private String userName;
    private String userPassword;


}
