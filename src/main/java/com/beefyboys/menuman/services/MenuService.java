package com.beefyboys.menuman.services;

import com.beefyboys.menuman.models.Menu;
import com.beefyboys.menuman.models.Section;
import com.beefyboys.menuman.repository.MenuDataStore;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.jooq.code.tables.Menu.MENU;


@Service
public class MenuService {

    @Autowired
    MenuDataStore repo;

    public boolean addMenu(Menu menu) {
        return repo.addMenu(menu);
    }

    public List<Menu> getAllMenus() {
        return repo.getAllMenus();
    }

    public Menu getMenu(Integer menuId) {
        return repo.getMenu(menuId);
    }

    public boolean deleteMenu(Integer menuId) {
        return repo.deleteMenu(menuId);
    }

}
