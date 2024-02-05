package com.bootcampASJ.tzappa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Models.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	List<PurchaseOrder> findByProvider(Provider provider);
	List<PurchaseOrder> findAllByOrderBySendDate();
}
