package com.bootcampASJ.tzappa.Controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Services.ProviderService;
import com.bootcampASJ.tzappa.ErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/proveedores") // localhost:8080/proveedores
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
	@PostMapping
	public ResponseEntity<Object> newProvider(@Valid @RequestBody Provider provider, BindingResult bindingResult) {
		System.out.println("prueba a ver si llega con datos q no estan en la bd. ");
		 if (bindingResult.hasErrors()) {
		        Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult);
		        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		    }
	
		 Optional<Provider> result = this.providerService.newProvider(provider);

	    if (result.isPresent()){
	        return new ResponseEntity<>(result.get(), HttpStatus.OK);
	    }else{
	        Map<String, String> error = Collections.singletonMap("Error", "Error en la integridad de datos. (Ej. Campo Unique)");
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	}
}
