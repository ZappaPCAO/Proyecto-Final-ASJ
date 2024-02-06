package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.IdenticalRecord;

import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Models.PurchaseOrder;
import com.bootcampASJ.tzappa.Repositories.ArticleRepository;
import com.bootcampASJ.tzappa.Repositories.ProviderRepository;
import com.bootcampASJ.tzappa.Repositories.PurchaseOrderRepository;

import jakarta.transaction.Transactional;

@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	ProviderRepository providerRepository;
	
	public List<PurchaseOrder> getPurchaseOrders() {
		return this.purchaseOrderRepository.findAllByOrderBySendDate();
	}
	
	public PurchaseOrder getPurchaseOrderById(Integer id) {
		return this.purchaseOrderRepository.findById(id).get();
	}
	
	public List<PurchaseOrder> getPurchaseOrdersByProvider(Integer id) {
		
		Provider provider = this.providerRepository.findById(id).get();
		
		return this.purchaseOrderRepository.findByProviderOrderBySendDate(provider);
	}
	
	public List<PurchaseOrder> getPurchaseOrdersByActive(){
		return this.purchaseOrderRepository.findByStateOrderBySendDate('A');
	}
	
	public String getNumPurchaseOrder() {
		// Busca la última orden de compra ordenada por númeroCompra de forma descendente
        Optional<PurchaseOrder> lastPurchaseOrder = Optional.ofNullable(this.purchaseOrderRepository.findLastNumPurchaseOrders());
        
        // Incrementa el número de compra en 1 y formatea como cadena de 8 caracteres con ceros a la izquierda
        String newNumPurchaseOrder = lastPurchaseOrder.map(order -> {
            Integer currentNum = Integer.parseInt(order.getNumPurchaseOrder());
            Integer newNumber = currentNum + 1;
            return String.format("%08d", newNumber);
        }).orElse("00000001"); // Si no hay ninguna orden de compra, comienza desde 1

        return newNumPurchaseOrder;
	}
	
	@Transactional
	public PurchaseOrder newPurchaseOrder(PurchaseOrder purchaseOrder) {
	    
	    PurchaseOrder aux =	this.purchaseOrderRepository.save(purchaseOrder);
	    
	    if(aux == null) {
	    	throw new IdenticalRecord("Paso algo!");
	    }
	    
	   return aux;    
	}
	
	@Transactional
	public PurchaseOrder cancelPurchaseOrder(Integer id) {
		PurchaseOrder purchaseOrder = this.purchaseOrderRepository.findById(id).get();
		
		if(purchaseOrder == null)  
			throw new IdenticalRecord("La entidad no fue encontrada en la base de datos");
		if(purchaseOrder.getState() == 'C' ) 
			throw new IdenticalRecord("Este registro ya esta CANCELADO.");
	
		purchaseOrder.setUpdatedAt(LocalDateTime.now());
		purchaseOrder.setState('C');
		
		return this.purchaseOrderRepository.save(purchaseOrder);
	}
	
	@Transactional
	public PurchaseOrder activatePurchaseOrder(Integer id) {
		PurchaseOrder purchaseOrder = this.purchaseOrderRepository.findById(id).get();
		
		if(purchaseOrder == null)  
			throw new IdenticalRecord("La entidad no fue encontrada en la base de datos");
		if(purchaseOrder.getState() == 'A' ) 
			throw new IdenticalRecord("Este registro ya esta ACTIVO.");
	
		purchaseOrder.setUpdatedAt(LocalDateTime.now());
		purchaseOrder.setState('A');
		
		return this.purchaseOrderRepository.save(purchaseOrder);
	}
	
}