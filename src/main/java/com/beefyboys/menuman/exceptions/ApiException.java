package com.beefyboys.menuman.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ApiException {

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Menu item not found!")
    public static class MenuItemNotFound extends RuntimeException {
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Section not found!")
    public static class SectionNotFound extends RuntimeException {
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Menu not found!")
    public static class MenuNotFound extends RuntimeException {
    }

}