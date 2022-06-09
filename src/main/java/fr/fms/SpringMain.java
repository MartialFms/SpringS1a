package fr.fms;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.business.SpringExercises;
import fr.fms.business.SpringShopApplication;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringMain implements CommandLineRunner {
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	static SpringExercises exercises;
	static SpringShopApplication tpShop;
	

	public static void main(String[] args) {
		SpringApplication.run(SpringMain.class, args);	
		System.out.println(
				"\n-------------------------------------------------------------------------------------------------"+
				"\n-------------------------------------------------- Menu -----------------------------------------"+
				"\n-------------------------------------------------------------------------------------------------");
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;
		System.out.println("\nChoisissez votre exercice : ");
		System.out.println(" 1/ exercice de base\n 2/ TP du Shop\n 0/ Sortir");
		while (!scan.hasNextInt())
			scan.next();
		menuChoice = scan.nextInt();
		switch (menuChoice) {
		case 1:
			exercises.menu();
			break;
		case 2:
			tpShop.menu();
			break;}
	}

	@Override
	public void run(String... args) throws Exception {
		categoryRepository.deleteAll();
		articleRepository.deleteAll();

		Category smartphone = categoryRepository.save(new Category("Smartphone"));
		Category tablet = categoryRepository.save(new Category("Tablet"));
		Category pc = categoryRepository.save(new Category("PC"));

		articleRepository.save(new Article("S10", "Samsung", 500, smartphone));
		articleRepository.save(new Article("S9", "Samsung", 350, smartphone));
		articleRepository.save(new Article("MI10", "Xiaomi", 100, smartphone));

		articleRepository.save(new Article("A22", "Samsung", 360, smartphone));
		articleRepository.save(new Article("Y62", "Wiko", 130, smartphone));
		articleRepository.save(new Article("Redmi", "Xiaomi", 220, smartphone));

		articleRepository.save(new Article("GalaxyTab", "Samsung", 450, tablet));
		articleRepository.save(new Article("Ipad", "Apple", 350, tablet));
		articleRepository.save(new Article("P11", "Lenovo", 280, tablet));
		articleRepository.save(new Article("M10", "Lenovo", 300, tablet));

		articleRepository.save(new Article("R510", "Asus", 600, pc));
		articleRepository.save(new Article("Cn-510", "HP", 600, pc));
	}
		

}
