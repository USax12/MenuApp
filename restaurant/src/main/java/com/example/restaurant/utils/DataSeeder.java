package com.example.restaurant.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.restaurant.model.MenuItem;
import com.example.restaurant.repository.MenuRepository;

@Component
public class DataSeeder implements CommandLineRunner {

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public void run(String... args) throws Exception {
		// Check if the menu is already seeded
		if (menuRepository.count() == 0) {
			seedMenuData();
		}
	}

	private void seedMenuData() {
		// Sample menu data
		/*
		 * MenuItem burger = new MenuItem("Burger", "Food", 10.99); MenuItem wine = new
		 * MenuItem("Wine", "Alcoholic", 15.99); MenuItem coke = new MenuItem("Coke",
		 * "Non-Alcoholic", 2.99);
		 */

		// Save menu items to the database
		/*
		 * menuRepository.save(burger); menuRepository.save(wine);
		 * menuRepository.save(coke);
		 */
	}
}
