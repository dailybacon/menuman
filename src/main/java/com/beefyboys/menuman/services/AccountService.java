package com.beefyboys.menuman.services;

import com.beefyboys.menuman.exceptions.ApiException;
import com.beefyboys.menuman.models.Account;
import com.beefyboys.menuman.repository.AccountDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountDataStore repo;

    public boolean addAccount(Account account) {
        String accountCheck = account.getUsername();
        if (repo.checkAccount(accountCheck) == true) {
            throw new ApiException.UsernameAlreadyExists();
        }
        return repo.addAccount(account);

    }

    public Account getAccount(String accountName) {
        return repo.getAccount(accountName);
    }

}
