package fr.gaminglab.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import fr.gaminglab.admin.dao.mongo.DaoCategorieConsulter;
import fr.gaminglab.admin.dao.mongo.DaoSujetCommenter;
import fr.gaminglab.admin.dao.mongo.DaoSujetNoter;
import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.dto.SujetDTO;
import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.entities.CategorieConsulter;
import fr.gaminglab.admin.entities.SujetCommenter;
import fr.gaminglab.admin.entities.SujetNoter;
import fr.gaminglab.admin.entities.Top;
import fr.gaminglab.entity.utilisateur.Joueur;
import fr.gaminglab.forum.entity.CategorieForum;

public class ServiceForumImpl implements IServiceForum {
	
	private static final String SLASH = "/";
	private static final String FORUM = "/forum";
	private static final String USER = "/user";
	private static final String CATEGORIE = "/categorie";
	private static final String SUJET = "/sujet";
	private RestTemplate restTemplate = new RestTemplate();
	private String base_url = "http://localhost:8182/gaminglab";
	private DaoCategorieConsulter daoCategorieConsulter = new DaoCategorieConsulter();
	private DaoSujetCommenter daoSujetCommenter = new DaoSujetCommenter();
	private DaoSujetNoter daoSujetNoter = new DaoSujetNoter();
	
	// Catégorie Consulter
	
	@Override
	public List<TotalDTO> getTotalCategoriesConsulter() {
		return daoCategorieConsulter.getTotalCategoriesConsulter();
	}

	@Override
	public List<GenericDTO> getTop5CategoriesConsulter() {
		List<Top> listeTop = daoCategorieConsulter.getTop5("idCategorie");
		
		List<GenericDTO> results = new ArrayList<GenericDTO>();
		
		for (int i = 0; i < listeTop.size(); i++) {
			Top topCat = (Top)listeTop.toArray()[i];
			
			System.out.println("TOP : top id : "+topCat.getId()+" / nombre : "+topCat.getNombre());
			
			//Peut être pas bon import de CategorieForum
			CategorieForum categorie = restTemplate.getForObject(base_url + FORUM + CATEGORIE + SLASH + topCat.getId(), CategorieForum.class);
			String libelle = categorie.getLibelle();
			
			GenericDTO dto = new GenericDTO();
			dto.setLibelle(libelle);
			dto.setNombre(topCat.getNombre());
			
			results.add(dto);
		}
		
		return results;
	}
	
	@Override
	public CategorieConsulter create(CategorieConsulter categorieConsulter) {
		return daoCategorieConsulter.create(categorieConsulter);
	}
	
	// Sujet Commenter

	@Override
	public List<GenericDTO> getTop5SujetsCommenter() {
		List<Top> listeTop = daoSujetCommenter.getTop5("idSujet");
		
		return getTopSujets(listeTop);
	}
	
	@Override
	public List<GenericDTO> getTop5JoueurCommenter() {
		List<Top> listeTop = daoSujetCommenter.getTop5("idUtilisateur");
		
		List<GenericDTO> results = new ArrayList<GenericDTO>();
		
		for (int i = 0; i < listeTop.size(); i++) {
			Top topCommentateur = (Top)listeTop.toArray()[i];
			
			Joueur joueur = restTemplate.getForObject(base_url + USER + SLASH + topCommentateur.getId(), Joueur.class);
			String libelle = joueur.getPseudo();
			
			GenericDTO dto = new GenericDTO();
			dto.setLibelle(libelle);
			dto.setNombre(topCommentateur.getNombre());
			
			results.add(dto);
		}
		
		return results;
	}
	
	@Override
	public SujetCommenter create(SujetCommenter sujetCommenter) {
		return daoSujetCommenter.create(sujetCommenter);
	}
	
	// Sujet Noter

	@Override
	public List<GenericDTO> getTop5SujetsNoter() {
		List<Top> listeTop = daoSujetNoter.getTop5("idSujet");
		
		return getTopSujets(listeTop);
	}

	@Override
	public SujetNoter create(SujetNoter sujetNoter) {
		return daoSujetNoter.create(sujetNoter);
	}
	
	//UTILITAIRE 
	
	private List<GenericDTO> getTopSujets(List<Top> listeTop) {
		List<GenericDTO> results = new ArrayList<GenericDTO>();
		
		for (int i = 0; i < listeTop.size(); i++) {
			Top topSujet = (Top)listeTop.toArray()[i];
			
			//Toujours pas sur pour l'import
			SujetDTO sujet = restTemplate.getForObject(base_url + FORUM + SUJET + SLASH + topSujet.getId(), SujetDTO.class);
			String libelle = sujet.getLibelle();
			
			GenericDTO dto = new GenericDTO();
			dto.setLibelle(libelle);
			dto.setNombre(topSujet.getNombre());
			
			results.add(dto);
		}
		
		return results;
	}
	
}
