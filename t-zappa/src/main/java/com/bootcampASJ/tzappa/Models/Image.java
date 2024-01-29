//package com.bootcampASJ.tzappa.Models;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//
//@Entity
//@Table(name="images")
//public class Image {
//	
//	@Id
//	@NotNull(message="[id] no puede ser nula.")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	
//	@NotNull(message="[url] no puede ser nula.")
//	@NotBlank(message="[url] no puede estar vacia.")
//	@Size(min = 3, max = 50, message = "[url] longitud fuera de rango 3-50.")
//	private String url;
//	
//	// FK
//	
////	@ManyToOne(fetch = FetchType.EAGER)
////	@JoinColumn(name = "article", referencedColumnName = "id", nullable = false)
////	private Article article;
////	
////	@ManyToOne(fetch = FetchType.EAGER)
////	private Provider provider;
//
//	// Metodos
//	
//	public Image() {}
//	
//	public Image(Integer id, String url) {
//		this.id = id;
//		this.url = url;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//}
