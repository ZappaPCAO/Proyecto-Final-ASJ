package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="providers")
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@NotNull(message="[cod_provider] no puede ser nula.")
	@Size(min = 3, max = 30, message = "[cod_provider] longitud fuera de rango 3-30.")
	@Column(name = "cod_provider", unique = true)
	private String codProvider;
	
	@NotNull(message="[business_name] no puede ser nula.")
	@Size(min = 3, max = 50, message = "[business_name] longitud fuera de rango 3-50.")
	@Column(name = "business_name", unique = true)
	private String businessName;
	
	@Size(min = 3, message = "[website] longitud fuera de rango 3-inf.")
	@Column(unique = true, columnDefinition = "text")
	private String website;
	
	@NotNull(message="[email] no puede ser nula.")
	@Size(min = 4, max = 50, message = "[email] longitud fuera de rango 4-50.")
	@Column(unique = true)
	private String email;
	
	@NotNull(message="[phone] no puede ser nula.")
	@Size(min = 8, max = 11, message = "[phone] longitud fuera de rango 8-11.")
	@Column(unique = true)
	private String phone;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="is_deleted")
	private Boolean isDeleted;

	// FK 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sector_id", referencedColumnName = "id", nullable = false)
	private Sector sector;
	
	@OneToOne
	@JoinColumn(name = "contact_data_id", referencedColumnName = "id", nullable = false)
    private ContactData contactData;
	
	@OneToOne
	@JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
	private Location location;

	@OneToOne
	@JoinColumn(name = "tax_data_id", referencedColumnName = "id", nullable = false)
	private TaxData taxData;
	
	// Relacion bidirecc
	
	@OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Article> articles;
	
	// Metodos

	public Provider() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodProvider() {
		return codProvider;
	}

	public void setCodProvider(String codProvider) {
		this.codProvider = codProvider;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getWebsite() {
		return website;
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

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public ContactData getContactData() {
		return contactData;
	}

	public void setContactData(ContactData contactData) {
		this.contactData = contactData;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public TaxData getTaxData() {
		return taxData;
	}

	public void setTaxData(TaxData taxData) {
		this.taxData = taxData;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Provider [id=" + id + ", codProvider=" + codProvider + ", businessName=" + businessName + ", website="
				+ website + ", email=" + email + ", phone=" + phone + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", isDeleted=" + isDeleted + ", sector=" + sector + ", contactData=" + contactData
				+ ", location=" + location + ", taxData=" + taxData + ", articles=" + articles + "]";
	}
}
