package com.duru.socialpaper.domain;

import com.duru.socialpaper.dto.UserDto;
import com.duru.socialpaper.exception.NoAuthentication;
import com.duru.socialpaper.service.JwtService;
import com.duru.socialpaper.service.UserService;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Date;
import java.util.Objects;

@Entity
@JsonRootName("user")
public class User extends  AbstractEntity{

    private static final Logger log = LoggerFactory.getLogger(User.class);


    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Lob
    private String token;

    private String bio;

    private String image;

    public User() {
    }

    public User(String username, String email, String password) {
        super(0L);
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }


    public String getBio() {
        return bio;
    }


    public String getImage() {
        return image;
    }


    public void matchPassword(String password) {
        if(!this.password.equals(password)){
            throw new NoAuthentication("비밀번호가 틀립니다.");
        }
    }

    public void makeJwtToken(JwtService jwtService){
            this.token = jwtService.create("social",this,this.email);
            log.debug("User makeJwtToken {}", token);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", bio='" + bio + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
