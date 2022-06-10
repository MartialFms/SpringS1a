package fr.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

//public interface ArticleRepository extends JpaRepository<Article, Long>
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	// page
	int page = 0;
	Pageable pageable = PageRequest.of(page, 5);
	public Page<Article> findAll(Pageable pageable);
	//
	
	public List<Article> findAll();

	public List<Article> findAllByOrderByIdAsc();

	public List<Article> findAllByOrderByIdDesc();

	public List<Article> findTop5ByOrderByIdDesc(); // affiche 5

	// public List<Article> findById(int id);
	public List<Article> findById(int id);

	public List<Article> findByBrand(String brand); // recherche dans colonne brand où le nom est #

	public List<Article> findByBrandContains(String brand); // recherche dans colonne brand où le nom est #

	public List<Article> findByBrandAndPrice(String brand, double price);

	public List<Article> findByBrandAndDescription(String brand, String info);

	public List<Article> deleteById(int id);

	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
	public List<Article> searchArticles(@Param("x") String kw, @Param("y") double price);

	@Query("select A from Article A where A.id like :x")
	public List<Article> searchArticle(@Param("x") int id);

	@Modifying
	@Query("update Article A set A.description = :d where A.id like :i")
	public void updateArticle(@Param("i") int id, @Param("d") String description);

	@Modifying
	@Query("update Article A set A.description = :d, A.brand = :b, A.price = :p, A.category = :c")
	public void updateShopArticle(@Param("d") String description, @Param("b") String brand, @Param("p") double price,
			@Param("c") Category category);

}
