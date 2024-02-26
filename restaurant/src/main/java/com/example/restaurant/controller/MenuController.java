package com.example.restaurant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.model.MenuItem;
import com.example.restaurant.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping("/items")
	public ResponseEntity<Map<String, List<MenuItem>>> getMenu() {
		Map<String, List<MenuItem>> menuItems = menuService.getMenuItems();
		return new ResponseEntity<>(menuItems, HttpStatus.OK);
	}
	
	@GetMapping("/item/{itemName}")
    public ResponseEntity<MenuItem> getMenuItemByName(@PathVariable String itemName) {
        MenuItem menuItem = menuService.getMenuItemByName(itemName);

        if (menuItem != null) {
            return new ResponseEntity<>(menuItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
