package fr.fms.business;

import java.util.List;

import org.springframework.data.domain.Page;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

public interface IBusiness {

	public void addArticle(String description, String brand, double price, int categoryId);

	public List<Article> showArticleWithMethode(int targetId);

	public List<Article> showArticleWithQuery(int targetId);

	public Article showArticleWithStream(int targetId);

	public List<Article> showArticleWithDAndM(String brand, String description);

	public void updateArticle(int id, String brand, String description, double price, int categoryId);

	public List<Category> categoryOrderAsc();

	public List<Category> categoryOrderDesc();

	public List<Article> showArticles();

	public void deleteArticle(int targetId);

	public Page<Category> show5ArticlesByPage(int page, int pageSize);

	public Page<Category> show5CategoriesByPage(int page, int pageSize);

	public void addCategory(String name);

	public void updateCategory(int targetId, String name);

	public void deleteCategory(int targetId);

	public List<Category> showCategory();

}
