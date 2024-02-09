package com.bootcampASJ.tzappa.Controller;

import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Services.ProviderService;
import com.bootcampASJ.tzappa.utils.ErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/providers") // localhost:8080/proveedores
public class ProviderController {
	
	@Autowired
	ProviderService providerService;
	
	@GetMapping // [GET] localhost:8080/proveedores
	public ResponseEntity<Object> getProviders() {		
		return ResponseEntity.ok( this.providerService.getProviders() );
	}
	
	@GetMapping("/{id}") // [GET] localhost:8080/proveedores/3
	public ResponseEntity<Object> getProviderById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.providerService.getProviderById(id));
	}
	
	@GetMapping("/actives")
	public ResponseEntity<Object> getProvidersByActive() {		
		return ResponseEntity.ok( this.providerService.getProvidersByActive() );
	}
	
	@GetMapping("/sector/{id}")
	public ResponseEntity<Object> getProviderBySector(@PathVariable Integer id){
		
		if(id == 0) {
			return ResponseEntity.ok(this.providerService.getProviders());
		}
		
		return ResponseEntity.ok(this.providerService.getProviderBySector(id));
	}
	
	@GetMapping("purchase-orders/top-1")
	public ResponseEntity<Object> getProviderWithMostOrders(){
	
		return ResponseEntity.ok(this.providerService.getProviderWithMostOrders());
		
	}
	
	@PostMapping
	public ResponseEntity<Object> newProvider(@Valid @RequestBody Provider provider, BindingResult bindingResult) {
		System.out.println("prueba a ver si llega con datos q no estan en la bd. ");
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
			 Provider result = this.providerService.newProvider(provider);
			 
			 return new ResponseEntity<>(result, HttpStatus.OK);			
		}catch(Exception error) {
			 return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProvider(@PathVariable Integer id, @Valid @RequestBody Provider provider,
													BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		try {			
			Provider result = this.providerService.updateProvider(provider);
		
			 return new ResponseEntity<>(result, HttpStatus.OK);			
		}catch(Exception error) {
			 return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProvider(@PathVariable Integer id){
		try {
			Provider result = this.providerService.deleteProvider(id);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception error) {
			return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
	@DeleteMapping("/rescue/{id}")
	public ResponseEntity<Object> rescueProvider(@PathVariable Integer id){		
		try {
			Provider result = this.providerService.rescueProvider(id);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception error) {
			return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
}
