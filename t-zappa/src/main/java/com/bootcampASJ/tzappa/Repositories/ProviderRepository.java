package com.bootcampASJ.tzappa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Models.Sector;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
	List<Provider> findBySector(Sector sector);
	List<Provider> findAllByOrderByBusinessName();
	List<Provider> findByIsDeletedFalse();
	
	@Query(value = "SELECT * FROM providers p \r\n"
    		+ "          WHERE p.id = (SELECT TOP 1 o.provider_id \r\n"
    		+ "                        FROM purchase_orders o \r\n"
    		+ "                        GROUP BY o.provider_id \r\n"
    		+ "                        ORDER BY COUNT(o.provider_id) DESC \r\n"
    		+ "                        )", nativeQuery = true)
    Provider findWithMostOrders();
}
