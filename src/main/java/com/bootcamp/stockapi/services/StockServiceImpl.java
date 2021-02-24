package com.bootcamp.stockapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.stockapi.model.Stock;
import com.bootcamp.stockapi.repository.StockApiRepository;

@Service
public class StockServiceImpl implements StockApiServiceIF {
	
	@Autowired
	private StockApiRepository stockApiRepository;
	
	public Stock findByProductName(String productName) {
		return stockApiRepository.findByProductName(productName);
	}
	
	public Stock findMaxCount() {
		return stockApiRepository.findMaxCount();
	}
	
	public Stock findMinCount() {
		return stockApiRepository.findMinCount();
	}
	
	public List<Stock> outOfStock() {
		return stockApiRepository.outOfStock();
	}
	public List<Stock> findorderByCountDesc(){
		return stockApiRepository.findorderByCountDesc();
	}
	public List<Stock> findorderByCountAsc(){
		return stockApiRepository.findorderByCountAsc();
	}
	
	@Override
	public List<Stock> findAllStock() {
		return (List<Stock>) stockApiRepository.findAll();
	}

	@Override
	public Stock findById(long id) {
		Optional<Stock> result = stockApiRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	@Override
	public Stock insert(Stock s) {
		return stockApiRepository.save(s);
	}
	
	
	@Override
	public boolean delete(long id) {
		try {
			stockApiRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Stock p) {
		try {
			stockApiRepository.save(p);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}