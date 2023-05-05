package com.api.parkingcontroll.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ParkingSpotDTO {

	@NotBlank
	private String numero;
	
	@NotBlank
	@Size(max=7)
	private String placaCarro;
	
	@NotBlank
	private String marcaCarro;
	
	@NotBlank
	private String modeloCarro;
	
	@NotBlank
	private String corCarro;
	
	@NotBlank
	private String nomeDono;
	
	@NotBlank
	private String numeroApartamento;
	
	@NotBlank
	private String numeroBloco;
}
