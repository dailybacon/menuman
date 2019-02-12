package com.beefyboys.menuman.repository;

import com.beefyboys.menuman.models.Account;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.jooq.code.Tables.ACCOUNT;

@Repository
public class AccountDataStore {

    @Autowired
    private DSLContext dataStore;

    public boolean addAccount(Account account){
        return dataStore
                .insertInto(ACCOUNT)
                .set(ACCOUNT.USERNAME, account.getUsername())
                .set(ACCOUNT.ADDRESS, account.getAddress())
                .execute() > 0;
    }

    public Account getAccount(String accountName){
        return dataStore
                .selectFrom(ACCOUNT)
                .where(ACCOUNT.USERNAME.eq(accountName))
                .fetchOneInto(Account.class);
    }
        public boolean checkAccount(String accountName){
        boolean userNameExists = dataStore.fetchExists(dataStore.selectOne()
                    .from(ACCOUNT)
                    .where(ACCOUNT.USERNAME.eq(accountName)));
            if (userNameExists) {
                return true;
            }
            return false;
        }

    }
