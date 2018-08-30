package com.duru.socialpaper.controller;


import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.dto.UserDto;
import com.duru.socialpaper.service.JwtService;
import com.duru.socialpaper.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);

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
        log.debug(data.toString());

        return new ResponseEntity<User>(userService.update(jwtService.get("social"),data.get("user")),HttpStatus.OK);
    }


}
