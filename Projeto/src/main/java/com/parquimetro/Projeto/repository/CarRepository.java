package com.parquimetro.Projeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parquimetro.Projeto.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
	
	Optional<Car> findByPlate(String plate);
	
	
	
}
