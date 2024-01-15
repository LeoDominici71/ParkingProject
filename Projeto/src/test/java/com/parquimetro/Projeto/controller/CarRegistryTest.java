package com.parquimetro.Projeto.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parquimetro.Projeto.entities.Car;
import com.parquimetro.Projeto.entities.dto.CarDTO;
import com.parquimetro.Projeto.service.CarService;

@SpringBootTest
@AutoConfigureMockMvc
public class CarRegistryTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CarService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void InsertShouldCreateCar() throws Exception {

		// Arrange
		CarDTO carDto = new CarDTO();
		carDto.setAutoMaker("123");
		carDto.setCarName("fusca");
		carDto.setPlate("122345");
		
		
	    Car car = new Car();
	    car.setAutoMaker("123");
	    car.setCarName("fusca");
	    car.setPlate("122345");
	    car.setEntrada(LocalDateTime.now());
	    car.setSaida(LocalDateTime.now());
	    car.setTime("12/12/34");
	    car.setValueToPay(12.0);
		
		when(service.save(any())).thenReturn(car);

		
		String jsonBody = objectMapper.writeValueAsString(carDto);


		// ACT
		var response = mockMvc.perform(post("/car")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
				
				.andReturn().getResponse();

		// ASSERT
        Assertions.assertEquals(201, response.getStatus());
		
	}
	
	@Test
	public void InsertShouldFindCar() throws Exception {

		// Arrange
		
	    Car car = new Car();
	    car.setAutoMaker("123");
	    car.setCarName("fusca");
	    car.setPlate("122345");
	    car.setEntrada(LocalDateTime.now());
	    car.setSaida(LocalDateTime.now());
	    car.setTime("12/12/34");
	    car.setValueToPay(12.0);
		
		when(service.findByPlate("122345")).thenReturn(car);
		


		// ACT
		ResultActions response = mockMvc.perform(get("/car/{plate}", "122345")
    	.accept(MediaType.APPLICATION_JSON));

		// ASSERT
		response.andExpect(status().isOk());
		response.andExpect(jsonPath("$.saida").exists());

	}
	
	
	@Test
	public void UpdateShouldUpdateCarData() throws Exception{
		
		
        // Arrange
		
		CarDTO carDto = new CarDTO();
		carDto.setAutoMaker("123");
		carDto.setCarName("fusca");
		carDto.setPlate("122345");
		
	    Car car = new Car();
	    car.setAutoMaker("123");
	    car.setCarName("fusca");
	    car.setPlate("122345");
	    car.setEntrada(LocalDateTime.now());
	    car.setSaida(LocalDateTime.now());
	    car.setTime("12/12/34");
	    car.setValueToPay(12.0);
		
		when(service.update(eq(car.getPlate()), any())).thenReturn(car);
		


		// ACT
	    String jsonBody = objectMapper.writeValueAsString(carDto);
	    
		ResultActions result = mockMvc.perform(put("/car/{plate}", "122345").content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		
		

		// ASSERT
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.saida").exists());

		
	}
	
		
	}


