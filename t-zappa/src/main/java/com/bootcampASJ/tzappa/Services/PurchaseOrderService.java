package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.Detail;
import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Models.PurchaseOrder;
import com.bootcampASJ.tzappa.Repositories.ArticleRepository;
import com.bootcampASJ.tzappa.Repositories.ProviderRepository;
import com.bootcampASJ.tzappa.Repositories.PurchaseOrderRepository;
import com.bootcampASJ.tzappa.utils.ExceptionCustom;
import com.bootcampASJ.tzappa.utils.dataValidation;

import jakarta.transaction.Transactional;

@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	ProviderRepository providerRepository;
	
	// Validaciones
	private dataValidation data = new dataValidation();
	
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
        Optional<PurchaseOrder> lastPurchaseOrder = Optional.ofNullable(this.purchaseOrderRepository.findLastNumPurchaseOrders());
        
        String newNumPurchaseOrder = lastPurchaseOrder.map(order -> {
            Integer currentNum = Integer.parseInt(order.getNumPurchaseOrder());
            Integer newNumber = currentNum + 1;
            return String.format("%08d", newNumber);
        }).orElse("00000001"); // Si no hay ninguna orden de compra, comienza desde 1
        
        return newNumPurchaseOrder;
	}
	
	@Transactional
	public PurchaseOrder newPurchaseOrder(PurchaseOrder purchaseOrder) {
		List<PurchaseOrder> storedPurchaseOrders = this.purchaseOrderRepository.findAll(); 
		
		if(!data.validatePurchaseOrder(purchaseOrder))
			throw new ExceptionCustom("Hay inconsistencias en los datos, verifique y vuelva a mandar.");	
		
		if(!(purchaseOrder.getProvider().getId() > 0) )
			throw new ExceptionCustom("Debe seleccionar un Proveedor.");
		
		for(PurchaseOrder order : storedPurchaseOrders) {
			if( (purchaseOrder.getNumPurchaseOrder().toLowerCase()).equals(order.getNumPurchaseOrder().toLowerCase()) )
				throw new ExceptionCustom("Ya hay un registro asociado a ese nro de orden.");			
		}		
		
		for (Detail detail : purchaseOrder.getDetails()) {
			detail.setPurchaseOrder(purchaseOrder);
		}
	    
		return this.purchaseOrderRepository.save(purchaseOrder);  
	}
	
	@Transactional
	public PurchaseOrder cancelPurchaseOrder(Integer id) {
		PurchaseOrder purchaseOrder = this.purchaseOrderRepository.findById(id).get();
		
		if(purchaseOrder == null)  
			throw new ExceptionCustom("Este registro no fue encontrado en la base de datos.");
		if(purchaseOrder.getState() == 'C') 
			throw new ExceptionCustom("Esta orden ya esta CANCELADA.");
	
		purchaseOrder.setUpdatedAt(LocalDateTime.now());
		purchaseOrder.setState('C');
		
		return this.purchaseOrderRepository.save(purchaseOrder);
	}
	
	@Transactional
	public PurchaseOrder activatePurchaseOrder(Integer id) {
		PurchaseOrder purchaseOrder = this.purchaseOrderRepository.findById(id).get();
		
		if(purchaseOrder == null)  
			throw new ExceptionCustom("La entidad no fue encontrada en la base de datos");
		if(purchaseOrder.getState() == 'A' ) 
			throw new ExceptionCustom("Esta orden ya esta ACTIVA.");
	
		purchaseOrder.setUpdatedAt(LocalDateTime.now());
		purchaseOrder.setState('A');
		
		return this.purchaseOrderRepository.save(purchaseOrder);
	}
	
}
