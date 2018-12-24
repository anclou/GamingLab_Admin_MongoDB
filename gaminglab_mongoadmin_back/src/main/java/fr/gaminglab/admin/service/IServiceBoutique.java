package fr.gaminglab.admin.service;

import java.util.HashMap;
import java.util.List;

import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.dto.TotalDTO;

public interface IServiceBoutique {
	public List<TotalDTO> getTotalArticlesAchat();
	
	public List<GenericDTO> getTop5ArticlesAchat();
	
	public Integer getTotalArticlesVisiteByMonth (Integer month);
	
	public List<GenericDTO> getTop5ArticlesVisite();
}
