package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;
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
@Table(name="Categories")
public class Category {

	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[category] no puede ser nula.")
	@NotBlank(message="[category] no puede estar vacia.")
	@Size(min = 4, max = 30, message = "[category] longitud fuera de rango 4-30.")
	@Column
	private String category;
	
	@NotNull(message="[created_at] no puede ser nula.")
	@NotBlank(message="[created_at] no puede estar vacia.")
	@Column
	private LocalDateTime created_at;
	
	@Column
	private LocalDateTime updated_at;
	
	@Column
	@NotNull(message="[is_deleted] no puede ser nula.")
	private Boolean is_deleted;
	
	// Metodos
	
	public Category() {}
	
	public Category(Integer id, String category) {
		this.id = id;
		this.category = category;
		this.created_at = LocalDateTime.now();
		this.is_deleted = false;
	}
	
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
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
		return updated_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.updated_at = update_at;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void delete() {
		this.is_deleted = true;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}
}
