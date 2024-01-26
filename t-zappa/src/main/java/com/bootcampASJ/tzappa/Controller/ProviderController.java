package com.bootcampASJ.tzappa.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	
	@PostMapping(path = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} ) // [POST] localhost:8080/proveedores
//	@PostMapping()
	public Object newProvider(@RequestBody Provider provider) {
		
		System.out.println("prueba");
//		if(bindingResult.hasErrors()) {
//			Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult) ;
//			
//			System.out.println(errors); 
//			
//			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//		}
		
		return provider;
		
//		return new ResponseEntity<Object>(this.providerService.newProvider(provider) , HttpStatus.OK);
	}
}