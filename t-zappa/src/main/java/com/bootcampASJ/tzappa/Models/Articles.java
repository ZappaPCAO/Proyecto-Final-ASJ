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
@Table(name="Contacts_Data")
public class Articles {

	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[cod_article] no puede ser nula.")
	@NotBlank(message="[cod_article] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[cod_article] longitud fuera de rango 3-30.")
	@Column(unique = true)
	private String cod_article;
	
	@NotNull(message="[name] no puede ser nula.")
	@NotBlank(message="[name] no puede estar vacia.")
	@Size(min = 3, max = 50, message = "[name] longitud fuera de rango 3-50.")
	private String name;
	
	@NotBlank(message="[description] no puede estar vacia.")
	@Size(min = 3, message = "[description] longitud fuera de rango 3-inf.")
	@Column(columnDefinition = "text")
	private String description;
	
	@NotNull(message="[name] no puede ser nula.")
	@Min(value = 1, message = "[number] debe ser un numero positivo.")
	private Double price;
	
	@NotNull(message="[created_at] no puede ser nula.")
	@NotBlank(message="[created_at] no puede estar vacia.")
	private LocalDateTime created_at;
	
	private LocalDateTime update_at;
	
	@NotNull(message="[is_deleted] no puede ser nula.")
	private Boolean is_deleted;
	
	// FK => Categories
	// FK => Providers
	
	// Metodos
	
	public Articles() {}
	
	public Articles(Integer id, String cod_article, String name, String description,
			Double price) {		
		this.id = id;
		this.cod_article = cod_article;
		this.name = name;
		this.description = description;
		this.price = price;
		this.created_at = LocalDateTime.now();
		this.is_deleted = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getCod_article() {
		return cod_article;
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
