package fr.gaminglab.admin.entities;

public class SujetNoter {
	
	private String id;
	private Integer idSujet;
	private Integer idUtilisateur;
	private Integer moisNote;

	public SujetNoter() {
	}
	
	public SujetNoter(String id, Integer idSujet, Integer idUtilisateur, Integer moisNote) {
		this.id = id;
		this.idSujet = idSujet;
		this.idUtilisateur = idUtilisateur;
		this.moisNote = moisNote;
	}

	@Override
	public String toString() {
		return "SujetNoter [id=" + id + ", idSujet=" + idSujet + ", idUtilisateur=" + idUtilisateur + ", moisNote="
				+ moisNote + "]";
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
	public Integer getMoisNote() {
		return moisNote;
	}
	public void setMoisNote(Integer moisNote) {
		this.moisNote = moisNote;
	}
	
	

}
