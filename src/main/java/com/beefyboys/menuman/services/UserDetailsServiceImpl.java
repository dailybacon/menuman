package com.beefyboys.menuman.services;

import com.beefyboys.menuman.models.Account;
import com.beefyboys.menuman.repository.AccountDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountDataStore accountDataStore;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDataStore.getAccount(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(account.getUsername(), account.getPasswordHash(), singletonList(account.getRole()));
    }

}
