package com.duru.socialpaper.service;

import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.domain.UserRepository;
import com.duru.socialpaper.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Resource(name="userRepository")
    private UserRepository userRepository;

    @Resource(name="jwtService")
    private JwtService jwtService;


    public User registration(UserDto userDto){

        User user = userDto.toUser();
        log.info("UserSerice registration {}", user.toString());
        log.debug("UserSerice registration {}", user.toString());
        user.makeJwtToken(jwtService);
        return userRepository.save(user);
    }


    public User authentication(UserDto userDto) {
        log.debug("UserService authentication {}", userDto.toString());
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(EntityNotFoundException::new);
        user.matchPassword(userDto.getPassword());
//        user.makeJwtToken(jwtService);
        return user;
    }


}
