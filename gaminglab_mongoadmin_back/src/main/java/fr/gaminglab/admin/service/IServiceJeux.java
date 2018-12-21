package fr.gaminglab.admin.service;

import java.util.List;

import fr.gaminglab.admin.dto.GenericDTO;

public interface IServiceJeux {
	//Nombre total de parties jouées dans le mois
	public Integer getTotalJeuxJouerByMonth (Integer month);
	
	//Top 5 Jeux qui ont le plus de parties jouées
	public List<GenericDTO> getTop5Jeux();
	
	//Top 5 Joueur qui ont le plus de parties jouées
	public List<GenericDTO> getTop5Joueurs();
}
