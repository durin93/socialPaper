package com.duru.socialpaper.service;

import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.domain.UserRepository;
import com.duru.socialpaper.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Resource(name="userRepository")
    private UserRepository userRepository;


    public User registration(UserDto userDto){

        User user = userDto.toUser();
        log.info("UserSerice registration {}", user.toString());
        log.debug("UserSerice registration {}", user.toString());
        return userRepository.save(user);
    }


}
