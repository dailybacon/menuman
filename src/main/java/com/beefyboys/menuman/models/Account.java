package com.beefyboys.menuman.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Account {

    private int id;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
