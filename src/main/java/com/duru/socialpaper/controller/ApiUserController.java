package com.duru.socialpaper.controller;


import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.dto.UserDto;
import com.duru.socialpaper.service.JwtService;
import com.duru.socialpaper.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "jwtService")
    private JwtService jwtService;

    @GetMapping("")
    public ResponseEntity<User> currentUser(){

        Map<String, Object> email =   jwtService.get("social");

        System.out.println(email.toString());

        return new ResponseEntity<User>(null);
    }


}
