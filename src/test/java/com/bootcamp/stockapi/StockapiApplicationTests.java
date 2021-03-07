package com.bootcamp.stockapi;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bootcamp.stockapi.model.Stock;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StockapiApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getAllStock() throws Exception{
		mockMvc.perform(get("/api/stock")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getStock() throws Exception{
		mockMvc.perform(get("/api/stock/emptyStock")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getStockByProductName() throws Exception{
		mockMvc.perform(get("/api/stock/product/masa")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getStockById() throws Exception{
		mockMvc.perform(get("/api/stock/9")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getMaxCount() throws Exception {
		mockMvc.perform(get("/api/stock/maxCount")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getMinCount() throws Exception {
		mockMvc.perform(get("/api/stock/minCount")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getOrderByCountDesc() throws Exception{
		mockMvc.perform(get("/api/stock/orderByCountDesc")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void delete() throws Exception{
		String id = "8";
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/stock/{id}", id)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void update() throws Exception{
		Stock stock = new Stock(9L, "masa", "ev", 0, 110);
		mockMvc.perform(put("/api/stock")
						.accept(MediaType.APPLICATION_JSON)
						.contentType("application/json")
						.content(stock.toString()))
						.andDo(print())
						.andExpect(status().isOk());
	}
	
	@Test
	public void checkAndUpdate() throws Exception{
		String productName = "masa";
		String quantity = "100";
		mockMvc.perform(put("/api/stock/checkAndUpdate/{productionName}/{quantity}",productName,quantity)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
