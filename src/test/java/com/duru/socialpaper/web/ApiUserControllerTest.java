package com.duru.socialpaper.web;

import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.*;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserControllerTest extends  AcceptanceTest{

    private ObjectMapper om = new ObjectMapper();

    @Test
    public void login() throws JsonProcessingException {
        UserDto user = new UserDto("durin@gmail.com", "1234");
        String userJSON = om.writeValueAsString(user);

        User loginUser =
        webTestClient.post().uri("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(userJSON))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().exists("Authorization")
                .expectBody(User.class)
                .returnResult().getResponseBody();

        assertThat(loginUser.getEmail()).isEqualTo("durin@gmail.com");
        assertThat(loginUser.getPassword()).isEqualTo("1234");


    }

    @Test
    public void regist() throws JsonProcessingException {


        UserDto user = new UserDto("Jacob", "jake@jake.jake", "jakejake");
        String userJSON = om.writeValueAsString(user);


                webTestClient.post().uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(userJSON))
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().doesNotExist("Authorization")
                .expectBody(User.class)
                .isEqualTo(new User("Jacob","jake@jake.jake","jakejake"));
    }



}
