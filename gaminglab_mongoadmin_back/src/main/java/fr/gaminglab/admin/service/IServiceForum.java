package fr.gaminglab.admin.service;

import java.util.List;

import fr.gaminglab.admin.entities.GenericDTO;

public interface IServiceForum {
	public Integer getTotalCategoriesConsulterByMonth (Integer month);
	
	public List<GenericDTO> getTop5CategoriesConsulter();
	
	public List<GenericDTO> getTop5SujetsCommenter();
	
	public List<GenericDTO> getTop5SujetsNoter();
	
	public List<GenericDTO> getTop5JoueurCommenter();
}
