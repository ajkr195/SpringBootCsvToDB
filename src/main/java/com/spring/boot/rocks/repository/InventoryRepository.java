package com.spring.boot.rocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.rocks.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
