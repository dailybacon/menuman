package com.beefyboys.menuman.repository;

import com.beefyboys.menuman.models.Menu;
import com.beefyboys.menuman.models.MenuItem;
import com.beefyboys.menuman.models.Section;
import org.jooq.DSLContext;
import org.jooq.code.tables.records.MenuItemRecord;
import org.jooq.code.tables.records.MenuRecord;
import org.jooq.code.tables.records.SectionRecord;

import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.jooq.code.Tables.*;
@Repository
public class MenuDataStore {

    @Autowired
    private DSLContext dataStore;

    private static Logger LOGGER = Logger.getLogger(MenuDataStore.class.toString());

    public Menu addMenu(Menu menu) {
        MenuRecord record = dataStore
                .insertInto(MENU)
                .set(MENU.NAME, menu.getName())
                .set(MENU.DESCRIPTION, menu.getDescription())
                .returning(MENU.ID).fetchOne();
        menu.setId(record.get(MENU.ID));
        return menu;
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

    public Menu updateMenu(Integer menuId, Menu menu){
        MenuRecord record = dataStore
                .update(MENU)
                .set(MENU.NAME, menu.getName())
                .set(MENU.DESCRIPTION, menu.getDescription())
                .where(MENU.ID.eq(menuId))
                .returning().fetchOne();
        menu.setId(menuId);
        return menu;
    }


    public Menu getFullMenu(int menuId) {
        Menu menu = getMenu(menuId);
        List<Section> sections = getMenuSections(menuId)
                .stream()
                .map(this::addMenuItemsToSection)
                .collect(Collectors.toList());
        menu.setSections(sections);
        return menu;


    }

    private List<Section> getMenuSections(int menuId) {
        return dataStore.select()
                .from(MENU_SECTIONS)
                .join(SECTION)
                .on(MENU_SECTIONS.SECTION_ID.eq(SECTION.ID))
                .where(MENU_SECTIONS.MENU_ID.eq(menuId))
                .fetchInto(Section.class);
    }


    private Section addMenuItemsToSection(Section section) {
        List<MenuItem> items = dataStore.select()
                .from(SECTION_ITEMS)
                .join(MENU_ITEM)
                .on(SECTION_ITEMS.ITEM_ID.eq(MENU_ITEM.ID))
                .where(SECTION_ITEMS.SECTION_ID.eq(section.getId()))
                .fetchInto(MenuItem.class);
        section.setItems(items);
        return section;
    }

    public boolean deleteMenu(Integer menuId) {
        return dataStore
                .deleteFrom(MENU)
                .where(MENU.ID.eq(menuId))
                .execute() > 0;
    }

    public Section addSection(Section section) {
        SectionRecord record = dataStore
                .insertInto(SECTION)
                .set(SECTION.NAME, section.getName())
                .set(SECTION.DESCRIPTION, section.getDescription())
                .returning(SECTION.ID).fetchOne();
        section.setId(record.get(SECTION.ID));
        return section;
    }



    public List<Section> getAllSections() {
        return dataStore
                .selectFrom(SECTION)
                .fetchInto(Section.class);
    }

    public Section getSection(Integer sectionId) {
        try {
            return dataStore
                    .selectFrom(SECTION)
                    .where(SECTION.ID.eq(sectionId))
                    .fetchOneInto(Section.class);

        } catch (DataAccessException e) {
            LOGGER.info(e.toString());
            throw e;
        }
    }

    public Section updateSection(Integer sectionId, Section section){
        SectionRecord record = dataStore
                .update(SECTION)
                .set(SECTION.NAME, section.getName())
                .set(SECTION.DESCRIPTION, section.getDescription())
                .where(SECTION.ID.eq(sectionId))
                .returning(SECTION.ID).fetchOne();
        section.setId(record.getId());
        return section;
    }

    public boolean deleteSection(Integer sectionId) {
        return dataStore
                .deleteFrom(SECTION)
                .where(SECTION.ID.eq(sectionId))
                .execute() > 0;
    }

    public MenuItem addMenuItem(MenuItem menuItem){
        MenuItemRecord record = dataStore
                .insertInto(MENU_ITEM)
                .set(MENU_ITEM.NAME, menuItem.getName())
                .set(MENU_ITEM.DESCRIPTION, menuItem.getDescription())
                .set(MENU_ITEM.COST, menuItem.getCost())
                .returning(MENU_ITEM.ID).fetchOne();
        menuItem.setId(record.get(MENU_ITEM.ID));
        return menuItem;
    }

    public List<MenuItem> getAllMenuItems() {
        return dataStore
                .selectFrom(MENU_ITEM)
                .fetchInto(MenuItem.class);
    }

    public MenuItem getMenuItem(Integer menuItemId){
        try {
            return dataStore
                    .selectFrom(MENU_ITEM)
                    .where(MENU_ITEM.ID.eq(menuItemId))
                    .fetchOneInto(MenuItem.class);

        }
        catch (DataAccessException e){
            LOGGER.info(e.toString());
            throw e;
        }
    }

    public MenuItem updateMenuItem(Integer menuItemId, MenuItem menuItem){
        MenuItemRecord record = dataStore
                .update(MENU_ITEM)
                .set(MENU_ITEM.NAME, menuItem.getName())
                .set(MENU_ITEM.DESCRIPTION, menuItem.getDescription())
                .set(MENU_ITEM.COST, menuItem.getCost())
                .where(MENU_ITEM.ID.eq(menuItemId))
                .returning(MENU_ITEM.ID).fetchOne();
        menuItem.setId(menuItemId);
        return menuItem;
    }

    public boolean deleteMenuItem(Integer menuItemId){
        return dataStore
                .deleteFrom(MENU_ITEM)
                .where(MENU_ITEM.ID.eq(menuItemId))
                .execute() > 0;
    }

    public boolean addMenuSection(int menuId, Section section) {
        Section addedSection = addSection(section);

        return dataStore.insertInto(MENU_SECTIONS)
                .set(MENU_SECTIONS.MENU_ID, menuId)
                .set(MENU_SECTIONS.SECTION_ID, addedSection.getId())
                .execute() > 0;
    }

    public boolean addMenuSectionMenuItem(int sectionId, MenuItem menuItem) {
        MenuItem addedMenuItem = addMenuItem(menuItem);

        return dataStore.insertInto(SECTION_ITEMS)
                .set(SECTION_ITEMS.SECTION_ID, sectionId)
                .set(SECTION_ITEMS.ITEM_ID, addedMenuItem.getId())
                .execute() > 0;
    }

}
