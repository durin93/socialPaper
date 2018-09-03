package com.duru.socialpaper.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Tag extends AbstractEntity{

    private String tag;

    @ManyToMany
    @JoinTable
    private List<Article> Articles;

    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public List<Article> getArticles() {
        return Articles;
    }
}
