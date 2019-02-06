package com.beefyboys.menuman.repository;

import com.beefyboys.menuman.models.Menu;
import com.beefyboys.menuman.models.MenuItem;
import com.beefyboys.menuman.models.Section;
import org.jooq.DSLContext;
import org.jooq.code.tables.records.MenuItemsRecord;
import org.jooq.code.tables.records.MenusRecord;
import org.jooq.code.tables.records.SectionsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.code.Tables.*;


@Repository
public class MenuDataStore {

    @Autowired
    private DSLContext dataStore;

    public Menu addMenu(Menu menu) {
        MenusRecord result = dataStore
                .insertInto(MENUS)
                .set(MENUS.NAME, menu.getName())
                .set(MENUS.DESCRIPTION, menu.getDescription())
                .returning(MENUS.ID)
                .fetchOne();
        menu.setId(result.getId());
        return menu;
    }

    public List<Menu> getAllMenus() {
        return dataStore.selectFrom(MENUS).fetchInto(Menu.class);
    }

    public Menu getMenu(int menuId) {
        return dataStore.selectFrom(MENUS).where(MENUS.ID.eq(menuId)).fetchOneInto(Menu.class);
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
                .join(SECTIONS)
                .on(MENU_SECTIONS.SECTION_ID.eq(SECTIONS.ID))
                .where(MENU_SECTIONS.MENU_ID.eq(menuId))
                .fetchInto(Section.class);
    }

    private Section addMenuItemsToSection(Section section) {
        List<MenuItem> items = dataStore.select()
                .from(SECTION_ITEMS)
                .join(MENU_ITEMS)
                .on(SECTION_ITEMS.ITEM_ID.eq(MENU_ITEMS.ID))
                .where(SECTION_ITEMS.SECTION_ID.eq(section.getId()))
                .fetchInto(MenuItem.class);
        section.setItems(items);
        return section;
    }

    public boolean deleteMenu(int menuId) {
        return dataStore.deleteFrom(MENUS).where(MENUS.ID.eq(menuId)).execute() > 0;
    }

    public Section addSection(Section section) {
        SectionsRecord result = dataStore.insertInto(SECTIONS)
                .set(SECTIONS.NAME, section.getName())
                .set(SECTIONS.DESCRIPTION, section.getDescription())
                .returning(SECTIONS.ID)
                .fetchOne();
        section.setId(result.getId());
        return section;
    }

    public List<Section> getAllSections() {
        return dataStore.selectFrom(SECTIONS).fetchInto(Section.class);
    }

    public Section getSection(int sectionId) {
        return dataStore.selectFrom(SECTIONS).where(SECTIONS.ID.eq(sectionId)).fetchOneInto(Section.class);
    }

    public boolean deleteSection(int sectionId) {
        return dataStore.deleteFrom(SECTIONS).where(SECTIONS.ID.eq(sectionId)).execute() > 0;
    }

    public MenuItem addMenuItem(MenuItem menuItem) {
        MenuItemsRecord result = dataStore
                .insertInto(MENU_ITEMS)
                .set(MENU_ITEMS.NAME, menuItem.getName())
                .set(MENU_ITEMS.DESCRIPTION, menuItem.getDescription())
                .set(MENU_ITEMS.COST, menuItem.getCost())
                .returning(MENU_ITEMS.ID)
                .fetchOne();
        menuItem.setId(result.getId());
        return menuItem;
    }

    public List<MenuItem> getAllMenuItems() {
        return dataStore.selectFrom(MENU_ITEMS).fetchInto(MenuItem.class);
    }

    public MenuItem getMenuItem(int menuItemId) {
        return dataStore.selectFrom(MENU_ITEMS).where(MENU_ITEMS.ID.eq(menuItemId)).fetchOneInto(MenuItem.class);
    }

    public boolean deleteMenuItem(int menuItemId) {
        return dataStore.deleteFrom(MENU_ITEMS).where(MENU_ITEMS.ID.eq(menuItemId)).execute() > 0;
    }

    public boolean addMenuSection(int menuId, Section section) {
        Section addedSection = addSection(section);

        return dataStore.insertInto(MENU_SECTIONS)
                .set(MENU_SECTIONS.MENU_ID, menuId).set(MENU_SECTIONS.SECTION_ID, addedSection.getId()).execute() > 0;
    }

    public boolean addMenuSectionMenuItem(int sectionId, MenuItem menuItem) {
        MenuItem addedMenuItem = addMenuItem(menuItem);

        return dataStore.insertInto(SECTION_ITEMS)
                .set(SECTION_ITEMS.SECTION_ID, sectionId)
                .set(SECTION_ITEMS.ITEM_ID, addedMenuItem.getId())
                .execute() > 0;
    }

}
