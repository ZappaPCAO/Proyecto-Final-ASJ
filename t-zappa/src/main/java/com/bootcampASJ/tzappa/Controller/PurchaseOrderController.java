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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcampASJ.tzappa.ErrorHandler;

import com.bootcampASJ.tzappa.Models.PurchaseOrder;
import com.bootcampASJ.tzappa.Services.PurchaseOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/purchase-orders") // localhost:8080/purchase-orders
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@GetMapping // [GET] localhost:8080/proveedores
	public ResponseEntity<Object> getPurchaseOrders() {		
		return ResponseEntity.ok( this.purchaseOrderService.getPurchaseOrders() );
	}
	
	@GetMapping("/{id}") // [GET] localhost:8080/proveedores/3
	public ResponseEntity<Object> getPurchaseOrderById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.purchaseOrderService.getPurchaseOrderById(id));
	}
	
	@GetMapping("/active")
	public ResponseEntity<Object> getPurchaseOrdersByActive(){
		return ResponseEntity.ok(this.purchaseOrderService.getPurchaseOrdersByActive());
	}
	
	@GetMapping("/provider/{id}")
	public ResponseEntity<Object> getPurchaseOrdersByProvider(@PathVariable Integer id){
		
		if(id == 0) {
			return ResponseEntity.ok(this.purchaseOrderService.getPurchaseOrders());
		}
		
		return ResponseEntity.ok(this.purchaseOrderService.getPurchaseOrdersByProvider(id));
	}
	
	@GetMapping("/num-purchase-order")
    public ResponseEntity<String> getNewNumPurchaseOrder() {
        return new ResponseEntity<>(this.purchaseOrderService.getNumPurchaseOrder(), HttpStatus.OK);
    }
	
	@PostMapping
	public ResponseEntity<Object> newPurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {			
			PurchaseOrder result = this.purchaseOrderService.newPurchaseOrder(purchaseOrder);
		   
		    return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception error) {        
			return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);		    
		}
	}
	
	@DeleteMapping("/cancel/{id}")
	public ResponseEntity<Object> cancelPurchaseOrder(@PathVariable Integer id) {
		try {
			PurchaseOrder result = this.purchaseOrderService.cancelPurchaseOrder(id);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception error) {
			return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/activate/{id}")
	public ResponseEntity<Object> activatePurchaseOrder(@PathVariable Integer id) {
		try {
			PurchaseOrder result = this.purchaseOrderService.activatePurchaseOrder(id);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception error) {
			return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
}
