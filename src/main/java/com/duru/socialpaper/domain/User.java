package com.duru.socialpaper.domain;

import com.duru.socialpaper.dto.UserDto;
import com.duru.socialpaper.exception.NoAuthentication;
import com.duru.socialpaper.service.JwtService;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@JsonTypeName("user")
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class User extends AbstractEntity {

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

    @ManyToMany
    @JoinTable
    private List<User> followings;

    public void add(User following) {
        followings.add(following);
    }

    public void delete(User following) {
        if (followings.contains(following)) {
            followings.remove(following);
        }
    }

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
        if (!this.password.equals(password)) {
            throw new NoAuthentication("비밀번호가 틀립니다.");
        }
    }

    public void makeJwtToken(JwtService jwtService) {
        this.token = jwtService.create("social", this.email, "userAuthentication");
        log.debug("User makeJwtToken {}", token);
    }

    public UserDto toUserDto() {
        return new UserDto(email, token, username, bio, image);
    }


    public Profile toProfile() {
        return new Profile(username, bio, image, false);
    }

    public Profile toProfile(boolean following) {
        return new Profile(username, bio, image, following);
    }

    public void update(UserDto userDto) {
        this.email = Optional.ofNullable(userDto.getEmail()).orElse(this.email);
        this.bio = Optional.ofNullable(userDto.getBio()).orElse(this.bio);
        this.image = Optional.ofNullable(userDto.getImage()).orElse(this.image);
        this.password = Optional.ofNullable(userDto.getPassword()).orElse(this.password);
        this.username = Optional.ofNullable(userDto.getUsername()).orElse(this.username);
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
