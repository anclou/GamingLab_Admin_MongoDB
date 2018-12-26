package fr.gaminglab.admin.entities;

public class Top {

	private Integer id;
	private Integer nombre;
	
	public Top() {
	}

	public Top(Integer idArticle, Integer nombreAchat) {
		super();
		this.id = idArticle;
		this.nombre = nombreAchat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idArticle) {
		this.id = idArticle;
	}

	public Integer getNombre() {
		return nombre;
	}

	public void setNombre(Integer nombreAchat) {
		this.nombre = nombreAchat;
	}
	

}
