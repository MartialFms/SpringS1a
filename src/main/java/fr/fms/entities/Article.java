package fr.fms.entities;

import java.io.Serializable;
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

	// ----------------------------- methodes -----------------------------

	public void showArticleWithMethode() {
		int id;
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		// trouver article methode 1
		Article article = articleRepository.findById(targetId);
			System.out.println(article);
		
	}

	public void showArticleWithQuery() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		// trouver article methode 2
		for (Article article : articleRepository.searchArticle(targetId)) {
			System.out.println(article);
		}
	}

	public void showArticleWithDAndM() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la marque : ");
		String brand = scanner.nextLine();
		System.out.println("\nSaisissez la description : ");
		String description = scanner.nextLine();
		// trouver article methode 2
		for (Article article : articleRepository.findByBrandAndDescription(description, brand)) {
			System.out.println(article);
		}
	}

	public void updateArticle() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		scanId.close();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la nouvelle description : ");
		String description = scanner.nextLine();
		scanner.close();
		// supprimer article
		articleRepository.updateArticle(targetId, description);
		showArticles();
	}

	public void orderAsc() {
		articleRepository.findAllByOrderByIdAsc();
		showArticles();
	}

	public void orderDesc() {
		articleRepository.findAllByOrderByIdDesc();
		showArticles();
	}

	public void showArticles() {
		articleRepository.findAll();
		System.out.println();

	}

	public void UpdateArticle() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		scanId.close();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la nouvelle marque : ");
		String brand = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez la nouvelle description : ");
		String description = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez le nouveau prix : ");
		double price = scanner.nextDouble();
		scanner.close();
		System.out.println("\nSaisissez l'id de la categorie : ");
		while (!scanId.hasNextInt())
			scanId.next();
		int targetCategoryId = scanId.nextInt();
		try {
			Category category = categoryRepository.findById(targetCategoryId);
				articleRepository.updateShopArticle(description, brand, price, category);
			
		} catch (Exception e) {
			System.out.println("Cette categorie n'existe pas !");
		}
		scanId.close();

		showArticles();

	}

	public void addArticle() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la marque : ");
		String brand = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez la description : ");
		String description = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez le prix : ");
		double price = scanner.nextDouble();
		scanner.close();
		System.out.println("\nSaisissez l'id de la categorie : ");
		Scanner scanCat = new Scanner(System.in);
		while (!scanCat.hasNextInt())
			scanCat.next();
		int targetCategory = scanCat.nextInt();
		scanner.close();

		// ajouter ici l'appel

	}

	public void deleteArticle() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		// supprimer article
		articleRepository.deleteById(targetId);
		showArticles();
	}

	// ?????????????????????????????
	public void show5ArticlesByPage() {
		// ajouter ici l'appel
	}

	public void show5CategoryByPage() {
		articleRepository.findTop5ByOrderByIdDesc();
	}
}
