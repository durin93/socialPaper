package com.duru.socialpaper.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class TagList {

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="fk_article_tag"))
    private List<Tag> tags = new ArrayList<>();

    public List<Tag> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public void add(Tag tag) {
        this.tags.add(tag);
    }
}
