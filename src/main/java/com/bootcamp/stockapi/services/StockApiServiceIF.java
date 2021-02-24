package com.bootcamp.stockapi.services;

import java.util.List;

import com.bootcamp.stockapi.model.Stock;

public interface StockApiServiceIF {
	List<Stock> findAllStock();
	Stock findById(long id);
	Stock insert(Stock s);
	boolean delete(long id);
	boolean update(Stock p);

}
