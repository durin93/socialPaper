package com.duru.socialpaper.domain;


import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@JsonTypeName("profile")
public class Profile {

    private String username;

    private String bio;

    private String image;

    private Boolean following;

    public Profile(){

    }

    public Profile(String username, String bio, String image, boolean following) {
        this.username = username;
        this.bio = bio;
        this.image = image;
        this.following = following;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", image='" + image + '\'' +
                ", following=" + following +
                '}';
    }
}
