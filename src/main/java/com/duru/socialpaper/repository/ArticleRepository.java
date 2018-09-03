package com.duru.socialpaper.repository;

import com.duru.socialpaper.domain.Article;
import com.duru.socialpaper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
}
