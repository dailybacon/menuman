package com.beefyboys.menuman.controllers;

import com.beefyboys.menuman.models.Account;
import com.beefyboys.menuman.models.NewAccount;
import com.beefyboys.menuman.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/init")
    public ResponseEntity<Account> init(){
        NewAccount newAccount = new NewAccount();
            newAccount.setUsername("administrator");
        newAccount.setPassword("administrator");
        newAccount.setAddress("100 Admin Ave");
        return created(accountService.addAccount(newAccount, true));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Account> signUp(@RequestBody @Valid NewAccount account){
        return created(accountService.addAccount(account, false));
    }

    @GetMapping("/{accountName}")
    @PreAuthorize("hasRole('ADMIN') or #accountName == authentication.name")
    public ResponseEntity getAccount(@PathVariable("accountName") String accountName) {
        return ok(accountService.getAccount(accountName));
    }

//    @PutMapping("/{accountName")
//    @PreAuthorize("hasRole('ADMIN') or #accountName == authentication.name")
//    public ResponseEntity updateAccount(@PathVariable("accountName") String accountName,
//                                        @RequestBody @Valid Account updatedAccount) {
//        return ok(accountService.updateAccount(accountName, updatedAccount));
//    }

    @DeleteMapping("/{accountName}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #accountName == authentication.name")
    public ResponseEntity deleteAccount(@PathVariable("accountName") String accountName) {
        accountService.deleteAccount(accountName);
        return noContent();
    }

    public ResponseEntity ok(Object object) {
        return ResponseEntity.ok(object);
    }

    public ResponseEntity created(Object object) {
        return ResponseEntity.status(HttpStatus.CREATED).body(object);
    }

    public ResponseEntity noContent() {
        return ResponseEntity.noContent().build();
    }

}
