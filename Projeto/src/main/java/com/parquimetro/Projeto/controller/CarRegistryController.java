package com.parquimetro.Projeto.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.parquimetro.Projeto.entities.Car;
import com.parquimetro.Projeto.entities.dto.CarDTO;
import com.parquimetro.Projeto.service.CarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/car")
public class CarRegistryController {
	
	@Autowired
	private CarService carService;

	@Operation(description = "find car by plate")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Retrieve Car"),
		@ApiResponse(responseCode = "404", description = "Plate does not exist")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<CarDTO> findById(@PathVariable Long id){
	Car car = carService.findById(id);
	CarDTO carDto = new CarDTO(car);
	return ResponseEntity.ok().body(carDto);	
	}
	
	@Operation(description = "save a car")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Retrieve Car"),
		@ApiResponse(responseCode = "400", description = "Plate already exist"),
		@ApiResponse(responseCode = "404", description = "There are null fields")

	})
	@PostMapping
	public ResponseEntity<CarDTO> insert(@RequestBody CarDTO carDTO){
	Car carSaved = carService.save(carDTO);
	CarDTO carDto = new CarDTO(carSaved);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(carDTO.getPlate()).toUri();
	return ResponseEntity.created(uri).body(carDto);
	
	}
	
	@Operation(description = "save a car")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Update park and car details"),
		@ApiResponse(responseCode = "404", description = "Entity not found"),
		@ApiResponse(responseCode = "400", description = "You cannot update after made checkOut"),


	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<CarDTO> update(@PathVariable Long id, @RequestBody CarDTO carDTO){
		Car newCar = carService.update(id, carDTO);
		CarDTO carDto = new CarDTO(newCar);
		return ResponseEntity.ok().body(carDto);
		
	}

}
