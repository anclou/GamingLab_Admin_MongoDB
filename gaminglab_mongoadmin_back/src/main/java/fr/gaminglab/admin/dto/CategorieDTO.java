package fr.gaminglab.admin.dto;

import java.io.Serializable;

public class CategorieDTO implements Serializable {
	
	private Integer idCategorie;
	private String libelle;

	public CategorieDTO() {}

    public CategorieDTO(Integer idCategorie, String libelle) {
    	this.idCategorie = idCategorie;
        this.libelle = libelle;
    }

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}
    
}
