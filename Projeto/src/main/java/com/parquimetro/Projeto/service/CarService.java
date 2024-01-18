package com.parquimetro.Projeto.service;

import org.springframework.stereotype.Service;

import com.parquimetro.Projeto.entities.Car;
import com.parquimetro.Projeto.entities.dto.CarDTO;

@Service
public interface CarService {
	
	Car save(CarDTO carDTO);
	
	Car findById(Long id);
	
	Car update(Long id, CarDTO dto);

}
