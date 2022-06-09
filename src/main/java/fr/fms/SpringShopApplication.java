package fr.fms;

import java.util.List;
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
public class SpringShopApplication implements CommandLineRunner {
	@Autowired
	private static ArticleRepository articleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	private static Article article = new Article();
	private static Category category = new Category();

	public static void main(String[] args) {
		SpringApplication.run(SpringShopApplication.class, args);

		System.out.println(
				"-------------------------------------------------- Menu -----------------------------------------");
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;
		System.out.println("\nQue souhaitez vous faire ? ");
		System.out.println(" 1/ Afficher tous les articles\n 2/ Afficher 5 articles par page\n "
				+ "3/ Ajouter un article\n 4/ Mettre à jour un article\n "
				+ "5/ Supprimer un article\n "
				+ "6/ Afficher toutes les catégories\n 7/ Afficher 5 categories par page\n "
				+ "8/ Ajouter une categorie\n 9/ Mettre à jour une categorie\n "
				+ "10/ Supprimer une categorie\n"
				+ "0/ Sortir");
		while (!scan.hasNextInt())
			scan.next();
		menuChoice = scan.nextInt();
		switch (menuChoice) {
		case 1:
			showArticles();
			showCategory();
			break;
		case 2:
			show5ArticlesByPage();
			break;
		case 3:
addArticle();
			break;
		case 4:
			updateArticle();
			break;
		case 5:
			deleteArticle();
			break;
		case 6:
			show5CategoryByPage();
			break;
		case 7:
			addCategory();
			break;
		case 8:
			updateCategory();
		case 9:
			deleteCategory();
		case 0:
			// Exit
			break;
		default:
			System.out.println("\nMauvaise saisie.");
		}
	}

	// ----------------------------------------------------------------------------------------------------------

	public static void UpdateArticle() {
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
	
	public static void addArticle() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la marque : ");
		String brand = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez la description : ");
		String description = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez le prix : ");
		double price = scanner.nextDouble();
		scanner.close();
		System.out.println("\nSaisissez l'id de la categorie : ");
		Scanner scanCat = new Scanner(System.in);
		while (!scanCat.hasNextInt())
			scanCat.next();
		int targetCategory = scanCat.nextInt();
		scanner.close();

		// ajouter ici l'appel

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
	
	// ?????????????????????????????
	public static void show5ArticlesByPage() {
		// ajouter ici l'appel
	}

	
	public static void addCategory() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez son nom : ");
		String name = scanner.nextLine();
		scanner.close();
		// ajouter ici l'appel

		}
	

	public static void updateCategory() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		scanId.close();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez son nouveau nom : ");
		String name = scanner.nextLine();
		scanner.close();
		// ajouter ici l'appel
		showCategory();
		}
	

	public static void deleteCategory() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		// ajouter ici l'appel
		showCategory();
		}
	
	// ?????????????????????????????
	public static void show5CategoryByPage() {
		articleRepository.findTop5ByOrderByIdDesc();	
	}

	public static void showCategory() {
		System.out.println(category);
	}
	
	public static void showArticles() {
		System.out.println(article);
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
		
		articleRepository.save(new Article("S10","Samsung",500, smartphone));
		articleRepository.save(new Article("S9","Samsung",350, smartphone));
		articleRepository.save(new Article("MI10","Xiaomi",100, smartphone));
		
		articleRepository.save(new Article("A22","Samsung",360, smartphone));
		articleRepository.save(new Article("Y62","Wiko",130, smartphone));
		articleRepository.save(new Article("Redmi","Xiaomi",220, smartphone));
		
		articleRepository.save(new Article("GalaxyTab","Samsung",450, tablet));
		articleRepository.save(new Article("Ipad","Apple",350, tablet));
		articleRepository.save(new Article("P11","Lenovo",280, tablet));
		articleRepository.save(new Article("M10","Lenovo",300, tablet));
		
		articleRepository.save(new Article("R510","Asus",600, pc));
		articleRepository.save(new Article("Cn-510","HP",600, pc));
		
		}

	}
	
	

