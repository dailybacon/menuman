package com.beefyboys.menuman.services;

import com.beefyboys.menuman.models.Account;
import com.beefyboys.menuman.repository.AccountDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountDataStore repo;

    public boolean addAccount(Account account) {
        return repo.addAccount(account);
    }

}
