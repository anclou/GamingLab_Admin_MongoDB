package fr.gaminglab.admin.dto;

import java.io.Serializable;
import java.util.Date;

import fr.gaminglab.entity.utilisateur.Joueur;

public class SujetDTO implements Serializable {
	
	private Integer idSujet;
	private String libelle;
	private String descriptif;
	private Date dateCreation;
	private Integer note;
	private CategorieDTO categorieForum;
	private Joueur joueur;
	private Integer nombreCommentaire;
	
	public SujetDTO() { }

    public SujetDTO(Integer idSujet, String libelle, String descriptif, Date dateCreation, Integer note, CategorieDTO categorieForum, Joueur joueur, Integer nombreCommentaire) {
    	this.idSujet = idSujet;
		this.libelle = libelle;
		this.descriptif = descriptif;
		this.dateCreation = dateCreation;
		this.note = note;
		this.categorieForum = categorieForum;
		this.joueur = joueur;
		this.nombreCommentaire = nombreCommentaire;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public CategorieDTO getCategorieForum() {
		return categorieForum;
	}

	public void setCategorieForum(CategorieDTO categorieForum) {
		this.categorieForum = categorieForum;
	}

	public Integer getIdSujet() {
		return idSujet;
	}

	public void setIdSujet(Integer idSujet) {
		this.idSujet = idSujet;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Integer getNombreCommentaire() {
		return nombreCommentaire;
	}

	public void setNombreCommentaire(Integer nombreCommentaire) {
		this.nombreCommentaire = nombreCommentaire;
	}

}
