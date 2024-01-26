package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Articles")
public class Article {

	@Column
	@Id
	@NotNull(message="[id] no puede ser nula.")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="[cod_article] no puede ser nula.")
	@NotBlank(message="[cod_article] no puede estar vacia.")
	@Size(min = 3, max = 30, message = "[cod_article] longitud fuera de rango 3-30.")
	@Column(unique = true)
	private String cod_article;
	
	@Column
	@NotNull(message="[name] no puede ser nula.")
	@NotBlank(message="[name] no puede estar vacia.")
	@Size(min = 3, max = 50, message = "[name] longitud fuera de rango 3-50.")
	private String name;
	
	@NotBlank(message="[description] no puede estar vacia.")
	@Size(min = 3, message = "[description] longitud fuera de rango 3-inf.")
	@Column(columnDefinition = "text")
	private String description;
	
	@Column
	@NotNull(message="[name] no puede ser nula.")
	@Min(value = 1, message = "[number] debe ser un numero positivo.")
	private Double price;
	
	@Column
	@NotNull(message="[created_at] no puede ser nula.")
	@NotBlank(message="[created_at] no puede estar vacia.")
	private LocalDateTime created_at;
	
	@Column
	private LocalDateTime updated_at;
	
	@Column
	@NotNull(message="[is_deleted] no puede ser nula.")
	private Boolean is_deleted;
	
	@Column
	private String image;
	
	// FK
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category", referencedColumnName = "id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "provider", referencedColumnName = "id", nullable = false)
//	@JsonBackReference
	private Provider provider;
	
	// Relacion bidirecc
	
//	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonManagedReference
//	private List<Detail> details;
	
//	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "images", referencedColumnName = "id", nullable = false)
//	private List<Image> images;
	
	// Metodos

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Article() {}
	
	public Article(Integer id, String image, String cod_article, String name, String description,
			Double price) {		
		this.id = id;
		this.cod_article = cod_article;
		this.name = name;
		this.description = description;
		this.price = price;
		this.created_at = LocalDateTime.now();
		this.is_deleted = false;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

//	public List<Image> getImages() {
//		return images;
//	}
	
	public LocalDateTime getUpdate_at() {
		return updated_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.updated_at = update_at;
	}

	public Integer getId() {
		return id;
	}

	public String getCod_article() {
		return cod_article;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}
	
	public Category getCategory() {
		return category;
	}

	public Provider getProvider() {
		return provider;
	}
	
	public void delete() {
		this.is_deleted = true;
	}
//	public List<Detail> getDetails() {
//		return details;
//	}
}
