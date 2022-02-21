package com.spring.boot.rocks.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.spring.boot.rocks.model.Inventory;

public class InventoryCsvUtils {

	public static void inventoryitemsToCsv(PrintWriter writer, List<Inventory> inventoryitems) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("Itemname", "Itemtype", "Itemquantity"));) {
			for (Inventory inventoryitem : inventoryitems) {
				List<String> data = Arrays.asList(inventoryitem.getItemname(), inventoryitem.getItemtype(),
						String.valueOf(inventoryitem.getItemquantity()));

				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}

	public static List<Inventory> parseCsvFile(InputStream is) {
		BufferedReader fileReader = null;
		CSVParser csvParser = null;

		List<Inventory> inventoryitems = new ArrayList<Inventory>();

		try {
			fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
//				Customer inventoryitem = new Customer(Long.parseLong(csvRecord.get("id")), csvRecord.get("name"),
//						csvRecord.get("address"), Integer.parseInt(csvRecord.get("age")));

				Inventory inventoryitem = new Inventory(csvRecord.get("Itemname"), csvRecord.get("Itemtype"),
						(csvRecord.get("Itemquantity")));

				inventoryitems.add(inventoryitem);
			}

		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvParser.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}

		return inventoryitems;
	}
}
