package fr.gaminglab.admin.entities;

public class ArticleAchat {

	private String id;
	private Integer idArticle;
	private Integer idUtilisateur;
	private Integer dateAchat;
	
	public ArticleAchat() { }

	public ArticleAchat(String id, Integer idArticle, Integer idUtilisateur, Integer dateAchat) {
		this.id = id;
		this.idArticle = idArticle;
		this.idUtilisateur = idUtilisateur;
		this.dateAchat = dateAchat;
	}
	
	@Override
	public String toString() {
		return "ArticleAchat [id=" + id + ", idArticle=" + idArticle + ", idUtilisateur=" + idUtilisateur + ", dateAchat=" + dateAchat
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Integer getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Integer dateAchat) {
		this.dateAchat = dateAchat;
	}
	

}
