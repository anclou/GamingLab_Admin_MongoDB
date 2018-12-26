package fr.gaminglab.admin.service;

import java.util.List;

import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.entities.CategorieConsulter;

public interface IServiceForum {
	public List<TotalDTO> getTotalCategoriesConsulter();
	
	public List<GenericDTO> getTop5CategoriesConsulter();
	
	public List<GenericDTO> getTop5SujetsCommenter();
	
	public List<GenericDTO> getTop5SujetsNoter();
	
	public List<GenericDTO> getTop5JoueurCommenter();
	
	public CategorieConsulter create (CategorieConsulter categorieConsulter);
}
