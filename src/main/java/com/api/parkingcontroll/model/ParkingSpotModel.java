package com.api.parkingcontroll.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="vaga")
public class ParkingSpotModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, unique = true, length = 10)
	private String numero;
	
	//TODO criar classe CarModel
	@Column(nullable = false, unique = true, length = 7)
	private String placaCarro;
	
	@Column(nullable = false, length = 70)
	private String marcaCarro;
	
	@Column(nullable = false, length = 70)
	private String modeloCarro;
	
	@Column(nullable = false, length = 70)
	private String corCarro;
	//até aqui
	
	@Column(nullable = false)
	private LocalDateTime dataRegistro;
	
	@Column(nullable = false, length = 70)
	private String nomeDono;
	
	@Column(nullable = false, length = 70)
	private String numeroApartamento;
	
	@Column(nullable = false, length = 70)
	private String numeroBloco;
	
	
}
