package com.bootcampASJ.tzappa.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String name;
	
	// FK
	
	@ManyToOne(fetch = FetchType.EAGER)
	private State state;
	
	// Relacion bidirecc
	
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Location> locations;

	// Metodos

	public City() {}
	
	public City(Integer id, String name) {		
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public List<Location> getLocations() {
		return locations;
	}
	
	public State getState() {
		return state;
	}
}
