package com.bootcamp.stockapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.bootcamp.stockapi.model.Stock;
import com.bootcamp.stockapi.services.StockServiceImpl;

@Controller
public class StockApiWebController {
	@Autowired
	private StockServiceImpl stockServiceImpl;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Stock> listStock = stockServiceImpl.findAllStock();
		model.addAttribute("liststock", listStock);
		return "index";
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteStock(@PathVariable("id") long id) {
		stockServiceImpl.delete(id);
		return "redirect:/";
	}
	
	@GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("stock", new Stock());
        return "new";
    }
	
	@GetMapping("/edit")
    public String edit(Model model) {
		List<Stock> listStock = stockServiceImpl.findAllStock();
        model.addAttribute("stock", new Stock());
		model.addAttribute("liststock", listStock);
        return "edit";
    }
	
	@GetMapping("/details")
	public String details(Model model) {
		List<Stock> outOfStock = stockServiceImpl.outOfStock();
		Stock max = stockServiceImpl.findMaxCount();
		Stock min = stockServiceImpl.findMinCount();
		model.addAttribute("outOfStock", outOfStock);
		model.addAttribute("max", max);
		model.addAttribute("min", min);
		return "details";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStock(@ModelAttribute("stock") Stock std) {
        stockServiceImpl.insert(std);
        return "redirect:/";
    }
	
	@RequestMapping(value = "/update", method = { RequestMethod.PUT, RequestMethod.GET})
    public String updateStock(@ModelAttribute("stock") Stock std) {
        stockServiceImpl.update(std);
        return "redirect:/";
    }
	
	@RequestMapping(value = "/findByName/{productName}", method = {RequestMethod.GET})
	public String search(@PathVariable("productName") String productName, Model model) {
		Stock stock = stockServiceImpl.findByProductName(productName);
		model.addAttribute("productName", stock);
		return "edit";
	}
}
