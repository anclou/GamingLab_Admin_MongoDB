package fr.gaminglab.admin.entities;

public class TopArticleAchat {

	private Integer idArticle;
	private Integer nombreAchat;
	
	public TopArticleAchat() {
	}

	public TopArticleAchat(Integer idArticle, Integer nombreAchat) {
		super();
		this.idArticle = idArticle;
		this.nombreAchat = nombreAchat;
	}

	public Integer getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	public Integer getNombreAchat() {
		return nombreAchat;
	}

	public void setNombreAchat(Integer nombreAchat) {
		this.nombreAchat = nombreAchat;
	}
	

}
