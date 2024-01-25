package com.bootcampASJ.tzappa.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Details")
public class Detail {
	
	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[amount] no puede ser nula.")
	@Min(value = 1, message = "[amount] debe ser un numero positivo.")
	private Integer amount;
	
	@NotNull(message="[subtotal] no puede ser nula.")
	@Min(value = 1, message = "[subtotal] debe ser un numero positivo.")
	private Double subtotal;

	// FK
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Article article;

	@ManyToOne(fetch = FetchType.EAGER)
	private Purchase_Order purchase_order;
	
	// Metodos
	
	public Detail() {}
	
	public Detail(Integer id, Integer amount, Double subtotal) {		
		this.id = id;
		this.amount = amount;
		this.subtotal = subtotal;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Integer getId() {
		return id;
	}
	public Article getArticle() {
		return article;
	}

	public Purchase_Order getPurchase_order() {
		return purchase_order;
	}

	public Integer getAmount() {
		return amount;
	}
}