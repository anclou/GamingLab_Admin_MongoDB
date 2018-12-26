package fr.gaminglab.admin.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import fr.gaminglab.admin.dao.mongo.DaoCategorieConsulter;
import fr.gaminglab.admin.dao.mongo.DaoJeuJouer;
import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.entities.CategorieConsulter;

public class ServiceForumImpl implements IServiceForum {
	
	private static final String SLASH = "/";
	private static final String JEU = "/game/jeu";
	private RestTemplate restTemplate = new RestTemplate();
	private String base_url = "http://localhost:8182/gaminglab";
	private DaoCategorieConsulter daoCategorieConsulter = new DaoCategorieConsulter();
	
	@Override
	public List<TotalDTO> getTotalCategoriesConsulter() {
		return daoCategorieConsulter.getTotalCategoriesConsulter();
	}

	@Override
	public List<GenericDTO> getTop5CategoriesConsulter() {
		return null;
	}

	@Override
	public List<GenericDTO> getTop5SujetsCommenter() {
		return null;
	}

	@Override
	public List<GenericDTO> getTop5SujetsNoter() {
		return null;
	}

	@Override
	public List<GenericDTO> getTop5JoueurCommenter() {
		return null;
	}

	@Override
	public CategorieConsulter create(CategorieConsulter categorieConsulter) {
		return daoCategorieConsulter.create(categorieConsulter);
	}
}
