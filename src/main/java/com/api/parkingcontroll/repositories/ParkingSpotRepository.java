package com.api.parkingcontroll.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.parkingcontroll.model.ParkingSpotModel;

//@Repository implícito
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

	public boolean existsByPlacaCarro(String placaCarro);
	
	public boolean existsByNumero(String numero);
	
	public boolean existsByNumeroApartamentoAndNumeroBloco(String numeroApartamento, String numeroBloco);
}
