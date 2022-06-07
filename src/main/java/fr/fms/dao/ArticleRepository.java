package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Article;

public interface ArticleRepository extends JpaRepository<Article,Long>{
	public List<Article> findByBrand(String brand);							// recherche dans colonne brand où le nom est #
	public List<Article> findByBrandContains(String brand);					// recherche dans colonne brand où le nom est #
	public List<Article> findByBrandAndPrice(String brand, double price);
}
