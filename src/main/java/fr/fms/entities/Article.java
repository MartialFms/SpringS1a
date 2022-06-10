package fr.fms.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;

@Entity
public class Article implements Serializable {
	private static final long serialVersionId = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String brand;
	private double price;

	@Autowired
	private static ArticleRepository articleRepository;

	@Autowired
	private static CategoryRepository categoryRepository;

	@ManyToOne
	private Category category;

	@Override
	public String toString() {
		return "Article [id=" + id + ", description=" + description + ", brand=" + brand + ", price=" + price
				+ ", category=" + category + "]";
	}

	public Article(String description, String brand, double price) {
		this.description = description;
		this.brand = brand;
		this.price = price;
	}

	public Article(String description, String brand, double price, Category category) {
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.category = category;
	}

	public Article() {
	}

	// ----------------------------- getter + setter -----------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
