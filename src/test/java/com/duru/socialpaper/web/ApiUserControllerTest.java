package com.duru.socialpaper.web;

import com.duru.socialpaper.domain.User;
import com.duru.socialpaper.dto.UserDto;
import com.duru.socialpaper.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiUserControllerTest extends AcceptanceTest {


    @Resource(name = "userService")
    private UserService userService;


    @Test
    public void currentUser() throws JsonProcessingException {

        UserDto user = new UserDto("Jacob", "jake@jake.jake", "jakejake");

        String userJSON = om.writeValueAsString(user);
        userJSON = "{\"user\":" + userJSON + "}";

        webTestClient.post().uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(userJSON))
                .exchange();


        HttpHeaders responseHeaders =
        webTestClient.post().uri("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(userJSON))
                .exchange()
                .returnResult(HttpHeaders.class).getResponseHeaders();


        List<String> jwt =responseHeaders.get("Authorization");

        User currentUser =
                webTestClient.get().uri("/api/user")
                        .header("Authorization", jwt.get(0))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(User.class)
                        .returnResult().getResponseBody();

        assertThat(currentUser.getEmail()).isEqualTo("jake@jake.jake");

    }

    @Test
    public void updateUser() throws JsonProcessingException {
        UserDto user = new UserDto("Jacob", "jake@jake.jake", "1234");

        String userJSON = om.writeValueAsString(user);
        userJSON = "{\"user\":" + userJSON + "}";

        webTestClient.post().uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(userJSON))
                .exchange();


        HttpHeaders responseHeaders =
                webTestClient.post().uri("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(userJSON))
                        .exchange()
                        .returnResult(HttpHeaders.class).getResponseHeaders();

        List<String> jwt =responseHeaders.get("Authorization");

        user = new UserDto("durin2@gmail.com", "12345");
        userJSON = om.writeValueAsString(user);
        userJSON = "{\"user\":" + userJSON + "}";

        User response = webTestClient.put().uri("/api/user")
                .header("Authorization", jwt.get(0))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(userJSON))
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        assertThat(response.getEmail()).isEqualTo("durin2@gmail.com");
        assertThat(response.getPassword()).isEqualTo("12345");
    }

}
