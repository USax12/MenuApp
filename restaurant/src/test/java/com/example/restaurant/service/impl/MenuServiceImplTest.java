package com.example.restaurant.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.common.exception.CustomException.MenuServiceException;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.repository.MenuRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

	@Mock
	private MenuRepository menuRepository;

	@InjectMocks
	private MenuServiceImpl menuService;

	@Test
	void getMenuItems_WithExistingMenuItems_ShouldReturnCategorizedMap() {
		// Arrange
		List<MenuItem> mockMenuItems = new ArrayList<>();
		mockMenuItems.add(new MenuItem("Burger", "Food", 1.0));
		mockMenuItems.add(new MenuItem("Beer", "Alcoholic", 2.0));
		mockMenuItems.add(new MenuItem("Soda", "Non-Alcoholic", 3.0));

		// Mock the menuRepository behavior
		when(menuRepository.findAll()).thenReturn(mockMenuItems);

		// Act
		var result = menuService.getMenuItems();

		// Assert
		assertNotNull(result);
		assertEquals(3, result.size());
		assertTrue(result.containsKey("Food"));
		assertTrue(result.containsKey("Alcoholic"));
		assertTrue(result.containsKey("Non-Alcoholic"));
		assertEquals(1, result.get("Food").size());
		assertEquals(1, result.get("Alcoholic").size());
		assertEquals(1, result.get("Non-Alcoholic").size());
	}

	@Test
	void getMenuItems_WithNoMenuItems_ShouldThrowMenuServiceException() {
		// Mock the menuRepository behavior
		when(menuRepository.findAll()).thenReturn(new ArrayList<>());

		// Act and Assert
		assertThrows(MenuServiceException.class, () -> menuService.getMenuItems());
	}

	@Test
	void getMenuItemByName_WithExistingMenuItem_ShouldReturnMenuItem() {
		// Arrange
		String itemName = "Burger";
		MenuItem mockMenuItem = new MenuItem(itemName, "Food", 2.2);

		// Mock the menuRepository behavior
		when(menuRepository.findByName(itemName)).thenReturn(Optional.of(mockMenuItem));

		// Act
		MenuItem result = menuService.getMenuItemByName(itemName);

		// Assert
		assertNotNull(result);
		assertEquals(itemName, result.getName());
	}

	@Test
	void getMenuItemByName_WithNonExistingMenuItem_ShouldReturnNull() {
		// Arrange
		String itemName = "NonExistingItem";

		// Mock the menuRepository behavior
		when(menuRepository.findByName(itemName)).thenReturn(Optional.empty());

		// Act
		MenuItem result = menuService.getMenuItemByName(itemName);

		// Assert
		assertNull(result);
	}
}
