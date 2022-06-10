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

	/*
	 * =============================================================================
	 * Article 
	 * =============================================================================
	 */
	
	@Override
	public List<Article> showArticles() {
		return  articleRepository.findAll();
	}
	
	@Override
	public List<Article> show5ArticlesByPage() {
		return null;																							// a changer
	}
	
	@Override
	public List<Article> showArticleWithMethode(int targetId) {
		return articleRepository.findById(targetId);		
	}

	@Override
	public List<Article> showArticleWithQuery(int targetId) {
		return articleRepository.searchArticle(targetId);
	}
	
	@Override
	public Article showArticleWithStream(int targetId) {
		Article article = articleRepository.findAll().stream().filter(a -> a.getId().equals(targetId)).findFirst().get();
		
		  // List<>oArticle.stream().filter(Customer::hasOverHundredPoints).collect(Collectors.toList());
		return article;																							// a changer
		}
	@Override
	public List<Article> showArticleWithDAndM(String brand, String description) {
// for (Article article : articleRepository.findByBrandAndDescription(brand, description)) {
		return null;																							// a changer
		}

	@Override
	public void addArticle(String description, String brand, double price, int categoryId) { 	
		Category category =categoryRepository.findAll().stream().filter(c -> c.getId().equals(categoryId)).findFirst().get();
		articleRepository.save(new Article(description, brand, price, category));
	}
	
	@Override
	public void updateArticle(int id, String brand, String description, double price, int categoryId) {
																												// a changer
	}

	@Override
	public void deleteArticle(int targetId) {
		articleRepository.deleteById(targetId);
	}


	/*
	 * =============================================================================
	 * Category
	 * =============================================================================
	 */
	
	@Override
	public List<Category> showCategory() {
		return  categoryRepository.findAll();
	}
	
	@Override
	public List<Article> show5CategoriesByPage() {
		return null;																							// a changer
	}
	
	@Override
	public List<Category> categoryOrderAsc() {
		return categoryRepository.findAllByOrderByIdAsc();
		//showArticles();
	}

	@Override
	public List<Category> categoryOrderDesc() {
		return categoryRepository.findAllByOrderByIdDesc();
		//showArticles();
	}
	
	@Override
	public void addCategory(String name) {
																										// a changer
	}

	@Override
	public void updateCategory(int targetId, String name) {
																										// a changer
	}

	@Override
	public void deleteCategory(int targetId) {
																										// a changer
	}
	
}
