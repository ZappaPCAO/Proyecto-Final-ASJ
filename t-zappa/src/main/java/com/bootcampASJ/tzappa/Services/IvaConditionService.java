package com.bootcampASJ.tzappa.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.IvaCondition;
import com.bootcampASJ.tzappa.Repositories.IvaConditionRepository;

@Service
public class IvaConditionService {
	@Autowired
	IvaConditionRepository ivaConditionRepository;
	
	public List<IvaCondition> getIvaCondition() {
		return this.ivaConditionRepository.findAll();
	}
}
