package com.bootcamp.stockapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.stockapi.model.Stock;
import com.bootcamp.stockapi.services.StockServiceImpl;

@RestController()
@RequestMapping("/api/stock")
public class StockApiController {
	
	@Autowired
	private StockServiceImpl stockServiceImpl;
	
	@GetMapping("")
	public List<Stock> getAllStock(){
		return stockServiceImpl.findAllStock();
	}
	
	@GetMapping("/emptyStock")
	public List<Stock> getStock(){
		return stockServiceImpl.outOfStock();
	}
	
	@GetMapping("/product/{productName}")
	public Stock getStockByProductName(@PathVariable String productName) {
		return stockServiceImpl.findByProductName(productName);
	}
	
	@GetMapping("/maxCount")
	public Stock getMaxCount() {
		return stockServiceImpl.findMaxCount();
	}
	
	@GetMapping("/minCount")
	public Stock getMinCount() {
		return stockServiceImpl.findMinCount();
	}
	
	@GetMapping("/orderByCountDesc")
	public List<Stock> getOrderByCountDesc() {
		return stockServiceImpl.findorderByCountDesc();
	}
	
	@GetMapping("/orderByCountAsc")
	public List<Stock> getOrderByCountAsc() {
		return stockServiceImpl.findorderByCountAsc();
	}
	
	@GetMapping("{id}")
	public Stock getStockId(@PathVariable long id) {
		return stockServiceImpl.findById(id);
	}
	
	@PostMapping("/addStock")
	public String addStock(@RequestBody Stock stock) {
		if(stock != null) {
			stockServiceImpl.insert(stock);
			return "Stock added";
		}
		else {
			return "Invalid request";
		}
	}
	
	
	
	@DeleteMapping("{id}")
	public String deleteStock(@PathVariable("id") long id) {
		if(id > 0) {
			if(stockServiceImpl.delete(id)) {
				return "Stock deleted";
			}
			else {
				return "Failed to delete stock";
			}
		}
		return "Invalid id";
	}
	
	@PutMapping("")
	public String updateStock(@RequestBody Stock stock) {
		if(stock != null) {
			stockServiceImpl.update(stock);
			return "Stock is updated";
		}
		else {
			return "Failed to update Stock";
		}
	}
	
	@PutMapping("/checkAndUpdate/{productName}/{quantity}")
	public String checkAndUpdate(@PathVariable String productName, @PathVariable int quantity) {
		Stock stock = stockServiceImpl.findByProductName(productName);
		if (stock == null) {
			return productName + " not found in Stock";
		} else {
			if (stock.getCount() == 0) {
				stock.setCount(quantity);
				stockServiceImpl.update(stock);
				return "Count of " + productName + " was 0 and updated to " + quantity;
			}
			else {
				return "Count of " + productName + " is " + stock.getCount();
			}
		}
	}
}
