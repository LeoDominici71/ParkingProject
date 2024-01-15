package com.parquimetro.Projeto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.parquimetro.Projeto.entities.Car;
import com.parquimetro.Projeto.entities.dto.CarDTO;
import com.parquimetro.Projeto.repository.CarRepository;
import com.parquimetro.Projeto.serviceImpl.CarServiceImpl;

@ExtendWith(SpringExtension.class)
public class CarServiceImplTest {
	
	@Spy
	@InjectMocks
	private CarService service = Mockito.spy(CarServiceImpl.class);
	
	@Mock
	private CarRepository repository;
	
	@Test
	public void testServiceFindByPlate(){
		
		String plate = "12334";
		
		
		Car car = new Car();
		car.setEntrada(LocalDateTime.now());
		car.setAutoMaker("teste");
		car.setCarName("teste");
		car.setPlate("12334");
		car.setSaida(LocalDateTime.now());
		car.setValueToPay(12.0);		
		
		// when
	    when(repository.findByPlate(plate)).thenReturn(Optional.of(car));
	    Car result = service.findByPlate(plate);

		// then
		assertNotNull(result);
		assertEquals(car.getPlate(), result.getPlate());
		verify(repository, Mockito.times(1)).findByPlate(plate);
		
	}
	
	@Test
	public void testServiceInsert() {
		
		Car car = new Car();
		car.setEntrada(LocalDateTime.now());
		car.setAutoMaker("teste");
		car.setCarName("teste");
		car.setPlate("12334");
		car.setSaida(LocalDateTime.now());
		car.setValueToPay(12.0);	
		
		CarDTO carDto = new CarDTO();
		carDto.setAutoMaker("teste");
		carDto.setEntrada(LocalDateTime.now());
		carDto.setCarName("teste");
		carDto.setPlate("12334");
		carDto.setSaida(LocalDateTime.now());
		carDto.setValueToPay(12.0);
		
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(car);
	    Car result = service.save(carDto);
	    
	    assertNotNull(result);
		assertEquals(carDto.getPlate(), result.getPlate());
		verify(repository, Mockito.times(1)).save(car);
		
	}
	
	@Test
	public void updateTest() {
		
		String plate = "12334";
		
		CarDTO carDto = new CarDTO();
		carDto.setAutoMaker("teste");
		carDto.setCarName("teste");
		carDto.setPlate("12334");
		
		Car car = new Car();
		car.setEntrada(LocalDateTime.now());
		car.setAutoMaker("teste");
		car.setCarName("teste");
		car.setPlate("12334");
		
	    when(repository.findByPlate(plate)).thenReturn(Optional.of(car));
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(car);
		
	    Car result = service.update(plate, carDto);
	    
	    assertNotNull(result);
		assertEquals(carDto.getPlate(), result.getPlate());
		verify(repository, Mockito.times(1)).findByPlate(plate);
		verify(repository, Mockito.times(1)).save(car);


	} 

}
