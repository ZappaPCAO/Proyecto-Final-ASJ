package com.bootcampASJ.tzappa.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="taxs_data")
public class TaxData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[cuit] no puede ser nula.")
	@Size(min = 13, max = 13, message = "[cuit] la longitud tiene que ser 13.")
	@Column(unique = true)
	private String cuit;
	
	// FK 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "iva_condition_id", referencedColumnName = "id", nullable = false)
	private IvaCondition ivaCondition;
	
	// Metodos
	
	public TaxData() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public IvaCondition getIvaCondition() {
		return ivaCondition;
	}

	public void setIvaCondition(IvaCondition ivaCondition) {
		this.ivaCondition = ivaCondition;
	}

	@Override
	public String toString() {
		return "TaxData [id=" + id + ", cuit=" + cuit + ", ivaCondition=" + ivaCondition + "]";
	}
}
