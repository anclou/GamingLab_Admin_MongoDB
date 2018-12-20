package fr.gaminglab.admin.service;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.springframework.web.client.RestTemplate;

import fr.gaminglab.admin.entities.GenericDTO;

public class ServiceBoutiqueImpl implements IServiceBoutique {
	
	private static final String SLASH = "/";
	private static final String ARTICLE = "/article";
	private static final String CATEGORIE = "/categorie";
	private RestTemplate restTemplate = new RestTemplate();
	private String base_url = "http://localhost:8182/gaminglab/boutique";

	@Override
	public Integer getTotalArticlesAchatByMonth(Integer month) {
		return null;
	}

	@Override
	public List<GenericDTO> getTop5ArticlesAchat() {
		
		// 1- DaoBoutique MongoDB : getTop5ArticlesAchat() 
		//		--> Map<Integer, Integer> : 
		// 			Clé : id de l'Article
		//			Valeur : nombre de fois l'article a été acheté
		
		// 2- Webservice : pour toutes clés de la Map<Integer, Integer> : 
		//		a) getArticleById() : 
		//   		-> GET /article/{idArticle}
		//		b) Créer nouvel objet GenericDTO et l'ajouter à la List<GenericDTO> à retourner
		
		return null;
	}

	@Override
	public Integer getTotalArticlesVisiteByMonth(Integer month) {
		return null;
	}

	@Override
	public List<GenericDTO> getTop5ArticlesVisite() {
		return null;
	}

}
