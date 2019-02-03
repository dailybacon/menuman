package com.beefyboys.menuman.controllers;

import com.beefyboys.menuman.models.Menu;
import com.beefyboys.menuman.models.Section;
import com.beefyboys.menuman.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/menu")
    public boolean addMenu(@RequestBody @Valid Menu menu){
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

    @PostMapping("/section")
    public List<Section> addSection(@RequestBody @Valid Section section){
        return Arrays.asList(section);
    }

}
