package com.beefyboys.menuman.models;

public class JwtResponse {

    String value;

    public JwtResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
