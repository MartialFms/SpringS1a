package fr.fms.entities;

import java.util.Collection;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

import fr.fms.dao.CategoryRepository;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@Autowired
	private static CategoryRepository categoryRepository;

	@OneToMany(mappedBy = "category")
	private Collection<Article> articles;

	public Category(String name) {
		this.name = name;
	}

	public Category() {
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", articles=" + articles + "]";
	}

// ----------------------------- getter + setter -----------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
