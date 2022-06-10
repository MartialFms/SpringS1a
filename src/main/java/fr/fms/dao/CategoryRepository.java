package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	public List<Category> findById(int id);

	public List<Category> findByName(String name);
	
	public List<Category> findAllByOrderByIdAsc();

	public List<Category> findAllByOrderByIdDesc();

	public List<Category> findTop5ByOrderByIdDesc();
}
