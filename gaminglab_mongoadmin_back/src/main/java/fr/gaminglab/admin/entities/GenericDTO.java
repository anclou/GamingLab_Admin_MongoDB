package fr.gaminglab.admin.entities;

public class GenericDTO {
	private Integer id;
	private String libelle;
	private Integer nombre;
	
	public GenericDTO() {}
	
	public GenericDTO(Integer id, String libelle, Integer nombre) {
		this.id = id;
		this.libelle = libelle;
		this.nombre = nombre;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Integer getNombre() {
		return nombre;
	}
	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}
	
	
}
