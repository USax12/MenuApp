package com.example.restaurant.service;

import java.util.List;
import java.util.Map;

import com.example.restaurant.model.MenuItem;

public interface MenuService {

	Map<String, List<MenuItem>> getMenuItems();

	MenuItem getMenuItemByName(String itemName);

}
