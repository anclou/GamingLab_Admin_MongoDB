package fr.gaminglab.admin.data;

import java.util.Random;

import fr.gaminglab.admin.dao.mongo.DaoArticleAchat;
import fr.gaminglab.admin.dao.mongo.DaoJeux;
import fr.gaminglab.admin.entities.ArticleAchat;
import fr.gaminglab.admin.entities.JeuJouer;

public class GenerationDataMongo {

	private static final Integer MAX_ID_ARTICLE = 66 + 1;
	private static final Integer MAX_ID_UTILISATEUR = 21 + 1;
	private static final Integer MAX_ID_JEU = 14 + 1;
	private static final Integer MAX_ID_CATEGORIE = 6 + 1;
	private static final Integer NOMBRE_DONNEES_PAR_MOI = 10;

	public static void main(String[] args) {
		System.out.println("coucou");
		DaoArticleAchat daoBoutique = new DaoArticleAchat();
		DaoJeux daoJeux = new DaoJeux();
		for (int mois = 1; mois <= 12; mois++) {

			for (int j = 0; j <= NOMBRE_DONNEES_PAR_MOI; j++) {
				createArticleAchat(daoBoutique, mois);
				createJeuJouer(daoJeux, mois);
			}
		}

	}

	private static void createJeuJouer(DaoJeux daoJeux, int mois) {
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

		return new JeuJouer(idJeuRandom, idUtilisateurRandom, mois);
	}

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
	
}
