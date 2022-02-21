package com.spring.boot.rocks.fileservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.rocks.model.Inventory;
import com.spring.boot.rocks.repository.InventoryRepository;
import com.spring.boot.rocks.utils.InventoryCsvUtils;

@Service
public class InventoryFileServices {

	@Autowired
	InventoryRepository customerRepository;

	// Store File Data to Database
	public void store(MultipartFile file) {
		try {
			List<Inventory> listInventoryItems = InventoryCsvUtils.parseCsvFile(file.getInputStream());
			// Save InventoryItems to DataBase
			customerRepository.saveAll(listInventoryItems);
		} catch (IOException e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}

	// Load Data to Excel/CSV File
	public void loadFile(PrintWriter writer) {
		List<Inventory> inventoryItems = (List<Inventory>) customerRepository.findAll();

		try {
			InventoryCsvUtils.inventoryitemsToCsv(writer, inventoryItems);
		} catch (IOException e) {
		}
	}
}
