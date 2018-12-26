package fr.gaminglab.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import fr.gaminglab.admin.dao.mongo.DaoArticleAchat;
import fr.gaminglab.admin.dao.mongo.DaoArticleVisite;
import fr.gaminglab.admin.dao.mongo.DaoJeuJouer;
import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.entities.Top;
import fr.gaminglab.entity.boutique.Article;
import fr.gaminglab.entity.jeu.Jeu;
import fr.gaminglab.entity.utilisateur.Joueur;

public class ServiceJeuxImpl implements IServiceJeux {
	
	private static final String SLASH = "/";
	private static final String JEU = "/game/jeu";
	private static final String USER = "/user";
	private RestTemplate restTemplate = new RestTemplate();
	private String base_url = "http://localhost:8182/gaminglab";
	private DaoJeuJouer daoJeuJouer = new DaoJeuJouer();

	@Override
	public List<TotalDTO> getTotalJeuxJouer() {
		return daoJeuJouer.getTotalJeuxJouer();
	}

	@Override
	public List<GenericDTO> getTop5Jeux() {
		List<Top> listeTop = daoJeuJouer.getTop5JeuxJouer();
		
		List<GenericDTO> results = new ArrayList<GenericDTO>();
		
		for (int i= 0; i < listeTop.size(); i++) {
			Top topJeu = (Top)listeTop.toArray()[i];
			
			Jeu jeu = restTemplate.getForObject(base_url + JEU + SLASH + topJeu.getId(), Jeu.class);
			String libelle = jeu.getLibelle();
			
			GenericDTO dto = new GenericDTO();
			dto.setLibelle(libelle);
			dto.setNombre(topJeu.getNombre());
			
			results.add(dto);
		}
		
		return results;
	}

	@Override
	public List<GenericDTO> getTop5Joueurs() {
		List<Top> listeTop = daoJeuJouer.getTop5JoueursJouer();
		
		List<GenericDTO> results = new ArrayList<GenericDTO>();
		
		for (int i= 0; i < listeTop.size(); i++) {
			Top topJoueur = (Top)listeTop.toArray()[i];
			
			Joueur joueur = restTemplate.getForObject(base_url + USER + SLASH + topJoueur.getId(), Joueur.class);
			String libelle = joueur.getPseudo();
			
			GenericDTO dto = new GenericDTO();
			dto.setLibelle(libelle);
			dto.setNombre(topJoueur.getNombre());
			
			results.add(dto);
		}
		
		return results;
	}

}
