package fr.fms;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringMain implements CommandLineRunner {

	@Autowired
	private IBusinessImpl business;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		 * =============================================================================
		 * Menu Principale
		 * =============================================================================
		 */

		System.out.println(
				"\n\u001b[30m\u001b[47m____________Menu_______________");
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;
		boolean mainMenu = true;
		while (mainMenu == true) {
			System.out.println(" * Choisissez votre exercice * ");
			System.out.println("\u001b[m| 1/ Construction de la BDD   |\n| 2/ exercice de base         |\n| 3/ TP du Shop               |\n|___________________0/ Sortir_|");
			while (!scan.hasNextInt())
				scan.next();
			menuChoice = scan.nextInt();
			switch (menuChoice) {
			case 1:
				buildBdd();
				break;
			case 2:
				exerciseMenu();
				mainMenu = false;
				break;
			case 3:
				tpShopMenu();
				mainMenu = false;
				break;
			default:
				System.out.println("\nMauvaise saisie.");
			}

		}
		scan.close();
	}

	/*
	 * =============================================================================
	 * Menu Exercice
	 * =============================================================================
	 */
	public void exerciseMenu() {
		boolean menu = true;

		System.out.println(
				"-------------------------------------------------- Menu -----------------------------------------");
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;
		while (menu == true) {
			System.out.println("\nQue souhaitez vous faire ? ");
			System.out.println(" 1/ Afficher un article (methode 1)\n "
					+ "2/ Afficher un article avec une description + une marque\n 3/ Supprimer un article par son id\n "
					+ "4/ Mettre un article ?? jour avec son id\n 5/ Organiser les noms de cat??gories par ordre croissant\n "
					+ "6/ Afficher la liste complete des articles par ordre d??croissant\n 7/ Afficher la liste complete des articles\n "
					+ "8/ Afficher la liste complete des cat??gories \n 0/ Sortir");
			while (!scan.hasNextInt())
				scan.next();
			menuChoice = scan.nextInt();
			switch (menuChoice) {
			case 1:
				showOneArticleWithMethode();
				break;
			case 2:
				showArticleWithDAndM();
				break;
			case 3:
				deleteArticle();
				break;
			case 4:
				updateArticle();
				break;
			case 5:
				categoryOrderAsc();
				break;
			case 6:
				categoryOrderDesc();
				break;
			case 7:
				showArticles();
			case 8:
				showCategory();
				break;
			case 0:
				menu = false;
				break;
			default:
				System.out.println("\nMauvaise saisie.");
			}
		}
		scan.close();
	}

	/*
	 * =============================================================================
	 * Menu TP Shop
	 * =============================================================================
	 */

	public void tpShopMenu() {

		System.out.println(
				"-------------------------------------------------- Menu -----------------------------------------");
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;
		System.out.println("\nQue souhaitez vous faire ? ");
		System.out.println(" 1/ Afficher tous les articles\n 2/ Afficher 5 articles par page\n "
				+ "3/ Ajouter un article\n 4/ Mettre ?? jour un article\n " + "5/ Supprimer un article\n "
				+ "6/ Afficher toutes les cat??gories\n 7/ Afficher 5 categories par page\n "
				+ "8/ Ajouter une categorie\n 9/ Mettre ?? jour une categorie\n " + "10/ Supprimer une categorie\n"
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
			show5CategoriesByPage();
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
		scan.close();
	}

	/*
	 * =============================================================================
	 * Mise en forme
	 * =============================================================================
	 */
	
	private String idFormatter( int id ) {
		String strDoubleFormat = String.format("%.2f", id);					//System.out.printf("%.2f", val);
		return strDoubleFormat;
    }
	
	private String priceFormatter( double price ) {
		String strDoubleFormat = String.format("%.2f", price);
		return strDoubleFormat;
    }

	private void readArticleTable(List<Article> articles) {
		System.out.println("\u001b[30m\u001b[47m__________________________________ARTICLES__________________________");
		System.out.printf("| %-3s| %-16s | %-10s | %-8s | %-16s |%n", "ID", "   Article",
				"  Marque", "  Prix", "  Categorie");
		for (Article article : articles) {
			System.out.printf("\u001b[m| %-3s| %-16s | %-10s | %8s | %-16s |%n", article.getId(), article.getDescription(),
					article.getBrand(), priceFormatter(article.getPrice()) + " ???", article.getCategory().getName());
		}
		System.out.println(
				"|____|__________________|____________|__________|__________________|");

	}

	private void readCategoryTable(List<Category> categories) {
		System.out.println("\u001b[30m\u001b[47m________CATEGORIES________");
		System.out.printf("  %-3s | %-16s  %n", "ID", "    Name");
		for (Category category : categories) {
			System.out.printf("\u001b[m| %-3s | %-16s |%n", category.getId(), category.getName());
		}
		System.out.println("|_____|__________________|");

	}

	/*
	 * =============================================================================
	 * Sous-menu Article
	 * =============================================================================
	 */

	private void categoryOrderAsc() {
		business.categoryOrderAsc();
	}

	private void categoryOrderDesc() {
		business.categoryOrderDesc();
	}

	private void showArticles() {
		List<Article> articles = business.showArticles();
		readArticleTable(articles);
	}

	private void showCategory() {
		List<Category> categories = business.categoryOrderDesc();
		readCategoryTable(categories);
	}

	private void showOneArticleWithMethode() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		//
		int menuChoice = 0;

		while (!scanId.hasNextInt())
			scanId.next();
		menuChoice = scanId.nextInt();
		switch (menuChoice) {
		case 1:
			business.showArticleWithMethode(targetId);
			break;
		case 2:
			business.showArticleWithQuery(targetId);
			break;
		case 3:
			business.showArticleWithStream(targetId);
			break;
		}
		scanId.close();
	}

	private void showArticleWithDAndM() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la description : ");
		String description = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez la marque : ");
		String brand = scanner.nextLine();
		scanner.close();
		business.showArticleWithDAndM(brand, description);
	}

	private void updateArticle() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la nouvelle marque : ");
		String brand = scanner.nextLine();
		System.out.println("\nSaisissez la nouvelle description : ");
		String description = scanner.nextLine();
		System.out.println("\nSaisissez le nouveau prix : ");
		double price = scanner.nextDouble();
		System.out.println("\nSaisissez l'id de la categorie : ");
		while (!scanId.hasNextInt())
			scanId.next();
		int categoryId = scanId.nextInt();

		//
		//
		business.updateArticle(targetId, brand, description, price, categoryId);
		scanId.close();
		scanner.close();
	}

	private void addArticle() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez la marque : ");
		String brand = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez le nom : ");
		String description = scanner.nextLine();
		scanner.close();
		System.out.println("\nSaisissez le prix : ");
		double price = scanner.nextDouble();
		scanner.close();
		System.out.println("\nSaisissez l'id de la categorie : ");
		Scanner scanCat = new Scanner(System.in);
		while (!scanCat.hasNextInt())
			scanCat.next();
		int categoryId = scanCat.nextInt();
		scanner.close();

		//
		// ajouter ici l'appel
		business.addArticle(brand, description, price, categoryId);
		scanCat.close();
	}

	private void deleteArticle() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		// supprimer article
		business.deleteArticle(targetId);
		scanId.close();
	}

	private void show5ArticlesByPage() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nPagination fix??e ?? 5 articles/pages ");
		int pageSize = 5;
		System.out.println("\nSaisissez la page : ");
		int page = scanner.nextInt();
		scanner.close();
		business.show5ArticlesByPage(page, pageSize); // a faire
	}

	/*
	 * =============================================================================
	 * Sous-menu Category
	 * =============================================================================
	 */

	private void addCategory() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSaisissez son nom : ");
		String name = scanner.nextLine();
		scanner.close();
		//
		// ajouter ici l'appel
		business.addCategory(name);

	}

	private void updateCategory() {
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
		//
		// ajouter ici l'appel
		business.updateCategory(targetId, name);
	}

	private void deleteCategory() {
		Scanner scanId = new Scanner(System.in);
		int targetId = 0;
		System.out.println("\nSaisissezl'Id : ");
		while (!scanId.hasNextInt())
			scanId.next();
		targetId = scanId.nextInt();
		//
		// ajouter ici l'appel
		business.deleteCategory(targetId);
		scanId.close();
	}

	private void show5CategoriesByPage() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nPagination fix??e ?? 5 cat??gories/pages ");
		int pageSize = 5;
		System.out.println("\nSaisissez la page : ");
		int page = scanner.nextInt();
		scanner.close();
		business.show5CategoriesByPage(page, pageSize); // a faire
	}

	/*
	 * =============================================================================
	 * Constrruction de la BDD
	 * =============================================================================
	 */

	private void buildBdd() {
		Category smartphone = categoryRepository.save(new Category("Smartphone"));
		Category tablet = categoryRepository.save(new Category("Tablet"));
		Category pc = categoryRepository.save(new Category("PC bureau"));
		Category laptop = categoryRepository.save(new Category("Pc portable"));
		Category device = categoryRepository.save(new Category("App connect??s"));
		Category stockage = categoryRepository.save(new Category("Stockage externe"));
		Category accessory = categoryRepository.save(new Category("Accessoire"));

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

		articleRepository.save(new Article("R510", "Asus", 600, laptop));
		articleRepository.save(new Article("Cn-510", "HP", 890, laptop));
		articleRepository.save(new Article("Pavillon7", "HP", 900, laptop));

		articleRepository.save(new Article("M72E", "Lenovo", 450, pc));
		articleRepository.save(new Article("Pavillon TP01", "HP", 580, pc));

		articleRepository.save(new Article("Smartwatch", "HP", 160, device));

		articleRepository.save(new Article("M710", "Lenovo", 460, stockage));

		articleRepository.save(new Article("Screen C24", "Samsung", 180, accessory));

	}

}
