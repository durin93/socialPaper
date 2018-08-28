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
        return new ResponseEntity<User>(userService.currentUser(jwtService.get("social")),HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody Map<String,UserDto> data){
        return new ResponseEntity<User>(userService.update(jwtService.get("social"),data.get("user")),HttpStatus.OK);
    }


}
