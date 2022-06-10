package fr.fms.business;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@Service
public class IBusinessImpl implements IBusiness{

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private  ArticleRepository articleRepository;
	
	public List<Article> readArticles() {	
		return  articleRepository.findAll();
	}

	public List<Category> readCategories() {	
		return categoryRepository.findAll();
	}
	
	public void addArticles(String description, String brand, double price, Category category) { 	
		articleRepository.save(new Article(description, brand, price, category));
	}

	
	
	public void showArticleWithMethode(int targetId) {
		// trouver article methode 1
		List<Article> article = articleRepository.findById(targetId);
			System.out.println(article);
		
	}

	public void showArticleWithQuery(int targetId) {
		// trouver article methode 2
		for (Article article : articleRepository.searchArticle(targetId)) {
			System.out.println(article);
		}
	}
	
	public void showArticleWithStream(int targetId) {
		// trouver article methode 2
		  List<>oArticle.stream().filter(Customer::hasOverHundredPoints).collect(Collectors.toList());
		}
	}

	public void showArticleWithDAndM(String brand, String description) {
//
		for (Article article : articleRepository.findByBrandAndDescription(brand, description)) {
			System.out.println(article);
		}
	}

	public void updateArticle(String brand, String description, double price, int categoryId) {
		try {
			List<Category> categories = categoryRepository.findById(categoryId);
				articleRepository.updateShopArticle(description, brand, price, categoryId);
			
		} catch (Exception e) {
			System.out.println("Cette categorie n'existe pas !");
		}

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
		for (Article article : articleRepository.findAll()) {
		System.out.println(article);}

	}


	public void addArticle(String brand, String description, double price, int categoryId) {

		// ajouter ici l'appel

	}

	public void deleteArticle(int targetId) {

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
	
	public void addCategory(String name) {
		// ajouter ici l'appel

	}

	public void updateCategory(int targetId, String name) {
		// ajouter ici l'appel
		showCategory();
	}

	public void deleteCategory(int targetId) {
		// ajouter ici l'appel
		showCategory();
	}


	public void showCategory() {
		for (Category category : categoryRepository.findAll()) {
		System.out.println(category);}
	}


/*
	@Override
	public Article save(String name, String brand, double price) {
		return articleRepository.save(new Article(name, brand, price));
		
	}

	@Override
	public List<Article> addArticles() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
}
