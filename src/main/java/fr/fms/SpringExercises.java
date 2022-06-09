package fr.fms;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringExercises implements CommandLineRunner {
	@Autowired
	private static ArticleRepository articleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	private static Article article = new Article();
	private static Category category = new Category();

	public static void main(String[] args) {
		SpringApplication.run(SpringExercises.class, args);

		System.out.println(
				"-------------------------------------------------- Menu -----------------------------------------");
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;
		System.out.println("\nQue souhaitez vous faire ? ");
		System.out.println(" 1/ Afficher un article (methode 1)\n 2/ Afficher un article (methode 2)\n "
				+ "3/ Afficher un article avec une description + une marque\n 4/ Supprimer un article par son id\n "
				+ "5/ Mettre un article à jour avec son id\n 6/ Organiser les noms de catégories par ordre croissant\n"
				+ "7/ Afficher la liste complete des articles\n 8/ Afficher la liste complete des articles\n"
				+ "9/ Afficher la liste complete des catégories 0/ Sortir");
		while (!scan.hasNextInt())
			scan.next();
		menuChoice = scan.nextInt();
		switch (menuChoice) {
		case 1:
			// Afficher un article (methode 1)
			showArticleWithMethode();
			break;
		case 2:
			// Afficher un article (methode 2)
			showArticleWithQuery();
			break;
		case 3:
			// Afficher un artible avec une description + une marque
			showArticleWithDAndM();
			break;
		case 4:
			// Supprimer un article par son id
			deleteArticle();
			break;
		case 5:
			// Mettre un article à jour avec son id
			updateArticle();
			break;
		case 6:
			// Organiser les noms de catégories par ordre croissant
			orderAsc();
			break;
		case 7:
			orderDesc();
			// Organiser les noms de catégories par ordre décroissant
			break;
		case 8:
			// Afficher la liste complete des articles
			showArticles();
		case 9:
			// Afficher la liste complete des categories
			showCategory();
			break;
		case 0:
			// Exit
			break;
		default:
			System.out.println("\nMauvaise saisie.");
		}
	}

	// ----------------------------------------------------------------------------------------------------------
	public static void showArticleWithMethode() {
		int id;
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		// trouver article methode 1
		for (Article article : articleRepository.findById(targetId)) {
			System.out.println(article);
		}
	}

	public static void showArticleWithQuery() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		// trouver article methode 2
		for (Article article : articleRepository.searchArticle(targetId)) {
			System.out.println(article);
		}
	}

	public static void showArticleWithDAndM() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la marque : ");
		String brand = scanner.nextLine();
		System.out.println("\nSaisissez la description : ");
		String description = scanner.nextLine();
		// trouver article methode 2
		for (Article article : articleRepository.findByBrandAndDescription(description, brand)) {
			System.out.println(article);
		}
	}

	public static void deleteArticle() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		// supprimer article
		articleRepository.deleteById(targetId);
		showArticles();
		}

		// test update
	public static void testUpdateArticle() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		scanId.close();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la nouvelle marque : ");
		String brand = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez la nouvelle description : ");
		String description = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez le nouveau prix : ");
		double price = scanner.nextDouble();
		scanner.close();
		System.out.println("\nSaisissez l'id de la categorie : ");
		while (!scanId.hasNextInt())
			scanId.next();
		int targetCategory = scanId.nextInt();
		scanId.close();
		// supprimer article
		articleRepository.testUpdateArticle(targetId, brand, description, price,
				targetCategory);
		showArticles();

		}
	

	public static void updateArticle() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		scanId.close();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la nouvelle description : ");
		String description = scanner.nextLine();
		scanner.close();
		// supprimer article
		articleRepository.updateArticle(targetId, description);
		showArticles();
		}
	

	public static void orderAsc() {
		articleRepository.findAllByOrderByIdAsc();
		showArticles();
	}
	

	public static void orderDesc() {
		articleRepository.findAllByOrderByIdDesc();
		showArticles();
	}

	public static void showArticles() {
		System.out.println(article);

	}

	public static void showCategory() {
		System.out.println(category);
	}

// ----------------------------------------------------------------------------------------------------------

	/*@Override
	public void run(String... args) throws Exception {
		// categoryRepository.save(new Category("Smartphone"));
		// articleRepository.save(new Article("S9","Samsung",250));
		for (Article article : articleRepository.findByBrand("Samsung")) {
			System.out.println(article);
		}
DEPENDANCE
	}*/
	
	@Override
	public void run(String... args) throws Exception {
		categoryRepository.deleteAll();
		articleRepository.deleteAll();
		Category smartphone = categoryRepository.save(new Category("Smartphone"));
		Category tablet = categoryRepository.save(new Category("Tablet"));
		Category pc = categoryRepository.save(new Category("PC"));
		
		articleRepository.deleteAll();
		articleRepository.save(new Article("S10","Samsung",500, smartphone));
		articleRepository.save(new Article("S9","Samsung",350, smartphone));
		articleRepository.save(new Article("MI10","Xiaomi",100, smartphone));
		
		articleRepository.save(new Article("GalaxyTab","Samsung",450, tablet));
		articleRepository.save(new Article("Ipad","Apple",350, tablet));
		
		articleRepository.save(new Article("R510","Asus",600, pc));
		
		
		}

	}

