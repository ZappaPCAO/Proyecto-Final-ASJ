package com.bootcampASJ.tzappa.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@NotNull(message="[cod_article] no puede ser nula.")
	@Size(min = 3, max = 30, message = "[cod_article] longitud fuera de rango 3-30.")
	@Column(name = "cod_article", unique = true)
	private String codArticle;
	
	@Column
	@NotNull(message="[name] no puede ser nula.")
	@Size(min = 3, max = 50, message = "[name] longitud fuera de rango 3-50.")
	private String name;
	
	@Size(min = 3, message = "[description] longitud fuera de rango 3-inf.")
	@Column(columnDefinition = "text")
	private String description;
	
	@NotNull(message="[name] no puede ser nula.")
	@Min(value = 1, message = "[number] debe ser un numero positivo.")
	@Column
	private Double price;
	
	@Size(min = 3, message = "[image] longitud fuera de rango 3-inf.")
	@Column(columnDefinition = "text")
	private String image;
	
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
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
	private Provider provider;
	
	// Metodos

	public Article() {
		this.createdAt = LocalDateTime.now();
		this.isDeleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodArticle() {
		return codArticle;
	}

	public void setCodArticle(String codArticle) {
		this.codArticle = codArticle;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", codArticle=" + codArticle + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", image=" + image + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", isDeleted=" + isDeleted + ", category=" + category + ", provider=" + provider + "]";
	}	
}
