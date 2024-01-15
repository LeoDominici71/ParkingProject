package com.parquimetro.Projeto.entities.dto;

import java.time.LocalDateTime;

import com.parquimetro.Projeto.entities.Car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
	
	private String plate;
	private String carName;
	private String autoMaker;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private String time;
	private Double valueToPay;
	
	
	
	public CarDTO(Car car) {
		this.entrada = car.getEntrada();
		this.plate = car.getPlate();
		this.autoMaker = car.getAutoMaker();
		this.carName = car.getCarName();
		this.saida = car.getSaida();
		this.valueToPay = car.getValueToPay();
		this.time = car.getTime();
	}
	
	
	


}
