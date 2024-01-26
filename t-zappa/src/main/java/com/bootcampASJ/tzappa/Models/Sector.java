package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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
@Table(name="Sectors")
public class Sector {
	
	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[sectorName] no puede ser nula.")
	@NotBlank(message="[sectorName] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[sectorName] longitud fuera de rango 3-30.")
	private String sectorName;
	
	@NotNull(message="[created_at] no puede ser nula.")
	@NotBlank(message="[created_at] no puede estar vacia.")
	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
	@NotNull(message="[is_deleted] no puede ser nula.")
	private Boolean is_deleted;
	
	// Relacion bidirecc
	
//	@OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonBackReference
//	private List<Provider> providers;

	// Metodos
	
	public Sector() {}

	public Sector(Integer id, String sectorName) {		
		this.id = id;
		this.sectorName = sectorName;
		this.created_at = LocalDateTime.now();
		this.is_deleted = false;
	}

	public String getSector() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public LocalDateTime getUpdate_at() {
		return updated_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.updated_at = update_at;
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
	
//	public List<Provider> getProviders() {
//		return providers;
//	}
}
