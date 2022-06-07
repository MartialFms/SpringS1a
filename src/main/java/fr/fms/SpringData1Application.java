package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;

@SpringBootApplication
public class SpringData1Application implements CommandLineRunner{
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	

	public static void main(String[] args) {
		SpringApplication.run(SpringData1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// categoryRepository.save(new Category("Smartphone"));
		// articleRepository.save(new Article("S9","Samsung",250));
		for(Article article : articleRepository.findByBrand("Samsung")) {
			System.out.println(article);
		}
		
	}
}
