package com.bootcampASJ.tzappa.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="contacts_data")
public class ContactData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[name] no puede ser nula.")
	@Size(min = 3, max = 30, message = "[name] longitud fuera de rango 3-30.")
	@Column
	private String name;
	
	@NotNull(message="[last_name] no puede ser nula.")
	@Size(min = 3, max = 30, message = "[last_name] longitud fuera de rango 3-30.")
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull(message="[phone] no puede ser nula.")
	@Size(min = 9, max = 14, message = "[phone] longitud fuera de rango 8-11.")
	@Column(unique = true)
	private String phone;
	
	@NotNull(message="[email] no puede ser nula.")
	@Size(min = 4, max = 50, message = "[email] longitud fuera de rango 4-50.")
	@Column(unique = true)
	private String email;
	
	@NotNull(message="[role] no puede ser nula.")
	@Size(min = 3, max = 30, message = "[role] longitud fuera de rango 3-30.")
	@Column
	private String role;

	// Metodos
	
	public ContactData() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@Override
	public String toString() {
		return "Contact_Data [id=" + id + ", name=" + name + ", lastName=" + lastName + ", phone=" + phone + ", email="
				+ email + ", role=" + role + "]";
	}
}
