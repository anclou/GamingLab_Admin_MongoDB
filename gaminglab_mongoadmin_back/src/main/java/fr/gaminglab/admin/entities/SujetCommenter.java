package fr.gaminglab.admin.entities;

public class SujetCommenter {
	
	private String id;
	private Integer idSujet;
	private Integer idUtilisateur;
	private Integer moisCommentaire;
	
	public SujetCommenter(String id, Integer idSujet, Integer idUtilisateur, Integer moisCommentaire) {
		this.id = id;
		this.idSujet = idSujet;
		this.idUtilisateur = idUtilisateur;
		this.moisCommentaire = moisCommentaire;
	}
	
	public SujetCommenter() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getIdSujet() {
		return idSujet;
	}
	public void setIdSujet(Integer idSujet) {
		this.idSujet = idSujet;
	}
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public Integer getMoisCommentaire() {
		return moisCommentaire;
	}
	public void setMoisCommentaire(Integer moisCommentaire) {
		this.moisCommentaire = moisCommentaire;
	}
	@Override
	public String toString() {
		return "SujetCommenter [id=" + id + ", idSujet=" + idSujet + ", idUtilisateur=" + idUtilisateur
				+ ", moisCommentaire=" + moisCommentaire + "]";
	}
	
}
