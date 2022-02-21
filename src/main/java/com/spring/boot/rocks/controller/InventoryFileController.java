package com.spring.boot.rocks.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.rocks.fileservice.InventoryFileServices;
import com.spring.boot.rocks.repository.InventoryRepository;

@Controller
public class InventoryFileController {

	@Autowired
	InventoryFileServices fileServices;

	@Autowired
	InventoryRepository inventoryRepository;

	@GetMapping({ "/", "/inventoryupload" })
	public String index() {
		return "inventoryuploadform";
	}

	@PostMapping("/inventoryupload")
	public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, Model model) {
		try {
			fileServices.store(file);
			model.addAttribute("message", "File uploaded successfully!");
		} catch (Exception e) {
			model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
		}
		return "inventoryuploadform";
	}

	@GetMapping("/inventorydownload")
	public void downloadFile(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=inventoryitems.csv");

		fileServices.loadFile(response.getWriter());
	}

	@GetMapping("/inventorylist")
	public String inventoryitemList(Model model) throws IOException {

		model.addAttribute("inventoryitems", inventoryRepository.findAll());
		return "inventorylist";
	}
}