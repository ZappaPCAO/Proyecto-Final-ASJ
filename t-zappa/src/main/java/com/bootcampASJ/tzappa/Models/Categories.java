package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Categories")
public class Categories {

	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[category] no puede ser nula.")
	@NotBlank(message="[category] no puede estar vacia.")
	@Size(min = 4, max = 30, message = "[category] longitud fuera de rango 4-30.")
	private String category;
	
	@NotNull(message="[created_at] no puede ser nula.")
	@NotBlank(message="[created_at] no puede estar vacia.")
	private LocalDateTime created_at;
	
	private LocalDateTime update_at;
	
	@NotNull(message="[is_deleted] no puede ser nula.")
	private Boolean is_deleted;
	
	// Metodos
	
	public Categories() {}
	
	public Categories(Integer id, String category) {
		this.id = id;
		this.category = category;
		this.created_at = LocalDateTime.now();
		this.is_deleted = false;
	}
	
	public Integer getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void delete() { // Borrado logico
		this.is_deleted = true;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}
}
