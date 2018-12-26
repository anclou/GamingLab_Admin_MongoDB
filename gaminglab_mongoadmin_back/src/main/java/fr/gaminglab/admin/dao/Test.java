package fr.gaminglab.admin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.gaminglab.admin.dao.mongo.DaoArticleAchat;
import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.entities.Top;
import fr.gaminglab.admin.service.IServiceBoutique;
import fr.gaminglab.admin.service.ServiceBoutiqueImpl;

public class Test {

	private DaoArticleAchat daoBoutique = new DaoArticleAchat();
	private static ServiceBoutiqueImpl serviceBoutique = new ServiceBoutiqueImpl();
	
	
	public static void main(String[] args) {
//		List<TopArticleAchat> topArticles = daoBoutique.getTop5ArticlesAchat();
//		
//		for (int i = 0; i < topArticles.size(); i++) {
//			TopArticleAchat topArticle = (TopArticleAchat)topArticles.toArray()[i];
//			System.out.println("Top " + i+1 + " article : " + topArticle.getIdArticle() + " / " + topArticle.getNombreAchat());
//		}
		
		List<GenericDTO> listeDto = serviceBoutique.getTop5ArticlesAchat();
		
		for (int i = 0; i < listeDto.size(); i++) {
			GenericDTO dto = (GenericDTO)listeDto.toArray()[i];
			System.out.println("Top " + i+1 + " article : Libelle : " + dto.getLibelle() + " / Nombre d'achat" + dto.getNombre());
		}
	}
}
