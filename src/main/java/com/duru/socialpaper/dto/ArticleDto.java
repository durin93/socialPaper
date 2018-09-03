package com.duru.socialpaper.dto;

import com.duru.socialpaper.domain.Article;
import com.duru.socialpaper.domain.User;

import java.util.ArrayList;
import java.util.List;

public class ArticleDto {

    private String title;

    private String description;

    private String body;

    private List<String> tagList;

    public ArticleDto() {
    }

    public ArticleDto(String title, String description, String body) {
        this.title = title;
        this.description = description;
        this.body = body;
    }

    public ArticleDto(String title, String description, String body, List<String> tagList) {
        this(title,description,body);
        this.tagList = tagList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Article toArticle(){
        return Article.of(title,description,body);
    }
}
