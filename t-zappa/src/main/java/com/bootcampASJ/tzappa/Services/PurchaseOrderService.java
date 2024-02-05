package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.IdenticalRecord;

import com.bootcampASJ.tzappa.Models.Detail;
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
	
//	public List<PurchaseOrder> getArticlesByActive(){
//		return this.articleRepository.findByIsDeletedFalse();
//	}
	
	public PurchaseOrder getPurchaseOrderById(Integer id) {
		return this.purchaseOrderRepository.findById(id).get();
	}
	
	public List<PurchaseOrder> getPurchaseOrdersByProvider(Integer id) {
		
		Provider provider = this.providerRepository.findById(id).get();
		
		return this.purchaseOrderRepository.findByProvider(provider);
	}
	
	@Transactional
	public PurchaseOrder newPurchaseOrder(PurchaseOrder purchaseOrder) {
	    
	    PurchaseOrder aux =	this.purchaseOrderRepository.save(purchaseOrder);
	    
	    if(aux == null) {
	    	throw new IdenticalRecord("Paso algo!");
	    }
	    
	   return aux;    
	}
}
