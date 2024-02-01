package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="sectors")
public class Sector {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[sectorName] no puede ser nula.")
	@NotBlank(message="[sectorName] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[sectorName] longitud fuera de rango 3-30.")
	@Column
	private String sector;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	// Metodos
	
	public Sector() {
		this.createdAt = LocalDateTime.now();
		this.isDeleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
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
		return "Sector [id=" + id + ", sector=" + sector + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", isDeleted=" + isDeleted + "]";
	}
}
