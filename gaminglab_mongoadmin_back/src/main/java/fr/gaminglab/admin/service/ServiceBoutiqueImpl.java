package fr.gaminglab.admin.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.web.client.RestTemplate;

import fr.gaminglab.admin.dao.mongo.DaoBoutique;
import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.entities.TopArticleAchat;
import fr.gaminglab.entity.boutique.Article;

public class ServiceBoutiqueImpl implements IServiceBoutique {
	
	private static final String SLASH = "/";
	private static final String ARTICLE = "/article";
	private static final String CATEGORIE = "/categorie";
	private RestTemplate restTemplate = new RestTemplate();
	private String base_url = "http://localhost:8182/gaminglab/boutique";
	private DaoBoutique daoBoutique = new DaoBoutique();

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
		
		List<TopArticleAchat> listeTopArticles = daoBoutique.getTop5ArticlesAchat();
		
		// 2- Webservice : pour toutes clés de la Map<Integer, Integer> : 
		//		a) getArticleById() : 
		//   		-> GET /article/{idArticle}
		//		b) Créer nouvel objet GenericDTO et l'ajouter à la List<GenericDTO> à retourner
		
		List<GenericDTO> results = new ArrayList<GenericDTO>();
		
		for (int i= 0; i < listeTopArticles.size(); i++) {
			TopArticleAchat topArticle = (TopArticleAchat)listeTopArticles.toArray()[i];
			
			Article article = restTemplate.getForObject(base_url + ARTICLE + SLASH + topArticle.getIdArticle(), Article.class);
			String libelle = article.getLibelle();
			
			GenericDTO dto = new GenericDTO();
			dto.setLibelle(libelle);
			dto.setNombre(topArticle.getNombreAchat());
			
			results.add(dto);
		}
		
		return results;
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
