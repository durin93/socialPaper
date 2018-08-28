package com.duru.socialpaper.controller;


import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.dto.UserDto;
import com.duru.socialpaper.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class ApiUsersController {

    private static final Logger log = LoggerFactory.getLogger(ApiUsersController.class);


    @Resource(name = "userService")
    private UserService userService;


    @PostMapping("")
    public ResponseEntity<User> registration(@RequestBody Map<String,UserDto> data){
        log.info("ApiUserController registration {}", data.get("user").toString());
        return new ResponseEntity<User>(userService.registration(data.get("user")),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> authentication(@RequestBody Map<String,UserDto> data){
        log.info("ApiUserController authentication {} ", data.get("user").toString());

        HttpHeaders headers = new HttpHeaders();
        User user =   userService.authentication(data.get("user"));
        headers.set("Authorization", "Token "+user.getToken());

        return new ResponseEntity<>(user,headers,HttpStatus.OK);
    }


}
