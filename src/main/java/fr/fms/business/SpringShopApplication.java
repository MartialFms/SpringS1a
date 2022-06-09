package fr.fms.business;

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

	private static Article article = new Article();
	private static Category category = new Category();

	public static void main(String[] args) {
		SpringApplication.run(SpringExercises.class, args);
		menu();
	}

	public static void menu() {

		System.out.println(
				"-------------------------------------------------- Menu -----------------------------------------");
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;
		System.out.println("\nQue souhaitez vous faire ? ");
		System.out.println(" 1/ Afficher tous les articles\n 2/ Afficher 5 articles par page\n "
				+ "3/ Ajouter un article\n 4/ Mettre à jour un article\n " + "5/ Supprimer un article\n "
				+ "6/ Afficher toutes les catégories\n 7/ Afficher 5 categories par page\n "
				+ "8/ Ajouter une categorie\n 9/ Mettre à jour une categorie\n " + "10/ Supprimer une categorie\n"
				+ "0/ Sortir");
		while (!scan.hasNextInt())
			scan.next();
		menuChoice = scan.nextInt();
		switch (menuChoice) {
		case 1:
			article.showArticles();
			category.showCategory();
			break;
		case 2:
			article.show5ArticlesByPage();
			break;
		case 3:
			article.addArticle();
			break;
		case 4:
			article.updateArticle();
			break;
		case 5:
			article.deleteArticle();
			break;
		case 6:
			category.show5CategoryByPage();
			break;
		case 7:
			category.addCategory();
			break;
		case 8:
			category.updateCategory();
		case 9:
			category.deleteCategory();
		case 0:
			// Exit
			break;
		default:
			System.out.println("\nMauvaise saisie.");
		}
		scan.close();
	}

	@Override
	public void run(String... args) throws Exception {


	}

}
