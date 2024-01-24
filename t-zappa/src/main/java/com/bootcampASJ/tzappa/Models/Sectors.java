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
@Table(name="Sectors")
public class Sectors {
	
	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[sector] no puede ser nula.")
	@NotBlank(message="[sector] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[sector] longitud fuera de rango 3-30.")
	private String sector;
	
	@NotNull(message="[created_at] no puede ser nula.")
	@NotBlank(message="[created_at] no puede estar vacia.")
	private LocalDateTime created_at;
	
	private LocalDateTime update_at;
	
	@NotNull(message="[is_deleted] no puede ser nula.")
	private Boolean is_deleted;

	// Metodos
	
	public Sectors() {}
	
	public Sectors(Integer id, String sector) {		
		this.id = id;
		this.sector = sector;
		this.created_at = LocalDateTime.now();
		this.is_deleted = false;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
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

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void delete() {
		this.is_deleted = true;
	}
	
	public Boolean getIs_deleted() {
		return is_deleted;
	}		
}