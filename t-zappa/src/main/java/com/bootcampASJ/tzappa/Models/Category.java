package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[category] no puede ser nula.")
	@Size(min = 4, max = 30, message = "[category] longitud fuera de rango 4-30.")
	@Column(name="category", unique = true)
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="is_deleted")
	private Boolean isDeleted;
	
	// Metodos
	
	public Category() {
		this.createdAt = LocalDateTime.now();
		this.isDeleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String category) {
		this.name = category;
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + name + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", isDeleted=" + isDeleted + "]";
	}
}
