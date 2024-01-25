package com.bootcampASJ.tzappa.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Locations")
public class Location {
	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[street] no puede ser nula.")
	@NotBlank(message="[street] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[street] longitud fuera de rango 3-30.")
	private String street;
	
	@Min(value = 1, message = "[number] debe ser mayor o igual a 1.")
    @Max(value = 10000, message = "[number] debe ser menor o igual a 10.000 .")
	private Integer number;
	
	@NotNull(message="[postal_code] no puede ser nula.")
	@NotBlank(message="[postal_code] no puede estar vacia.")
	@Size(min = 3, max = 8, message = "[postal_code] longitud fuera de rango 3-8.")
	private String postal_code;
	
	// FK 
	
	@OneToOne(fetch = FetchType.EAGER)
	private Provider provider;

	@ManyToOne(fetch = FetchType.EAGER)
	private City city;
	
	// Metodos

	public Location() {}
	
	public Location( Integer id, String street, Integer number, String postal_code) {
		this.id = id;
		this.street = street;
		this.number = number;
		this.postal_code = postal_code;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public Provider getProvider() {
		return provider;
	}

	public City getCity() {
		return city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public Integer getId() {
		return id;
	}
}
