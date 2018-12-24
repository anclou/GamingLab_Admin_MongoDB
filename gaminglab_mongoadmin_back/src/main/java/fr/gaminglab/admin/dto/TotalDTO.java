package fr.gaminglab.admin.dto;

public class TotalDTO {
	
	public TotalDTO(Integer mois, Integer nombre) {
		this.mois = mois;
		this.nombre = nombre;
	}

	private Integer mois;
	private Integer nombre;
	
	public TotalDTO() {
		super();
	}

	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	public Integer getNombre() {
		return nombre;
	}

	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}
	
}
