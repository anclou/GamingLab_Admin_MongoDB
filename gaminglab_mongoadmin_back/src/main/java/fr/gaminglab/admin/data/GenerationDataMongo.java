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
import fr.gaminglab.admin.entities.SujetCommenter;
import fr.gaminglab.admin.entities.SujetNoter;
import fr.gaminglab.admin.service.ServiceForumImpl;

public class GenerationDataMongo {

	private static final Integer MAX_ID_ARTICLE = 66 + 1;
	private static final Integer MAX_ID_UTILISATEUR = 21 + 1;
	private static final Integer MAX_ID_JEU = 14 + 1;
	private static final Integer MAX_ID_CATEGORIE = 6 + 1;
	private static final Integer MAX_ID_SUJET = 28 + 1;
	public static final Integer NOMBRE_DONNEES_MAX_PAR_MOIS = 40;
	public static final Integer NOMBRE_DONNEES_MIN_PAR_MOIS = 4;

	private static Random random = new Random();

	public static void main(String[] args) {
		System.out.println("coucou");
		DaoArticleAchat daoArticleAchat = new DaoArticleAchat();
		DaoJeuJouer daoJeux = new DaoJeuJouer();
		DaoArticleVisite daoArticleVisite = new DaoArticleVisite();
		ServiceForumImpl serviceForum = new ServiceForumImpl();

		for (int mois = 1; mois <= 12; mois++) {

			// Modif Chris 26/12
			Integer nbRandom = random.nextInt(NOMBRE_DONNEES_MAX_PAR_MOIS) + NOMBRE_DONNEES_MIN_PAR_MOIS;

			for (int j = 0; j < nbRandom; j++) {
				createArticleVisiteEtArticleAchat(daoArticleVisite,daoArticleAchat,mois);
			}
			
			nbRandom = random.nextInt(NOMBRE_DONNEES_MAX_PAR_MOIS) + NOMBRE_DONNEES_MIN_PAR_MOIS;

			for (int j = 0; j < nbRandom; j++) {
				createCategorieConsulter(serviceForum, mois);
				createSujetCommenter(serviceForum, mois);
				createSujetNoter(serviceForum, mois);
			}
			
			nbRandom = random.nextInt(NOMBRE_DONNEES_MAX_PAR_MOIS) + NOMBRE_DONNEES_MIN_PAR_MOIS;

			for (int j = 0; j < nbRandom; j++) {
				createJeuJouer(daoJeux, mois);
			}
		}
	}

	/**
	 * Pour un ArticleVisite, il y a une certaine chance qu'il y est un ArticleAchat associé.
	 * @param daoArticleVisite
	 * @param daoArticleAchat
	 * @param mois
	 */
	private static void createArticleVisiteEtArticleAchat(DaoArticleVisite daoArticleVisite, DaoArticleAchat daoArticleAchat,
			Integer mois) {
		ArticleVisite articleVisite = createArticleVisite(daoArticleVisite, mois);

		Integer intRandomForRandom;
		Integer bound;
		do {
			intRandomForRandom = random.nextInt(50);
			bound = intRandomForRandom % 10;
		} while (bound <= 0);

		Integer randomAchat = random.nextInt(bound);
		if (randomAchat >= 5) {
			createArticleAchat(daoArticleAchat, articleVisite.getIdArticle(), articleVisite.getIdUtilisateur(), mois);
		}
	}

	// ArticleAchat
	private static void createArticleAchat(DaoArticleAchat daoBoutique, Integer idArticle, Integer idUtilisateur,
			int mois) {
		ArticleAchat articleAchat = new ArticleAchat(null, idArticle, idUtilisateur, mois);
		daoBoutique.create(articleAchat);
		System.out.println(articleAchat.toString());
	}

	// ArticleVisite
	private static ArticleVisite createArticleVisite(DaoArticleVisite daoArticleVisite, int mois) {
		ArticleVisite articleVisite = getArticleVisite(mois);
		daoArticleVisite.create(articleVisite);
		System.out.println(articleVisite.toString());
		return articleVisite;
	}

	public static ArticleVisite getArticleVisite(Integer mois) {
		Integer idArticleRandom;
		Integer idUtilisateurRandom;
		do {
			idUtilisateurRandom = random.nextInt(MAX_ID_UTILISATEUR);
			idArticleRandom = random.nextInt(MAX_ID_ARTICLE);
		} while (idArticleRandom == 0 || idUtilisateurRandom == 0);

		return new ArticleVisite(null, idArticleRandom, idUtilisateurRandom, mois);
	}

	// JeuJouer
	private static void createJeuJouer(DaoJeuJouer daoJeux, int mois) {
		JeuJouer jeuJouer = getJeuJouer(mois);
		daoJeux.create(jeuJouer);
		System.out.println(jeuJouer.toString());
	}

	public static JeuJouer getJeuJouer(Integer mois) {
		Integer idJeuRandom;
		Integer idUtilisateurRandom;
		do {
			idUtilisateurRandom = random.nextInt(MAX_ID_UTILISATEUR);
			idJeuRandom = random.nextInt(MAX_ID_JEU);
		} while (idJeuRandom == 0 || idUtilisateurRandom == 0);

		return new JeuJouer(null, idJeuRandom, idUtilisateurRandom, mois);
	}

	// CategorieConsulter
	private static void createCategorieConsulter(ServiceForumImpl serviceForum, int mois) {
		CategorieConsulter categorieConsulter = getCategorieConsulter(mois);
		serviceForum.create(categorieConsulter);
		System.out.println(categorieConsulter.toString());
	}

	public static CategorieConsulter getCategorieConsulter(Integer mois) {
		Integer idCategorieRandom;
		Integer idUtilisateurRandom;
		do {
			idUtilisateurRandom = random.nextInt(MAX_ID_UTILISATEUR);
			idCategorieRandom = random.nextInt(MAX_ID_CATEGORIE);
		} while (idCategorieRandom == 0 || idUtilisateurRandom == 0);

		return new CategorieConsulter(null, idCategorieRandom, idUtilisateurRandom, mois);
	}

	// SujetCommenter
	private static void createSujetCommenter(ServiceForumImpl serviceForum, int mois) {
		SujetCommenter sujetCommenter = getSujetCommenter(mois);
		serviceForum.create(sujetCommenter);
		System.out.println(sujetCommenter.toString());
	}

	public static SujetCommenter getSujetCommenter(Integer mois) {
		Integer idSujetRandom;
		Integer idUtilisateurRandom;
		do {
			idUtilisateurRandom = random.nextInt(MAX_ID_UTILISATEUR);
			idSujetRandom = random.nextInt(MAX_ID_SUJET);
		} while (idSujetRandom == 0 || idUtilisateurRandom == 0);

		return new SujetCommenter(null, idSujetRandom, idUtilisateurRandom, mois);
	}

	// SujetNoter
	private static void createSujetNoter(ServiceForumImpl serviceForum, int mois) {
		SujetNoter sujetNoter = getSujetNoter(mois);
		serviceForum.create(sujetNoter);
		System.out.println(sujetNoter.toString());
	}

	public static SujetNoter getSujetNoter(Integer mois) {
		Integer idSujetRandom;
		Integer idUtilisateurRandom;
		do {
			idUtilisateurRandom = random.nextInt(MAX_ID_UTILISATEUR);
			idSujetRandom = random.nextInt(MAX_ID_SUJET);
		} while (idSujetRandom == 0 || idUtilisateurRandom == 0);

		return new SujetNoter(null, idSujetRandom, idUtilisateurRandom, mois);
	}

}
