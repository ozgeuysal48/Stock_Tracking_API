package com.bootcamp.stockapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bootcamp.stockapi.model.Stock;



@Repository
public interface StockApiRepository extends CrudRepository<Stock, Long> {
	
	@Query(value = "from Stock where productName = :productName")
	public Stock findByProductName(@Param("productName") String productName);
	
	@Query(value = "from Stock s where s.count = (select max(s2.count) from Stock s2)")
	public Stock findMaxCount();
	
	@Query(value = "from Stock m where m.count = (select min(s2.count) from Stock s2 where s2.count != 0)")
	public Stock findMinCount();
	
	@Query(value = "from Stock c where c.count = 0")	
	public List<Stock> outOfStock();
	
	@Query(value = "select s from Stock as s order by s.count desc")
	public List<Stock> findorderByCountDesc();
	
	@Query(value = "select s from Stock as s order by s.count asc")
	public List<Stock> findorderByCountAsc();
	
}
