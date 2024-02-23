package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.MenuItem;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {

}
