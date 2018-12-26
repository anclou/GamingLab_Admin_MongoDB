package fr.gaminglab.admin.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.gaminglab.admin.dao.mongo.DaoArticleAchat;
import fr.gaminglab.admin.dao.mongo.DaoArticleVisite;
import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.entities.Top;
import fr.gaminglab.entity.boutique.Article;

@Component
public class ServiceBoutiqueImpl implements IServiceBoutique {
	
	private static final String SLASH = "/";
	private static final String ARTICLE = "/article";
	private RestTemplate restTemplate = new RestTemplate();
	private String base_url = "http://localhost:8182/gaminglab/boutique";
	private DaoArticleAchat daoArticleAchat = new DaoArticleAchat();
	private DaoArticleVisite daoArticleVisite = new DaoArticleVisite();

	@Override
	public List<TotalDTO> getTotalArticlesAchat() {
		return daoArticleAchat.getTotalArticlesAchat();
	}

	@Override
	public List<GenericDTO> getTop5ArticlesAchat() {
		
		// 1- DaoBoutique MongoDB : getTop5ArticlesAchat() 
		//		--> Map<Integer, Integer> : 
		// 			Clé : id de l'Article
		//			Valeur : nombre de fois l'article a été acheté
		
		List<Top> listeTopArticles = daoArticleAchat.getTop5ArticlesAchat();
		
		// 2- Webservice : pour toutes clés de la Map<Integer, Integer> : 
		//		a) getArticleById() : 
		//   		-> GET /article/{idArticle}
		//		b) Créer nouvel objet GenericDTO et l'ajouter à la List<GenericDTO> à retourner
		
		return getTop5Articles(listeTopArticles);
	}

	@Override
	public List<TotalDTO> getTotalArticlesVisite() {
		return daoArticleVisite.getTotalArticlesVisite();
	}

	@Override
	public List<GenericDTO> getTop5ArticlesVisite() {
		List<Top> listeTopArticles = daoArticleVisite.getTop5ArticlesVisite();
		
		return getTop5Articles(listeTopArticles);
	}
	
	//UTIL
	
	private List<GenericDTO> getTop5Articles (List<Top> listeTop) {
		List<GenericDTO> results = new ArrayList<GenericDTO>();
		
		for (int i= 0; i < listeTop.size(); i++) {
			Top topArticle = (Top)listeTop.toArray()[i];
			
			Article article = restTemplate.getForObject(base_url + ARTICLE + SLASH + topArticle.getId(), Article.class);
			String libelle = article.getLibelle();
			
			GenericDTO dto = new GenericDTO();
			dto.setLibelle(libelle);
			dto.setNombre(topArticle.getNombre());
			
			results.add(dto);
		}
		
		return results;
	}

}
