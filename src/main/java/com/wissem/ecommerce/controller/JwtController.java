package com.wissem.ecommerce.jwt.controller;


import com.wissem.ecommerce.jwt.entity.JwtRequest;
import com.wissem.ecommerce.jwt.entity.JwtResponse;
import com.wissem.ecommerce.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

}
