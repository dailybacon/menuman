package com.beefyboys.menuman.services;

import com.beefyboys.menuman.exceptions.ApiException;
import com.beefyboys.menuman.models.Menu;
import com.beefyboys.menuman.models.MenuItem;
import com.beefyboys.menuman.models.Section;
import com.beefyboys.menuman.repository.MenuDataStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceTests {

	@Mock
	private MenuDataStore dataStore;
	private Menu menu = new Menu();
	private Section section = new Section();
	private MenuItem menuItem = new MenuItem();
	private List<Menu> menuList = Arrays.asList(menu);
	private List<Section> sectionList = Arrays.asList(section);
	private List<MenuItem> menuItemList = Arrays.asList(menuItem);

	private int id = 1;
	
	@InjectMocks
	private MenuService menuService;

	@Test
	public void shouldUpdateMenuItemWhenMenuItemExists() {
		given(dataStore.getMenuItem(any())).willReturn(new MenuItem());
		given(dataStore.updateMenuItem(any(), any())).willReturn(new MenuItem());

		MenuItem result = menuService.updateMenuItem(1, new MenuItem());

		assertThat(result, is(notNullValue()));
	}

	@Test(expected = ApiException.MenuItemNotFound.class)
	public void shouldThrowExceptionWhenUpdatingMenuItemThatDoesNotExist() {
		given(dataStore.getMenuItem(any())).willReturn(null);

		menuService.updateMenuItem(1, new MenuItem());
	}

	@Test
	public void shouldAddMenu() {
		given(dataStore.addMenu(any())).willReturn(menu);

		Menu result = menuService.addMenu(new Menu());

		assertThat(result, is(menu));
	}

	@Test
	public void shouldGetAllMenus() {
		given(dataStore.getAllMenus()).willReturn(menuList);

		List<Menu> result = menuService.getAllMenus();

		assertThat(result, is(menuList));
	}

	@Test
	public void shouldGetMenu() {
		ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
		given(dataStore.getMenu(anyInt())).willReturn(menu);

		Menu result = menuService.getMenu(id);

		verify(dataStore).getMenu(captor.capture());
		assertThat(result, is(menu));
		assertThat(captor.getValue(), is(id));
	}

	@Test
	public void shouldGetFullMenu() {
		given(dataStore.getFullMenu(anyInt())).willReturn(menu);

		Menu result = menuService.getFullMenu(id);

		assertThat(result, is(menu));
	}

	@Test
	public void shouldUpdateMenuWhenMenuExists() {
		given(dataStore.getMenu(anyInt())).willReturn(menu);
		given(dataStore.updateMenu(anyInt(), any())).willReturn(menu);

		Menu result = menuService.updateMenu(id, new Menu());

		assertThat(result, is(menu));
	}

	@Test(expected = ApiException.MenuNotFound.class)
	public void shouldThrowExceptionWhenMenuNotFound() {
		given(dataStore.getMenu(anyInt())).willReturn(null);

		menuService.updateMenu(id, new Menu());
	}

	@Test
	public void shouldDeleteMenu() {
		given(dataStore.deleteMenu(anyInt())).willReturn(true);

		boolean result = menuService.deleteMenu(id);

		assertThat(result, is(true));
	}

	// Section tests

	@Test
	public void shouldAddSection() {
		given(dataStore.addSection(any())).willReturn(section);

		Section result = menuService.addSection(section);

		assertThat(result, is(section));
	}

	@Test
	public void shouldGetAllSections() {
		given(dataStore.getAllSections()).willReturn(sectionList);

		List<Section> result = menuService.getAllSections();

		assertThat(result, is(sectionList));
	}

	@Test
	public void shouldGetSection() {
		given(dataStore.getSection(any())).willReturn(section);

		Section result = menuService.getSection(id);

		assertThat(result, is(section));
	}

	@Test
	public void shouldUpdateSection() {
		given(dataStore.getSection(anyInt())).willReturn(section);
		given(dataStore.updateSection(anyInt(), any())).willReturn(section);

		Section result = menuService.updateSection(id, section);

		assertThat(result, is(section));
	}

	@Test (expected =  ApiException.SectionNotFound.class)
	public void shouldThrowSectionNotFoundException() {
		given(dataStore.getSection(anyInt())).willReturn(null);

		menuService.updateSection(id, section);
	}

	@Test
	public void shouldDeleteSection() {
		given(dataStore.deleteSection(anyInt())).willReturn(true);

		boolean result = menuService.deleteSection(id);

		assertThat(result, is(true));
	}

	@Test
	public void shouldAddMenuItem() {
		given(dataStore.addMenuItem(any())).willReturn(menuItem);

		MenuItem result = menuService.addMenuItem(menuItem);

		assertThat(result, is(menuItem));
	}

	@Test
	public void shouldGetAllMenuItems() {
		given(dataStore.getAllMenuItems()).willReturn(menuItemList);

		List<MenuItem> result = menuService.getAllMenuItems();

		assertThat(result, is(menuItemList));
	}

	@Test
	public void shouldDeleteMenuItem() {
		given(dataStore.deleteMenuItem(anyInt())).willReturn(true);

		boolean result = menuService.deleteMenuItem(id);

		assertThat(result, is(true));
	}

	@Test
	public void shouldAddMenuSection() {
		given(dataStore.addMenuSection(anyInt(), any())).willReturn(true);

		boolean result = menuService.addMenuSection(id, section);

		assertThat(result, is(true));
	}

	@Test
	public void shouldAddMenuSectionMenuItem(){
		given(dataStore.addMenuSectionMenuItem(anyInt(), any())).willReturn(true);

		boolean result = menuService.addMenuSectionMenuItem(id, menuItem);

		assertThat(result, is(true));
	}


}

