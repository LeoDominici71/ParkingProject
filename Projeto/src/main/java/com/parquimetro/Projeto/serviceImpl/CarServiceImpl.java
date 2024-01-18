package com.parquimetro.Projeto.serviceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.parquimetro.Projeto.entities.Car;
import com.parquimetro.Projeto.entities.dto.CarDTO;
import com.parquimetro.Projeto.repository.CarRepository;
import com.parquimetro.Projeto.service.CarService;
import com.parquimetro.Projeto.service.exceptions.CheckOutException;
import com.parquimetro.Projeto.service.exceptions.EntityNotFoundException;
import com.parquimetro.Projeto.service.exceptions.NulPointerExceptions;
import com.parquimetro.Projeto.service.exceptions.PlateAlreadyExistException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository repository;

	@Override
	public Car save(CarDTO carDTO) {
		// TODO Auto-generated method stub

		if (carDTO.getPlate() == null || carDTO.getCarName() == null || carDTO.getAutoMaker() == null) {
			throw new NulPointerExceptions("The plate, name of the car and automaker cannot be null");
		}


			carDTO.setEntrada(LocalDateTime.now());
			double price = 0.0;
			carDTO.setValueToPay(price);
			Car car = new Car();


			car.setPlate(carDTO.getPlate());
			car.setEntrada(carDTO.getEntrada());
			car.setCarName(carDTO.getCarName());
			car.setAutoMaker(carDTO.getAutoMaker());
			car.setValueToPay(price);
			repository.save(car);

			return car;
		}

	

	@Override
	@Cacheable(value = "car")
	public Car findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Car> carOptional = repository.findById(id);
		Car car = carOptional.orElseThrow(() -> new EntityNotFoundException("Entity not founds"));

		return car;
	}

	@Override
	public Car update(Long id, CarDTO dto) {
		// TODO Auto-generated method stub
		Optional<Car> carOptional = repository.findById(id);
		Car car = carOptional.orElseThrow(() -> new EntityNotFoundException("Entity not founds"));
		if (car.getSaida() == null) {
			car.setSaida(LocalDateTime.now());
			if (dto.getAutoMaker() != null) {
				car.setAutoMaker(dto.getAutoMaker());

			}
			if (dto.getCarName() != null) {
				car.setCarName(dto.getCarName());
			}

		} else {
			throw new CheckOutException("The customer already made check out");

		}

		String valueBetween = valueBetween(car.getEntrada(), car.getSaida(), car);
		car.setTime(valueBetween);
		Car carSaved = repository.save(car);

		return carSaved;
	}

	public String valueBetween(LocalDateTime entrada, LocalDateTime saida, Car car) {

		Duration duracao = Duration.between(entrada, saida);

		Long days = duracao.toDays();
		Long hours = duracao.toHours();
		Long minuts = duracao.toMinutes();
		Long seconds = duracao.toSeconds();

		StringBuilder stringBuilder = new StringBuilder();

		Double price = value(hours, car);

		car.setValueToPay(price);

		stringBuilder.append("Day: ").append(days).append(" hours: ").append(hours).append(" minuts: ").append(minuts)
				.append(" seconds: ").append(seconds);

		String time = stringBuilder.toString();

		return time;

	}


	public Double value(Long hours, Car car) {

		Double price = Car.PARKINGTAX;

		Double value = hours * price;

		if (hours < 1) {
			value = price;
		}

		return value;
	}

}
