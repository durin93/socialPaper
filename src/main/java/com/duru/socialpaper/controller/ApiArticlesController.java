package com.duru.socialpaper.controller;


import com.duru.socialpaper.domain.Article;
import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.dto.ArticleDto;
import com.duru.socialpaper.dto.UserDto;
import com.duru.socialpaper.service.ArticleService;
import com.duru.socialpaper.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/articles")
public class ApiArticlesController {

    private static final Logger log = LoggerFactory.getLogger(ApiArticlesController.class);


    @Resource(name = "articleService")
    private ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<Article> create(@RequestBody Map<String,ArticleDto> data){
        log.info("ApiArticlesController create {}", data.get("article").toString());
        return new ResponseEntity<>(articleService.create(data.get("article")),HttpStatus.CREATED);
    }


}
