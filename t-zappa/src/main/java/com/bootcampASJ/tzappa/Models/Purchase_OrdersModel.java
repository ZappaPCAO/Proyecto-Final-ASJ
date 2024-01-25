package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Purchase_Orders")
public class Purchase_OrdersModel {
	
	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[nro_purchase_order] no puede ser nula.")
	@Min(value = 1, message = "[nro_purchase_order] debe ser un numero positivo.")
	@Column(unique = true)
	private Integer nro_purchase_order;
	
	@NotNull(message="[send_date] no puede ser nula.")
	@NotBlank(message="[send_date] no puede estar vacia.")
	private LocalDateTime send_date;
	
	@NotNull(message="[receipt_date] no puede ser nula.")
	@NotBlank(message="[receipt_date] no puede estar vacia.")
	private LocalDateTime receipt_date;
	
	@NotBlank(message="[description] no puede estar vacia.")
	@Size(min = 3, message = "[description] longitud fuera de rango 3-inf.")
	@Column(columnDefinition = "text")
	private String description;
	
	@NotNull(message="[estado] no puede ser nula.")
	@NotBlank(message="[estado] no puede estar vacia.")
	@Size(min = 1, max = 1, message = "[estado] tiene que ser solo un caracter.")
	private char estado;
	
	@NotNull(message="[total] no puede ser nula.")
	@Min(value = 1, message = "[total] debe ser un numero positivo.")
	private Double total;
	
	@NotNull(message="[created_at] no puede ser nula.")
	@NotBlank(message="[created_at] no puede estar vacia.")
	private LocalDateTime created_at;
	
	private LocalDateTime update_at;
	
	@NotNull(message="[is_deleted] no puede ser nula.")
	private Boolean is_deleted;
	
	// FK => Providers
	
	// Metodos
	
	public Purchase_OrdersModel() {}
	
	public Purchase_OrdersModel(Integer id, Integer nro_purchase_order, LocalDateTime send_date,
			LocalDateTime receipt_date, String description, char estado, Double total) {
		this.id = id;
		this.nro_purchase_order = nro_purchase_order;
		this.send_date = send_date;
		this.receipt_date = receipt_date;
		this.description = description;
		this.estado = estado;
		this.total = total;
		this.created_at = LocalDateTime.now();
		this.is_deleted = false;		
	}

	public LocalDateTime getSend_date() {
		return send_date;
	}

	public void setSend_date(LocalDateTime send_date) {
		this.send_date = send_date;
	}

	public LocalDateTime getReceipt_date() {
		return receipt_date;
	}

	public void setReceipt_date(LocalDateTime receipt_date) {
		this.receipt_date = receipt_date;
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

	public LocalDateTime getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}

	public Integer getId() {
		return id;
	}

	public Integer getNro_purchase_order() {
		return nro_purchase_order;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}
	
	public void delete() {
		this.is_deleted = true;
	}	
}
