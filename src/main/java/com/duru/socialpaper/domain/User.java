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
import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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
            this.token = jwtService.create("social", this.email ,"userAuthentication");
            log.debug("User makeJwtToken {}", token);
    }

    public UserDto toUserDto(){
        return new UserDto(email,token,username,bio,image);
    }


    public void update(UserDto userDto) {
        this.email = Optional.ofNullable(userDto.getEmail()).orElse(this.email);
        this.bio = Optional.ofNullable(userDto.getBio()).orElse(this.bio);
        this.image = Optional.ofNullable(userDto.getImage()).orElse(this.image);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (token != null ? !token.equals(user.token) : user.token != null) return false;
        if (bio != null ? !bio.equals(user.bio) : user.bio != null) return false;
        return image != null ? image.equals(user.image) : user.image == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", bio='" + bio + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}
