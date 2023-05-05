package com.api.parkingcontroll.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontroll.DTO.ParkingSpotDTO;
import com.api.parkingcontroll.model.ParkingSpotModel;
import com.api.parkingcontroll.services.ParkingSpotService;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
	
	@Autowired
	private ParkingSpotService parkingSpotService;
	
	@PostMapping
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
		//TODO custom validator
		if(parkingSpotService.existsByPlacaCarro(parkingSpotDTO.getPlacaCarro())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Placa já cadastrada!");
		}
		
		if(parkingSpotService.existsByNumero(parkingSpotDTO.getNumero())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Numero da vaga já cadastrada!");
		}
		
		if(parkingSpotService.existsByNumeroApartamentoAndNumeroBloco(parkingSpotDTO.getNumeroApartamento(), parkingSpotDTO.getNumeroBloco())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Bloco ou Numero de apartamento já cadastrada!");
		}
		
		var parkingSpotModel = new ParkingSpotModel();
		BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
		parkingSpotModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
	}
	
	@GetMapping
	public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getParkingSpotById(@PathVariable(value = "id") UUID id){
		Optional<ParkingSpotModel> parkingSpotModel = parkingSpotService.findById(id);
		
		if(!parkingSpotModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
		}
		
		return  ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteParkingSpot(@PathVariable(value = "id") UUID id){
		Optional<ParkingSpotModel> parkingSpotModel = parkingSpotService.findById(id);
		
		if(!parkingSpotModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
		}
		
		parkingSpotService.delete(parkingSpotModel.get());
		return  ResponseEntity.status(HttpStatus.OK).body("Vaga deletada com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		
		if(!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
		}
		
		var parkingSpotModel = new ParkingSpotModel();
		
		BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
		parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
		parkingSpotModel.setDataRegistro(parkingSpotModelOptional.get().getDataRegistro());
		
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
	}
}
