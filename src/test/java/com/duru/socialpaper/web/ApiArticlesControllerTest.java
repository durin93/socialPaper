package com.duru.socialpaper.web;

import com.duru.socialpaper.domain.Article;
import com.duru.socialpaper.domain.Profile;
import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.dto.ArticleDto;
import com.duru.socialpaper.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiArticlesControllerTest extends AcceptanceTest {

    @Test
    public void post_articles() throws JsonProcessingException {

        UserDto loginUser = new UserDto("사루", "saru@gmail.com", "1234");
        String loginUserJSON = om.writeValueAsString(loginUser);
        loginUserJSON = "{\"user\":" + loginUserJSON + "}";


        String loginUserToken =
        webTestClient.post().uri("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(loginUserJSON))
                .exchange()
                .expectBody(User.class)
                .returnResult().getResponseBody().getToken();


        List<String> tagList = Arrays.asList("reactjs","angularjs","dragons");

        ArticleDto articleDto = new ArticleDto("title","description","body", tagList);

        String articleJSON = om.writeValueAsString(articleDto);
        articleJSON = "{\"article\":" + articleJSON + "}";



        Article article =
                webTestClient.post().uri("/api/articles")
                        .header("Authorization","Token "+loginUserToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(articleJSON))
                        .exchange()
                        .expectStatus().isCreated()
                        .expectBody(Article.class)
                        .returnResult().getResponseBody();

    }



}
