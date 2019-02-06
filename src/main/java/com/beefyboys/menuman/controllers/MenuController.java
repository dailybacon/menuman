package com.beefyboys.menuman.controllers;

import com.beefyboys.menuman.models.Menu;
import com.beefyboys.menuman.models.MenuItem;
import com.beefyboys.menuman.models.Section;
import com.beefyboys.menuman.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/menu")
    public Menu addMenu(@RequestBody @Valid Menu menu){
        return menuService.addMenu(menu);
    }

    @GetMapping("/menu")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/menu/{menuId}")
    public Menu getMenu(@PathVariable("menuId") int menuId){
        return menuService.getMenu(menuId);
    }

    @DeleteMapping("/menu/{menuId}")
    public boolean deleteMenu(@PathVariable("menuId") int menuId){
        return menuService.deleteMenu(menuId);
    }

    @GetMapping("/menu/{menuId}/full")
    public Menu getFullMenu(@PathVariable("menuId") int menuId) {
        return menuService.getFullMenu(menuId);
    }

    @PostMapping("/section")
    public Section addSection(@RequestBody @Valid Section section){
        return menuService.addSection(section);
    }

    @GetMapping("/section")
    public List<Section> getAllSections(){
        return menuService.getAllSections();
    }

    @GetMapping("/section/{sectionId}")
    public Section getSection(@PathVariable("sectionId") int sectionId){
        return menuService.getSection(sectionId);
    }

    @PostMapping("/section/{sectionId}")
    public boolean deleteSection(@PathVariable("sectionId") int sectionId){
        return menuService.deleteSection(sectionId);
    }

    @PostMapping("/menu-item")
    public MenuItem addMenuItem(@RequestBody @Valid MenuItem MenuItem){
        return menuService.addMenuItem(MenuItem);
    }

    @GetMapping("/menu-item")
    public List<MenuItem> getAllMenuItems(){
        return menuService.getAllMenuItems();
    }

    @GetMapping("/menu-item/{menuItemId}")
    public MenuItem getMenuItem(@PathVariable("menuItemId") int menuItemId){
        return menuService.getMenuItem(menuItemId);
    }

    @PostMapping("/menu-item/{menuItemId}")
    public boolean deleteMenuItem(@PathVariable("menuItemId") int menuItemId){
        return menuService.deleteMenuItem(menuItemId);
    }

    @PostMapping("/menu/{menuId}/section")
    public boolean addMenuSection(@PathVariable("menuId") int menuId,
                                  @RequestBody @Valid Section section){
        return menuService.addMenuSection(menuId, section);
    }

    @PostMapping("/section/{sectionId}/menu-item")
    public boolean addMenuSectionMenuItem(@PathVariable("sectionId") int sectionId,
                                          @RequestBody @Valid MenuItem menuItem){
        return menuService.addMenuSectionMenuItem(sectionId, menuItem);
    }

}
