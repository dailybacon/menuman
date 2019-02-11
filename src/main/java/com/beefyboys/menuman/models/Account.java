package com.beefyboys.menuman.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Account {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer id;

    @NotNull
    @NotEmpty
    String username;

    @NotNull
    @NotEmpty
    String address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
