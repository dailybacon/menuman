package com.beefyboys.menuman.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NewAccount {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z_]+[a-zA-Z]$",
             message = "Username should fit pattern <letter><letters-and-underscores><letter>")
    String username;

    @NotNull
    @NotEmpty
    String address;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[^ ]{8,64}",
             message = "Password should be between 8-64 characters (spaces not allowed)")
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
