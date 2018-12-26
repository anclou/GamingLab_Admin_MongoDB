package fr.gaminglab.admin.service;

import java.util.List;

import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.dto.TotalDTO;

public interface IServiceJeux {
	//Nombre total de parties jouées dans le mois
	public List<TotalDTO> getTotalJeuxJouer();
	
	//Top 5 Jeux qui ont le plus de parties jouées
	public List<GenericDTO> getTop5Jeux();
	
	//Top 5 Joueur qui ont le plus de parties jouées
	public List<GenericDTO> getTop5Joueurs();
}
