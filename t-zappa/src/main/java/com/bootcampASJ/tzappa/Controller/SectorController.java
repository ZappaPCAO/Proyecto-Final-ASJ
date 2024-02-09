package com.bootcampASJ.tzappa.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.bootcampASJ.tzappa.Models.Sector;
import com.bootcampASJ.tzappa.Services.SectorService;
import com.bootcampASJ.tzappa.utils.ErrorHandler;

import jakarta.validation.Valid;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/sectors")
public class SectorController {
	
	@Autowired
	private SectorService sectorService;
	
	@GetMapping
	public ResponseEntity<Object> getSectors(){
		return ResponseEntity.ok( this.sectorService.getSectors() );
	}
	
	@PostMapping
	public ResponseEntity<Object> newSector(@Valid @RequestBody Sector sector, 
													BindingResult bindingResult){
		System.out.println("prueba a ver si llega con datos q no estan en la bd. ");
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	
		Optional<Sector> result = this.sectorService.newSector(sector);

	    if (result.isPresent()){
	        return new ResponseEntity<>(result.get(), HttpStatus.OK);
	    }else{
	        Map<String, String> error = Collections.singletonMap("Error", "Error en la integridad de datos. (Ej. Campo Unique)");
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	}
	
	@PutMapping
	public ResponseEntity<Object> updateSector(@Valid @RequestBody Sector sector,
														BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Sector> result = this.sectorService.updateSector(sector);
		
		if (result.isPresent()){
	        return new ResponseEntity<>(result.get(), HttpStatus.OK);
	    }else{
	        Map<String, String> error = Collections.singletonMap("Error", "Error en la integridad de datos. (Ej. Campo Unique)");
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSector(@PathVariable Integer id){
		return ( this.sectorService.deleleteSector(id) ?  ResponseEntity.ok().build() : 
														  ResponseEntity.badRequest().build());
	}	
}