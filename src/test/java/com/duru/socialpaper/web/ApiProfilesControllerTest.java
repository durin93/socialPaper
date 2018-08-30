package com.duru.socialpaper.web;

import com.duru.socialpaper.domain.Profile;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.MediaType;

public class ApiProfilesControllerTest extends  AcceptanceTest{


    @Test
    public void getProfile() throws JsonProcessingException {


        Profile profile =
        webTestClient.get().uri(" /api/profiles/두린이")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Profile.class)
                .returnResult().getResponseBody();



    }



}
