package com.duru.socialpaper.service;

import com.duru.socialpaper.domain.Article;
import com.duru.socialpaper.domain.Tag;
import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.dto.ArticleDto;
import com.duru.socialpaper.repository.ArticleRepository;
import com.duru.socialpaper.repository.TagRepository;
import com.duru.socialpaper.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {

    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);

    @Resource(name = "articleRepository")
    private ArticleRepository articleRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "tagRepository")
    private TagRepository tagRepository;

    @Resource(name = "jwtService")
    private JwtService jwtService;

    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(NullPointerException::new);
    }

    public Article create(ArticleDto articleDto) {
        log.debug("create article {}", articleDto.toString());
        Article article = articleDto.toArticle();
        article.writeBy(findByUserEmail(jwtService.get("social")));
        for (String tagWord : articleDto.getTagList()) {
            if (!tagRepository.findByTag(tagWord).isPresent()) {
                tagRepository.save(new Tag(tagWord));
            }
            article.addTag(tagRepository.findByTag(tagWord).get());
        }


        return articleRepository.save(article);
    }


}
