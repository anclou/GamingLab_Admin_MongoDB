package fr.gaminglab.admin.service;

import java.util.HashMap;
import java.util.List;

import fr.gaminglab.admin.entities.GenericDTO;

public interface IServiceBoutique {
	public Integer getTotalArticlesAchatByMonth(Integer month);
	
	public List<GenericDTO> getTop5ArticlesAchat();
	
	public Integer getTotalArticlesVisiteByMonth (Integer month);
	
	List<GenericDTO> getTop5ArticlesVisite();
}
