package com.bootcampASJ.tzappa.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="locations")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[street] no puede ser nula.")
	@Size(min = 3, max = 30, message = "[street] longitud fuera de rango 3-30.")
	@Column
	private String street;
	
	@Min(value = 1, message = "[number] debe ser mayor o igual a 1.")
    @Max(value = 99999, message = "[number] debe ser menor o igual a 99.999.")
	@Column
	private Integer number;
	
	@NotNull(message="[postal_code] no puede ser nula.")
	@Size(min = 3, max = 8, message = "[postal_code] longitud fuera de rango 3-8.")
	@Column(name = "postal_code")
	private String postalCode;
	
	// FK 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
	private City city;
	
	// Metodos

	public Location() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", street=" + street + ", number=" + number + ", postalCode=" + postalCode
				+ ", city=" + city + "]";
	}
}
