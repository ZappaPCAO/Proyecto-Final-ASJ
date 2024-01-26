package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Providers")
public class Provider {
	
	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@NotNull(message="[cod_provider] no puede ser nula.")
	@NotBlank(message="[cod_provider] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[cod_provider] longitud fuera de rango 3-30.")
	@Column(unique = true)
	private String cod_provider;
	
	@NotNull(message="[business_name] no puede ser nula.")
	@NotBlank(message="[business_name] no puede estar vacia.")
	@Size(min = 3, max = 50, message = "[business_name] longitud fuera de rango 3-50.")
	@Column
	private String business_name;
	
	@NotNull(message="[website] no puede ser nula.")
	@NotBlank(message="[website] no puede estar vacia.")
	@Size(min = 3, message = "[website] longitud fuera de rango 3-inf.")
	@Column(unique = true, columnDefinition = "text")
	private String website;
	
	@NotNull(message="[email] no puede ser nula.")
	@NotBlank(message="[email] no puede estar vacia.")
	@Size(min = 4, max = 50, message = "[email] longitud fuera de rango 4-50.")
	@Column(unique = true)
	private String email;
	
	@NotNull(message="[phone] no puede ser nula.")
	@NotBlank(message="[phone] no puede estar vacia.")
	@Size(min = 8, max = 11, message = "[phone] longitud fuera de rango 8-11.")
	@Column(unique = true)
	private String phone;
	
	@NotNull(message="[created_at] no puede ser nula.")
	@NotBlank(message="[created_at] no puede estar vacia.")
	@Column
	private LocalDateTime created_at;
	
	@Column
	private LocalDateTime updated_at;
	
	@NotNull(message="[is_deleted] no puede ser nula.")
	@Column
	private Boolean is_deleted;

	// FK 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sector", referencedColumnName = "id", nullable = false)
	private Sector sector;
	
	@OneToOne
	@JoinColumn(name = "contact_data", referencedColumnName = "id", nullable = false)
    private Contact_Data contact_data;
	
	@OneToOne
	@JoinColumn(name = "location", referencedColumnName = "id", nullable = false)
	private Location location;

	@OneToOne
	@JoinColumn(name = "tax_data", referencedColumnName = "id", nullable = false)
	private Tax_Data tax_data;
	
	// Relacion bidirecc
	
	@OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference
	private List<Article> articles;

//	@OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference
//	private List<Purchase_Order> purchase_orders;
	
	// Metodos

	public Provider() {}
	
	public Provider(String cod_provider, String business_name,
			String website,	String email, String phone) {		
		this.cod_provider = cod_provider;
		this.business_name = business_name;
		this.website = website;
		this.email = email;
		this.phone = phone;
		this.created_at = LocalDateTime.now();
		this.is_deleted = false;
	}

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public String getWebsite() {
		return website;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public Contact_Data getContact_data() {
		return contact_data;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getCod_provider() {
		return cod_provider;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}
	
	public List<Article> getArticles() {
		return articles;
	}
	
	public Sector getSector() {
		return sector;
	}

//	public List<Purchase_Order> getPurchase_orders() {
//		return purchase_orders;
//	}
	
	public Tax_Data getTax_data() {
		return tax_data;
	}
	
	public void delete() {
		this.is_deleted = true;
	}
}
