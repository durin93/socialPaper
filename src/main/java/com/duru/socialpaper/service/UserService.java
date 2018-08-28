package com.duru.socialpaper.service;

import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.domain.UserRepository;
import com.duru.socialpaper.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Resource(name="userRepository")
    private UserRepository userRepository;

    @Resource(name="jwtService")
    private JwtService jwtService;


    public User registration(UserDto userDto){
        User user = userDto.toUser();
        user.makeJwtToken(jwtService);
        log.debug("UserService registration {}", user.toString());
        return userRepository.save(user);
    }


    public User authentication(UserDto userDto) {
        log.debug("UserService authentication {}", userDto.toString());
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(EntityNotFoundException::new);
        user.matchPassword(userDto.getPassword());
        return user;
    }


    public User currentUser(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(EntityNotFoundException::new);
    }

    public User update(String userEmail, UserDto userDto) {
        log.debug("UserService update {} to {}", userEmail, userDto.toString());
        User user = userRepository.findByEmail(userEmail).orElseThrow(EntityNotFoundException::new);
        user.update(userDto);
        return user;
    }
}
