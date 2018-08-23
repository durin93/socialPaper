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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);


    @Resource(name = "userService")
    private UserService userService;


    @PostMapping("")
    public ResponseEntity<User> registration(@RequestBody UserDto userDto){
        log.info("ApiUserController registration {}", userDto.toString());
        return new ResponseEntity<User>(userService.registration(userDto),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> authentication(@RequestBody UserDto userDto){
        log.info("ApiUserController authentication {}", userDto.toString());

        HttpHeaders headers = new HttpHeaders();
        User user =   userService.authentication(userDto);
        headers.set("Authorization", "Token "+user.getToken());

        log.debug("앗" + headers.toString());

        Map<String,Object> map = new HashMap<>();
        map.put("user",user);

        return new ResponseEntity<Map<String,Object>>(map,headers,HttpStatus.OK);
    }


}
