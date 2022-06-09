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
public class SpringExercises implements CommandLineRunner {

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
		System.out.println(" 1/ Afficher un article (methode 1)\n 2/ Afficher un article (methode 2)\n "
				+ "3/ Afficher un article avec une description + une marque\n 4/ Supprimer un article par son id\n "
				+ "5/ Mettre un article à jour avec son id\n 6/ Organiser les noms de catégories par ordre croissant\n"
				+ "7/ Afficher la liste complete des articles par ordre décroissant\n 8/ Afficher la liste complete des articles\n"
				+ "9/ Afficher la liste complete des catégories 0/ Sortir");
		while (!scan.hasNextInt())
			scan.next();
		menuChoice = scan.nextInt();
		switch (menuChoice) {
		case 1:
			// Afficher un article (methode 1)
			article.showArticleWithMethode();
			break;
		case 2:
			// Afficher un article (methode 2)
			article.showArticleWithQuery();
			break;
		case 3:
			// Afficher un artible avec une description + une marque
			article.showArticleWithDAndM();
			break;
		case 4:
			// Supprimer un article par son id
			article.deleteArticle();
			break;
		case 5:
			// Mettre un article à jour avec son id
			article.updateArticle();
			break;
		case 6:
			// Organiser les noms de catégories par ordre croissant
			category.orderAsc();
			break;
		case 7:
			category.orderDesc();
			// Organiser les noms de catégories par ordre décroissant
			break;
		case 8:
			// Afficher la liste complete des articles
			article.showArticles();
		case 9:
			// Afficher la liste complete des categories
			category.showCategory();
			break;
		case 0:
			// Exit
			break;
		default:
			System.out.println("\nMauvaise saisie.");
		}
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
