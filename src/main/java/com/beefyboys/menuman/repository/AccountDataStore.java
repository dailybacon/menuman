package com.beefyboys.menuman.repository;

import com.beefyboys.menuman.models.Account;
import org.jooq.DSLContext;
import org.jooq.code.enums.AccountRole;
import org.jooq.code.tables.records.AccountRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.jooq.code.Tables.ACCOUNT;

@Repository
public class AccountDataStore {

    @Autowired
    private DSLContext dataStore;

    public Account addAccount(Account account){
        AccountRecord accountRecord = dataStore
                .insertInto(ACCOUNT)
                .set(ACCOUNT.USERNAME, account.getUsername())
                .set(ACCOUNT.ADDRESS, account.getAddress())
                .set(ACCOUNT.PASSWORD_HASH, account.getPasswordHash())
                .set(ACCOUNT.ROLE, AccountRole.valueOf(account.getRole().name()))
                .returning(ACCOUNT.ID).fetchOne();
        account.setId(accountRecord.getId());
        return account;
    }

    public Account getAccount(String accountName){
        return dataStore.selectFrom(ACCOUNT)
                .where(ACCOUNT.USERNAME.eq(accountName))
                .fetchOneInto(Account.class);
    }

    public boolean checkAccount(String accountName){
        boolean userNameExists = dataStore.fetchExists(dataStore.selectOne()
            .from(ACCOUNT)
            .where(ACCOUNT.USERNAME.eq(accountName)));
        return userNameExists;
    }

}
