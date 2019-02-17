package com.beefyboys.menuman.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewAccount {

    @NotNull
    @NotEmpty
    String username;

    @NotNull
    @NotEmpty
    String address;

    @NotNull
    @NotEmpty
    String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
