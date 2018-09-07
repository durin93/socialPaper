package com.duru.socialpaper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity{

    private String tag;

    @ManyToMany( mappedBy = "tagList")
    @JsonIgnore
    private List<Article> articles = new ArrayList<>();

    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void addArticle(Article article) {

        if(!articles.contains(article)){
            articles.add(article);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Tag tag1 = (Tag) o;

        if (tag != null ? !tag.equals(tag1.tag) : tag1.tag != null) return false;
        return articles != null ? articles.equals(tag1.articles) : tag1.articles == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (articles != null ? articles.hashCode() : 0);
        return result;
    }
}
