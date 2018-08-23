package com.duru.socialpaper.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Errors {

    private List<String> body;

    public Errors(List<String> body) {
        this.body = body;
    }

    public static Errors of(String message){
        List<String> body = new ArrayList<>();
        body.add(message);
       return new Errors(body);
    }


    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Errors errors = (Errors) o;
        return Objects.equals(body, errors.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(body);
    }

    @Override
    public String toString() {
        return "Errors{" +
                "body=" + body +
                '}';
    }
}
