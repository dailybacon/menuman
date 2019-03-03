package com.beefyboys.menuman.services;

import com.beefyboys.menuman.exceptions.ApiException;
import com.beefyboys.menuman.models.Account;
import com.beefyboys.menuman.models.AccountRole;
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

    public Account addAccount(NewAccount account, boolean isAdmin) {
        String accountCheck = account.getUsername();
        if (repo.checkAccount(accountCheck) == true) {
            throw new ApiException.UsernameAlreadyExists();
        }
        return repo.addAccount(prepareNewAccount(account, isAdmin));

    }

    private Account prepareNewAccount(NewAccount newAccount, boolean isAdmin) {
        Account account = new Account();
        account.setUsername(newAccount.getUsername());
        account.setAddress(newAccount.getAddress());
        account.setPasswordHash(passwordEncoder.encode(newAccount.getPassword()));
        account.setRole(isAdmin ? AccountRole.ROLE_ADMIN : AccountRole.ROLE_USER);
        return account;
    }

    public Account getAccount(String accountName) {
        return repo.getAccount(accountName);
    }

    public void deleteAccount(String accountName) {
        if (!repo.deleteAccount(accountName)) throw new ApiException.UserNameNotFound();
    }

}
