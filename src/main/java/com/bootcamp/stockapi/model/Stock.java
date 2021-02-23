package com.bootcamp.stockapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "id")
	public Long id;
	
	@Column(name = "productName")
	public String productName;
	
	@Column(name = "productType")
	public String productType;
		
	@Column(name = "count")
	public int count;
	
	@Column(name = "price")
	public double price;
	
	public Stock() { }
	
	public Stock(Long id, String productName, String productType, int count, double price) {
		this.id = id;
		this.productName = productName;
		this.productType = productType;
		this.count = count;
		this.price = price;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(String.valueOf(id));
		builder.append(",");
		builder.append(productName);
		builder.append(",");
		builder.append(productType);
		builder.append(",");
		builder.append(String.valueOf(count));
		builder.append(",");
		builder.append(String.valueOf(price));
		
		//return builder.toString();
		return "{\"id\":"+id+",\"productName\":\""+productName+"\",\"productType\""
				+ ":\""+productType+"\",\"count\":"+count+",\"price\":"+price+"}";
	}
}