package com.beefyboys.menuman.controllers;

import com.beefyboys.menuman.models.Menu;
import com.beefyboys.menuman.models.MenuItem;
import com.beefyboys.menuman.models.Section;
import com.beefyboys.menuman.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/menu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean addMenu(@RequestBody @Valid Menu menu){
        return menuService.addMenu(menu);
    }

    @GetMapping("/menu")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/menu/{menuId}")
    public Menu getMenu(@PathVariable("menuId") int menuId){
        return menuService.getMenu(menuId);
    }

    @PutMapping("/menu/{menuId}")
    public Menu updateMenu(@PathVariable("menuId") int menuId,
                           @RequestBody @Valid Menu menu) {
        return menuService.updateMenu(menuId, menu);
    }

    @DeleteMapping("/menu/{menuId}")
    public boolean deleteMenu(@PathVariable("menuId") int menuId){
        return menuService.deleteMenu(menuId);
    }

    @PostMapping("/section")
    public Section addSection(@RequestBody @Valid Section section){
        return menuService.addSection(section);
    }

    @GetMapping("/section")
    public List<Section> getAllSections() {
    return menuService.getAllSections();}

    @GetMapping("/section/{sectionId}")
    public Section getSection(@PathVariable("sectionId") int sectionId){
        return menuService.getSection(sectionId);
    }

    @PutMapping("/section/{sectionId}")
    public Section updateSection(@PathVariable("sectionId") int sectionId,
                                 @RequestBody @Valid Section section){
        return menuService.updateSection(sectionId, section);
    }


    @DeleteMapping("/section/{sectionId}")
    public boolean deleteSection(@PathVariable("sectionId") int sectionId){
        return menuService.deleteSection(sectionId);
    }

    @PostMapping("/menu-item/")
    public MenuItem addMenuItem(@RequestBody @Valid MenuItem menuItem){
        return menuService.addMenuItem(menuItem);
    }

    @GetMapping("/menu-item/")
    public List<MenuItem> getAllMenuItems(){
        return menuService.getAllMenuItems();}

    @GetMapping("/menu-item/{menuItemId}")
    public MenuItem getMenuItem(@PathVariable("menuItemId") int menuItemId){
        return menuService.getMenuItem(menuItemId);
    }

    @PutMapping("/menu-item/{menuItemId}")
    public MenuItem updateMenuItem(@PathVariable("menuItemId") int menuItemId,
                                   @RequestBody @Valid MenuItem menuItem){
        return menuService.updateMenuItem(menuItemId, menuItem);
    }

    @DeleteMapping("/menu-item/{menuItemId}")
    public boolean deleteMenuItem(@PathVariable("menuItemId") int menuItemId){
        return menuService.deleteMenuItem(menuItemId);
    }

}