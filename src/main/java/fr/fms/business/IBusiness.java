package fr.fms.business;

import java.util.List;
import java.util.Scanner;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

public interface IBusiness {
	
	public void addArticle(String description, String brand, double price, Category category);
	public List<Article> showArticleWithMethode(int targetId) ;

	public List<Article> showArticleWithQuery(int targetId) ;
	
	public List<Article> showArticleWithStream(int targetId) ;
	public List<Article> showArticleWithDAndM(String brand, String description);

	public void updateArticle(String brand, String description, double price, Category category) ;

	public List<Category> categoryOrderAsc();

	public List<Category> categoryOrderDesc();

	public List<Article> showArticles();

	public void deleteArticle(int targetId) ;

	public List<Article> show5ArticlesByPage();
	
	public void addCategory(String name);

	public void updateCategory(int targetId, String name);

	public void deleteCategory(int targetId);


	public List<Category> showCategory();


}
