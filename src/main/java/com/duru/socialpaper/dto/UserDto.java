package com.duru.socialpaper.dto;

import com.duru.socialpaper.domain.User;

import java.util.Objects;

public class UserDto {

    private String username;

    private String email;

    private String password;

    private String token;

    private String bio;

    private String image;


    public UserDto() {
    }


    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDto(String username, String email, String password) {
        this(email,password);
        this.username = username;
    }

    public UserDto(String email, String token, String username, String bio, String image) {
        this.email = email;
        this.username = username;
        this.token = token;
        this.bio = bio;
        this.image = image;
    }


    public User toUser() {
        return new User(username,email,password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(username, userDto.username) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, email, password);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}



