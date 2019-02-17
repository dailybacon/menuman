package com.beefyboys.menuman.services;

import com.beefyboys.menuman.exceptions.ApiException;
import com.beefyboys.menuman.models.Account;
import com.beefyboys.menuman.models.NewAccount;
import com.beefyboys.menuman.repository.AccountDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountDataStore repo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Account addAccount(NewAccount account) {
        String accountCheck = account.getUsername();
        if (repo.checkAccount(accountCheck) == true) {
            throw new ApiException.UsernameAlreadyExists();
        }
        return repo.addAccount(prepareNewAccount(account));

    }

    private Account prepareNewAccount(NewAccount newAccount) {
        Account account = new Account();
        account.setUsername(newAccount.getUsername());
        account.setAddress(newAccount.getAddress());
        account.setPasswordHash(passwordEncoder.encode(newAccount.getPassword()));
        return account;
    }

    public Account getAccount(String accountName) {
        return repo.getAccount(accountName);
    }


}
