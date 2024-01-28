package com.bootcampASJ.tzappa.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="IVA_Conditions")
public class IVA_Condition {
	
	@Id
	@NotNull(message = "[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "[condition] no puede ser nula.")
	@NotBlank(message = "[condition] no puede estar vacia.")
	@Size(min = 3, max = 50, message = "[condition] longitud fuera de rango 3-50.")
	@Column
	private String condition;

	// Metodos
	
	public IVA_Condition() {}
	
	public IVA_Condition(Integer id, String condition) {		
		this.id = id;
		this.condition = condition;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCondition() {
		return condition;
	}	
}
