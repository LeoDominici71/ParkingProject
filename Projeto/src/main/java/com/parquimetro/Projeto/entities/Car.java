package com.parquimetro.Projeto.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final double PARKINGTAX = 6.0;

	@Id
	@Column(unique = true)
	private String plate;
	private String carName;
	private String autoMaker;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private String time;
	private Double valueToPay;
	
	@Override
	public int hashCode() {
		return Objects.hash(plate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(plate, other.plate);
	}
	
	
	
	
	

}
