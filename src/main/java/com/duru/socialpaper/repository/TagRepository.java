package com.duru.socialpaper.repository;

import com.duru.socialpaper.domain.Article;
import com.duru.socialpaper.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByTag(String tag);
}
