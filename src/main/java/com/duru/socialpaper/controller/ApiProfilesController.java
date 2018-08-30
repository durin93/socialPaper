package com.duru.socialpaper.controller;


import com.duru.socialpaper.domain.Profile;
import com.duru.socialpaper.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/profiles")
public class ApiProfilesController {

    private static final Logger log = LoggerFactory.getLogger(ApiProfilesController.class);


    @Resource(name = "userService")
    private UserService userService;


    @PostMapping("{username}/follow")
    public ResponseEntity<Profile> follow(@PathVariable String username){
        return new ResponseEntity<>(userService.followUser(username),HttpStatus.OK);
    }


    @GetMapping("{username}")
    public ResponseEntity<Profile> getProfile(@PathVariable String username){
        return new ResponseEntity<>(userService.getProfile(username),HttpStatus.OK);
    }


}
