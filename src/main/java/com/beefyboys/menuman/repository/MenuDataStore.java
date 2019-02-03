package com.beefyboys.menuman.repository;

import com.beefyboys.menuman.models.Menu;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.jooq.code.tables.Menu.MENU;

@Repository
public class MenuDataStore {

    @Autowired
    private DSLContext dataStore;

    public boolean addMenu(Menu menu) {
        return dataStore
                .insertInto(MENU)
                .set(MENU.NAME, menu.getName())
                .set(MENU.DESCRIPTION, menu.getDescription())
                .execute() > 0;
    }

    public List<Menu> getAllMenus() {
        return dataStore
                .selectFrom(MENU)
                .fetchInto(Menu.class);
    }

    public Menu getMenu(Integer menuId) {
        return dataStore
                .selectFrom(MENU)
                .where(MENU.ID.eq(menuId))
                .fetchOneInto(Menu.class);
    }

    public boolean deleteMenu(Integer menuId) {
        return dataStore
                .deleteFrom(MENU)
                .where(MENU.ID.eq(menuId))
                .execute() > 0;
    }

}
