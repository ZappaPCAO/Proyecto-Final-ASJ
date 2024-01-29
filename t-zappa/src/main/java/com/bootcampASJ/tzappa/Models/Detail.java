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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="details")
public class Detail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@NotNull(message="[amount] no puede ser nula.")
	@Min(value = 1, message = "[amount] debe ser un numero positivo.")
	@Column
	private Integer amount;
	
	@NotNull(message="[subtotal] no puede ser nula.")
	@Min(value = 1, message = "[subtotal] debe ser un numero positivo.")
	@Column
	private Double subtotal;

	// FK
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
	private Article article;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_order_id", referencedColumnName = "id", nullable = false)
	private PurchaseOrder purchaseOrder;
	
	// Metodos
	
	public Detail() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@Override
	public String toString() {
		return "Detail [id=" + id + ", amount=" + amount + ", subtotal=" + subtotal + ", article=" + article
				+ ", purchaseOrder=" + purchaseOrder + "]";
	}
}
