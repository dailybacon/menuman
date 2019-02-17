package com.beefyboys.menuman.controllers;

import com.beefyboys.menuman.models.Account;
import com.beefyboys.menuman.models.NewAccount;
import com.beefyboys.menuman.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/sign-up")
    public Account signUp(@RequestBody @Valid NewAccount account){
        return accountService.addAccount(account);
    }

    @GetMapping("/account/{accountName}")
    public Account getAccount(@PathVariable("accountName") String accountName) {
      return accountService.getAccount(accountName);
    }



}
