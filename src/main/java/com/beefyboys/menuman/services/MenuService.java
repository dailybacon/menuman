package com.beefyboys.menuman.services;

import com.beefyboys.menuman.models.Menu;
import com.beefyboys.menuman.repository.MenuDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
