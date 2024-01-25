package com.bootcampASJ.tzappa.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Taxs_Data")
public class Tax_Data {

	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[cuit] no puede ser nula.")
	@NotBlank(message="[cuit] no puede estar vacia.")
	@Size(min = 13, max = 13, message = "[cuit] la longitud tiene que ser 13.")
	@Column(unique = true)
	private String cuit;
	
	// FK 
	
	@OneToOne(fetch = FetchType.EAGER)
	private Provider provider;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private IVA_Condition iva_condition;
	
	// Metodos
	
	public Tax_Data() {}
	
	public Tax_Data(Integer id, String cuit) {		
		this.id = id;
		this.cuit = cuit;
		// Si no le erro, deberian estar aca tmb las vinculaciones con las fk.
	}

	public Integer getId() {
		return id;
	}

	public String getCuit() {
		return cuit;
	}
}