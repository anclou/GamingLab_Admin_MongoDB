package fr.gaminglab.admin.entities;

public class CategorieConsulter {
	private String id;
	private Integer idCategorie;
	private Integer idUtilisateur;
	private Integer moisConsulter;
	
	public CategorieConsulter() {
	}

	public CategorieConsulter(String id, Integer idCategorie, Integer idUtilisateur, Integer moisConsulter) {
		this.id = id;
		this.idCategorie = idCategorie;
		this.idUtilisateur = idUtilisateur;
		this.moisConsulter = moisConsulter;
	}

	@Override
	public String toString() {
		return "CategorieConsulter [id=" + id + ", idCategorie=" + idCategorie + ", idUtilisateur=" + idUtilisateur
				+ ", moisConsulter=" + moisConsulter + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Integer getMoisConsulter() {
		return moisConsulter;
	}

	public void setMoisConsulter(Integer moisConsulter) {
		this.moisConsulter = moisConsulter;
	}
}
