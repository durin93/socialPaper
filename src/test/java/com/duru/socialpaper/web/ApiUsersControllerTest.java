package com.duru.socialpaper.web;

import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUsersControllerTest extends AcceptanceTest {


    @Test
    public void login() throws JsonProcessingException {
        UserDto user = new UserDto("durin", "durin@gmail.com", "1234");
        String userJSON = om.writeValueAsString(user);
        userJSON = "{\"user\":" + userJSON + "}";


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
        userJSON = "{\"user\":" + userJSON + "}";


        User registUser =
                webTestClient.post().uri("/api/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(userJSON))
//                .body(Mono.just(userJSON), String.class)
                        .exchange()
                        .expectStatus().isCreated()
                        .expectBody(User.class).returnResult().getResponseBody();

        assertThat(registUser.getEmail()).isEqualTo("jake@jake.jake");
        assertThat(registUser.getUsername()).isEqualTo("Jacob");
    }

    @Test
    public void follow() throws JsonProcessingException {

    }
}
