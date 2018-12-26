package fr.gaminglab.admin.entities;

public class ArticleVisite {

	private String id;
	private Integer idArticle;
	private Integer idUtilisateur;
	private Integer dateVisite;
	
	public ArticleVisite() {
	}

	public ArticleVisite(String id, Integer idArticle, Integer idUtilisateur, Integer dateVisite) {
		super();
		this.id = id;
		this.idArticle = idArticle;
		this.idUtilisateur = idUtilisateur;
		this.dateVisite = dateVisite;
	}

	@Override
	public String toString() {
		return "ArticleVisite [id=" + id + ", idArticle=" + idArticle + ", idUtilisateur=" + idUtilisateur
				+ ", dateVisite=" + dateVisite + "]";
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

	public Integer getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(Integer dateVisite) {
		this.dateVisite = dateVisite;
	}

	
	
}
