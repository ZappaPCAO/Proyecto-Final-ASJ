package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="purchase_orders")
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@NotNull(message="[num_purchase_order] no puede ser nula.")
	@Min(value = 1, message = "[num_purchase_order] debe ser un numero positivo.")
	@Column(name = "num_purchase_order", unique = true)
	private Integer numPurchaseOrder;
	
	@NotNull(message="[send_date] no puede ser nula.")
	@Column(name = "send_date")
	private LocalDateTime sendDate;
	
	@NotNull(message="[receipt_date] no puede ser nula.")
	@Column(name = "receipt_date")
	private LocalDateTime receiptDate;
	
	@Size(min = 3, message = "[description] longitud fuera de rango 3-inf.")
	@Column(columnDefinition = "text")
	private String description;
	
	@Column
	@NotNull(message="[estado] no puede ser nula.")
	@Size(min = 1, max = 1, message = "[estado] tiene que ser solo un caracter.")
	private char estado;
	
	@NotNull(message="[total] no puede ser nula.")
	@Min(value = 1, message = "[total] debe ser un numero positivo.")
	@Column
	private Double total;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="is_deleted")
	private Boolean isDeleted;
	
	// FK 
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
	private Provider provider;
	
//	// Relacion bidirecc
//	
//	@OneToMany
//	@JoinColumn(name = "details", referencedColumnName = "id", nullable = false)
//	private List<Detail> details;
	
	// Metodos

	public PurchaseOrder() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumPurchaseOrder() {
		return numPurchaseOrder;
	}

	public void setNumPurchaseOrder(Integer numPurchaseOrder) {
		this.numPurchaseOrder = numPurchaseOrder;
	}

	public LocalDateTime getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}

	public LocalDateTime getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(LocalDateTime receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", numPurchaseOrder=" + numPurchaseOrder + ", sendDate=" + sendDate
				+ ", receiptDate=" + receiptDate + ", description=" + description + ", estado=" + estado + ", total="
				+ total + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", isDeleted=" + isDeleted
				+ ", provider=" + provider + ", details=" + details + "]";
	}
}
