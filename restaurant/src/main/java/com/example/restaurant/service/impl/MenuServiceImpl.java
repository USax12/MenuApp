package com.example.restaurant.service.impl;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.exception.CustomException.MenuServiceException;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Map<String, List<MenuItem>> getMenuItems() {
		try {
			List<MenuItem> allMenuItems = menuRepository.findAll();

			// Check if there are no menu items
			if (allMenuItems.isEmpty()) {
				throw new MenuServiceException("No menu items found.");
			}

			// Define the order of categories
			List<String> categoryOrder = List.of("Food", "Alcoholic", "Non-Alcoholic");

			// Categorize menu items by their category with a specific order
			return allMenuItems.stream()
					.collect(Collectors.groupingBy(MenuItem::getCategory,
							() -> new LinkedHashMap<>(categoryOrder.size()), Collectors.toList()))
					.entrySet().stream().sorted(Comparator.comparingInt(categoryOrder::indexOf)).collect(Collectors
							.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		} catch (Exception e) {

			throw new MenuServiceException("An error occurred while fetching menu items.", e);
		}
	}
}
