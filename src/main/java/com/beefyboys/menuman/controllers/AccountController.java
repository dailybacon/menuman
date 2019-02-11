package com.beefyboys.menuman.controllers;

import com.beefyboys.menuman.models.Account;
import com.beefyboys.menuman.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public boolean addAccount(@RequestBody @Valid Account account) { return accountService.addAccount(account);}

}
