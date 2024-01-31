package com.bootcampASJ.tzappa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcampASJ.tzappa.Services.IvaConditionService;

@RestController
@RequestMapping("/iva-condition") // localhost:8080/iva-condition
public class IvaConditionController {

	@Autowired
	IvaConditionService ivaConditionService;
	
	@GetMapping // [GET] localhost:8080/iva-condition
	public ResponseEntity<Object> getProviders() {		
		return ResponseEntity.ok( this.ivaConditionService.getIvaCondition() );
	}
}
