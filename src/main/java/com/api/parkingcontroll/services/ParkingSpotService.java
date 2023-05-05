package com.api.parkingcontroll.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.parkingcontroll.model.ParkingSpotModel;
import com.api.parkingcontroll.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {

	@Autowired
	private ParkingSpotRepository parkingSpotRepository;

	@Transactional
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
		return parkingSpotRepository.save(parkingSpotModel);
	}

	public boolean existsByPlacaCarro(String placaCarro) {
		return parkingSpotRepository.existsByPlacaCarro(placaCarro);
	}

	public boolean existsByNumero(String numero) {
		return parkingSpotRepository.existsByNumero(numero);
	}

	public boolean existsByNumeroApartamentoAndNumeroBloco(String numeroApartamento, String numeroBloco) {
		return parkingSpotRepository.existsByNumeroApartamentoAndNumeroBloco(numeroApartamento, numeroBloco);
	}

	public List<ParkingSpotModel> findAll() {
		return parkingSpotRepository.findAll();
	}

	public Optional<ParkingSpotModel> findById(UUID id) {
		return parkingSpotRepository.findById(id);
	}

	public void delete(ParkingSpotModel parkingSpotModel) {
		parkingSpotRepository.delete(parkingSpotModel);
		
	}

	public Page<ParkingSpotModel> findAll(Pageable pageable) {
		return parkingSpotRepository.findAll(pageable);
	}
}
