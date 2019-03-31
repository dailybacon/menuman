package com.beefyboys.menuman.services;

import com.beefyboys.menuman.exceptions.ApiException;
import com.beefyboys.menuman.models.Menu;
import com.beefyboys.menuman.models.MenuItem;
import com.beefyboys.menuman.models.Section;
import com.beefyboys.menuman.repository.MenuDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuDataStore repo;

    public Menu addMenu(Menu menu) {
        return repo.addMenu(menu);
    }

    public List<Menu> getAllMenus() {
        return repo.getAllMenus();
    }

    public Menu getMenu(int menuId) {
        return repo.getMenu(menuId);
    }

    public Menu getFullMenu(int menuId) {
        return repo.getFullMenu(menuId);
    }

    public boolean deleteMenu(int menuId) {
        return repo.deleteMenu(menuId);
    }

    public Menu updateMenu(int menuId, Menu menu) {
        Menu menuCheck = getMenu(menuId);
        if (menuCheck == null) {
            throw new ApiException.MenuNotFound();
        }
        return repo.updateMenu(menuId, menu);
    }

    public Section addSection(Section section) {
        return repo.addSection(section);
    }

    public List<Section> getAllSections(){
        return repo.getAllSections();
    }

    public Section getSection(int sectionId) {
        return repo.getSection(sectionId);
    }

    public Section updateSection(int sectionId, Section section){
        Section sectionCheck = getSection(sectionId);
        if (sectionCheck == null) {
            throw new ApiException.SectionNotFound();
        }
        return repo.updateSection(sectionId, section);
    }

    public boolean deleteSection(int sectionId) {
        return repo.deleteSection(sectionId);
    }

    public MenuItem addMenuItem(MenuItem menuItem){
        return repo.addMenuItem(menuItem);
    }

    public List<MenuItem> getAllMenuItems() {
        return repo.getAllMenuItems();
    }

    public MenuItem getMenuItem(int menuItemId){
        return repo.getMenuItem(menuItemId);
    }

    public MenuItem updateMenuItem(int menuItemId, MenuItem menuItem){
        MenuItem menuItemCheck = getMenuItem(menuItemId);
        if (menuItemCheck == null) {
           throw new ApiException.MenuItemNotFound();
        }
        return repo.updateMenuItem(menuItemId, menuItem);
    }

    public boolean deleteMenuItem(int menuItemId){
        return repo.deleteMenuItem(menuItemId);
    }

    public boolean addMenuSection(int menuId, Section section) {
        return repo.addMenuSection(menuId, section);
    }

    public boolean addMenuSectionMenuItem(int sectionId, MenuItem menuItem) {
        return repo.addMenuSectionMenuItem(sectionId, menuItem);
    }
}
