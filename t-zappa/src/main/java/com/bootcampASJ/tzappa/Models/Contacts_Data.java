package com.bootcampASJ.tzappa.Models;

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
@Table(name="Contacts_Data")
public class Contacts_Data {
	
	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[name] no puede ser nula.")
	@NotBlank(message="[name] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[name] longitud fuera de rango 3-30.")
	private String name;
	
	@NotNull(message="[last_name] no puede ser nula.")
	@NotBlank(message="[last_name] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[last_name] longitud fuera de rango 3-30.")
	private String last_name;
	
	@NotNull(message="[phone] no puede ser nula.")
	@NotBlank(message="[phone] no puede estar vacia.")
	@Size(min = 8, max = 11, message = "[phone] longitud fuera de rango 8-11.")
	@Column(unique = true)
	private String phone;
	
	@NotNull(message="[email] no puede ser nula.")
	@NotBlank(message="[email] no puede estar vacia.")
	@Size(min = 4, max = 50, message = "[email] longitud fuera de rango 4-50.")
	@Column(unique = true)
	private String email;
	
	@NotNull(message="[role] no puede ser nula.")
	@NotBlank(message="[role] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[role] longitud fuera de rango 3-30.")
	private String role;
	
	// FK => Providers
	
	// Metodos
	
	public Contacts_Data() {}
	public Contacts_Data(Integer id, String name, String last_name, String phone,
			String email, String role) {		
		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.phone = phone;
		this.email = email;
		this.role = role;
		// FK irian tmb creo
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getId() {
		return id;
	}	
}
