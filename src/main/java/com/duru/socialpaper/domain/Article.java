package com.duru.socialpaper.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonTypeName("article")
public class Article extends  AbstractEntity {

    private String slug;

    @Column(nullable = false)
    private String title;

    private String description;

    private String body;

    @ManyToMany
    @JoinTable
    private List<Tag> tagList = new ArrayList<>();

    private String createdAt;

    private String updatedAt;

    private Boolean favorited = false;

    private int favoritesCount = 0;



    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_article_author"))
    private User author;


    public void addTag(Tag tag) {

        if(!tagList.contains(tag)){
            tagList.add(tag);
        }

    }


    public  Article(){

    }

    public Article(String title, String description, String body) {
        this.title = title;
        this.description = description;
        this.body =body;
    }


    public static Article of(String title, String description, String body) {
        return new Article(title,description,body);
    }

    public String getSlug() {
        return slug;
    }

    public List<String> getTagList() {
        List<String> tagStrings = new ArrayList<>();
        for (Tag tag:tagList) {
            tagStrings.add(tag.getTag());
        }
        return tagStrings;
    }

    public String getCreatedAt() {
        return getCreateDate();
    }

    public String getUpdatedAt() {
        return getModifiedDate();
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }



    public Boolean getFavorited() {
        return favorited;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public Profile getAuthor() {
        return author.toProfile();
    }

    public void writeBy(User author) {
        this.author = author;
    }

}



