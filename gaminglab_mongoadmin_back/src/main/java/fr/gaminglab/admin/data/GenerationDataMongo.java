package fr.gaminglab.admin.data;

import java.util.Random;

import fr.gaminglab.admin.dao.mongo.DaoArticleAchat;
import fr.gaminglab.admin.dao.mongo.DaoArticleVisite;
import fr.gaminglab.admin.dao.mongo.DaoCategorieConsulter;
import fr.gaminglab.admin.dao.mongo.DaoJeuJouer;
import fr.gaminglab.admin.entities.ArticleAchat;
import fr.gaminglab.admin.entities.ArticleVisite;
import fr.gaminglab.admin.entities.CategorieConsulter;
import fr.gaminglab.admin.entities.JeuJouer;
import fr.gaminglab.admin.service.ServiceForumImpl;

public class GenerationDataMongo {

	private static final Integer MAX_ID_ARTICLE = 66 + 1;
	private static final Integer MAX_ID_UTILISATEUR = 21 + 1;
	private static final Integer MAX_ID_JEU = 14 + 1;
	private static final Integer MAX_ID_CATEGORIE = 6 + 1;
	private static final Integer NOMBRE_DONNEES_PAR_MOIS = 40;

	public static void main(String[] args) {
		System.out.println("coucou");
		DaoArticleAchat daoBoutique = new DaoArticleAchat();
		DaoJeuJouer daoJeux = new DaoJeuJouer();
		DaoArticleVisite daoArticleVisite = new DaoArticleVisite();
		ServiceForumImpl serviceForum = new ServiceForumImpl();
		
		for (int mois = 1; mois <= 12; mois++) {
			//Modif Chris 26/12
			Random random = new Random();
			Integer nbRandom = random.nextInt(NOMBRE_DONNEES_PAR_MOIS);
			
			for (int j = 0; j < nbRandom; j++) {
				createArticleAchat(daoBoutique, mois);
				createJeuJouer(daoJeux, mois);
				createArticleVisite(daoArticleVisite, mois);
				createCategorieConsulter(serviceForum, mois);
			}
		}

	}

	//JeuJouer
	private static void createJeuJouer(DaoJeuJouer daoJeux, int mois) {
		JeuJouer jeuJouer = getJeuJouer(mois);
		daoJeux.create(jeuJouer);
		System.out.println(jeuJouer.toString());
	}
	
	public static JeuJouer getJeuJouer(Integer mois) {
		Integer idJeuRandom;
		Integer idUtilisateurRandom;
		Random random = new Random();
		do{
			idUtilisateurRandom = random.nextInt(MAX_ID_UTILISATEUR);
			idJeuRandom = random.nextInt(MAX_ID_JEU);
		}while(idJeuRandom == 0 || idUtilisateurRandom == 0);

		return new JeuJouer(null, idJeuRandom, idUtilisateurRandom, mois);
	}

	//ArticleAchat
	private static void createArticleAchat(DaoArticleAchat daoBoutique, int mois) {
		ArticleAchat articleAchat = getArticleAchat(mois);
		daoBoutique.create(articleAchat);
		System.out.println(articleAchat.toString());
	}
	
	public static ArticleAchat getArticleAchat(Integer mois) {
		Integer idArticleRandom;
		Integer idUtilisateurRandom;
		Random random = new Random();
		do{
			idUtilisateurRandom = random.nextInt(MAX_ID_UTILISATEUR);
			idArticleRandom = random.nextInt(MAX_ID_ARTICLE);
		}while(idArticleRandom == 0 || idUtilisateurRandom == 0);

		return new ArticleAchat(null, idArticleRandom, idUtilisateurRandom, mois);
	}
	
	//ArticleVisite
	private static void createArticleVisite (DaoArticleVisite daoArticleVisite, int mois) {
		ArticleVisite articleVisite = getArticleVisite(mois);
		daoArticleVisite.create(articleVisite);
		System.out.println(articleVisite.toString());
	}
	
	public static ArticleVisite getArticleVisite(Integer mois) {
		Integer idArticleRandom;
		Integer idUtilisateurRandom;
		Random random = new Random();
		do{
			idUtilisateurRandom = random.nextInt(MAX_ID_UTILISATEUR);
			idArticleRandom = random.nextInt(MAX_ID_ARTICLE);
		}while(idArticleRandom == 0 || idUtilisateurRandom == 0);

		return new ArticleVisite (null, idArticleRandom, idUtilisateurRandom, mois);
	}
	
	//CategorieConsulter
	private static void createCategorieConsulter (ServiceForumImpl serviceForum, int mois) {
		CategorieConsulter categorieConsulter = getCategorieConsulter(mois);
		serviceForum.create(categorieConsulter);
		System.out.println(categorieConsulter.toString());
	}
	
	public static CategorieConsulter getCategorieConsulter(Integer mois) {
		Integer idCategorieRandom;
		Integer idUtilisateurRandom;
		Random random = new Random();
		do{
			idUtilisateurRandom = random.nextInt(MAX_ID_UTILISATEUR);
			idCategorieRandom = random.nextInt(MAX_ID_CATEGORIE);
		}while(idCategorieRandom == 0 || idUtilisateurRandom == 0);

		return new CategorieConsulter (null, idCategorieRandom, idUtilisateurRandom, mois);
	}
	
}
