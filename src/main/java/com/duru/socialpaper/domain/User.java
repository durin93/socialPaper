package com.duru.socialpaper.domain;

import com.duru.socialpaper.dto.UserDto;
import com.duru.socialpaper.exception.NoAuthentication;
import com.duru.socialpaper.service.JwtService;
import com.duru.socialpaper.service.UserService;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

@Entity
public class User extends  AbstractEntity{

    private static final Logger log = LoggerFactory.getLogger(User.class);


    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String token;

    private String bio;

    private String image;

    public User() {
    }

    public User(String userName, String email, String password) {
        super(0L);
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
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

    public void setToken(String token) {
        this.token = token;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void matchPassword(UserDto userDto) {
        if(!this.password.equals(userDto.getPassword())){
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
        return Objects.equals(userName, user.userName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", bio='" + bio + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
