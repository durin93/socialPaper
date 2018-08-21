package com.duru.socialpaper.web;

import com.duru.socialpaper.domain.User;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserControllerTest extends  AcceptanceTest{


    @Test
    public void regist(){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, Object> value = new HashMap<>();
        value.put("userName", "Jacob");
        value.put("email", "jake@jake.jake");
        value.put("password","jakejake");

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(value,headers);

        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/users" , request, User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(new User("Jacob","jake@jake.jake","jakejake"));

    }



}
