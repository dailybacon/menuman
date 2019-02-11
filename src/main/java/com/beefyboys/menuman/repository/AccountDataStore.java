package com.beefyboys.menuman.repository;

import com.beefyboys.menuman.models.Account;
import org.jooq.DSLContext;
import org.jooq.code.tables.records.AccountRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

import static org.jooq.code.Tables.ACCOUNT;

@Repository
public class AccountDataStore {

    @Autowired
    private DSLContext dataStore;

    public static Logger LOGGER = Logger.getLogger(AccountDataStore.class.toString());

    public boolean addAccount(Account account){
        return dataStore
                .insertInto(ACCOUNT)
                .set(ACCOUNT.USERNAME, account.getUsername())
                .set(ACCOUNT.ADDRESS, account.getAddress())
                .execute() > 0;
    }

}
