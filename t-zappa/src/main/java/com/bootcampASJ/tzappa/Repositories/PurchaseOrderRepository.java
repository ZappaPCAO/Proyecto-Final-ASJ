package com.bootcampASJ.tzappa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Models.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	List<PurchaseOrder> findByProviderOrderBySendDate(Provider provider);
	List<PurchaseOrder> findAllByOrderBySendDate();
	
	@Query(value = "SELECT TOP 1 * FROM purchase_orders ORDER BY CAST(num_purchase_order AS INT) DESC", nativeQuery = true)
	PurchaseOrder findLastNumPurchaseOrders();
	
    List<PurchaseOrder> findByStateOrderBySendDate(Character character);
}
