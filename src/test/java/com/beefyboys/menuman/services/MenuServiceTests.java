package com.beefyboys.menuman.services;

import com.beefyboys.menuman.exceptions.ApiException;
import com.beefyboys.menuman.models.MenuItem;
import com.beefyboys.menuman.repository.MenuDataStore;
import com.beefyboys.menuman.services.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceTests {

	@Mock
	private MenuDataStore mockDatastore;

	@InjectMocks
	private MenuService menuService;

	@Test
	public void shouldUpdateMenuItemWhenMenuItemExists() {
		given(mockDatastore.getMenuItem(any())).willReturn(new MenuItem());
		given(mockDatastore.updateMenuItem(any(), any())).willReturn(new MenuItem());
		assertThat(menuService.updateMenuItem(1, new MenuItem()), is(notNullValue()));
	}

	@Test(expected = ApiException.MenuItemNotFound.class)
	public void shouldThrowExceptionWhenUpdatingMenuItemThatDoesNotExist() {
		given(mockDatastore.getMenuItem(any())).willReturn(null);
		menuService.updateMenuItem(1, new MenuItem());
	}

	@Test
	public void failingTest() {
		assertThat(true, is(false));
	}

}

