package com.duru.socialpaper.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonTypeName("article")
public class Article extends  AbstractEntity {

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_article_author"))
    private User author;

    private String slug;

    @Column(nullable = false)
    private String title;

    private String description;

    private String body;

    @ManyToMany
    @JoinTable
    private List<Tag> tagList;

    public void addTag(Tag tag) {
        tagList.add(tag);
    }

    private Boolean favorited = false;

    private int favoritesCount = 0;

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



