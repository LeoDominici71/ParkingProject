package com.parquimetro.Projeto.service;

import org.springframework.stereotype.Service;

import com.parquimetro.Projeto.entities.Car;
import com.parquimetro.Projeto.entities.dto.CarDTO;

@Service
public interface CarService {
	
	Car save(CarDTO carDTO);
	
	Car findByPlate(String plate);
	
	Car update(String plate, CarDTO dto);

}
