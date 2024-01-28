package com.bootcampASJ.tzappa.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Cities")
public class City {

	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[name] no puede ser nula.")
	@NotBlank(message="[name] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[name] longitud fuera de rango 3-30.")
	@Column
	private String name;
	
	// FK
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state", referencedColumnName = "id", nullable = false)
	private State state;

	// Metodos

	public City() {}
	
	public City(Integer id, String name) {		
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}
	
	public State getState() {
		return state;
	}
}
