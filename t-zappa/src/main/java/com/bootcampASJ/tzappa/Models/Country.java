package com.bootcampASJ.tzappa.Models;

import java.util.List;

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
@Table(name="Countries")
public class Country {
	
	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[name] no puede ser nula.")
	@NotBlank(message="[name] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[name] longitud fuera de rango 3-30.")
	private String name;
	
	// Relacion bidirecc
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<State> states;

	// Metodos
	
	public Country() {}
	
	public Country(Integer id, String name) {		
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public List<State> getStates() {
		return states;
	}

	public String getName() {
		return name;
	}
}