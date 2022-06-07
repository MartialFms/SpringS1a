package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;


	public interface CategoryRepository extends JpaRepository<Article,Long>{

		public List<Category> findByName(String name);

		}

