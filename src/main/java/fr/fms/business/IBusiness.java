package fr.fms.business;

import java.util.List;
import java.util.Scanner;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

public interface IBusiness {
	
// ici afficher la liste
	public List<Article> readArticles();
	public List<Category> readCategories();
	public void addArticles(String description, String brand, double price, Category category);
	public void showArticleWithMethode();

	public void showArticleWithQuery();

	public void showArticleWithDAndM();

	public void updateArticle();

	public void orderAsc();

	public void orderDesc();

	public void showArticles();

	public void UpdateArticle();

	public void addArticle();

	public void deleteArticle();

	// ?????????????????????????????
	public void show5ArticlesByPage();

	public void show5CategoryByPage();
	
	public void addCategory();

	public void updateCategory();

	public void deleteCategory();


	public void showCategory();
}
