package fr.gaminglab.admin.entities;

public class JeuJouer {

	private Integer idJeu;
	private Integer idUtilisateur;
	private Integer moisJouer;
	private Integer id;
	
	
	public JeuJouer(Integer idJeu, Integer idUtilisateur, Integer mois) {
		super();
		this.idJeu = idJeu;
		this.idUtilisateur = idUtilisateur;
		this.moisJouer = mois;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JeuJouer [idJeu=" + idJeu + ", idUtilisateur=" + idUtilisateur + ", moisJouer=" + moisJouer + ", id="
				+ id + "]";
	}

	/**
	 * @return the idJeu
	 */
	public Integer getIdJeu() {
		return idJeu;
	}
	/**
	 * @param idJeu the idJeu to set
	 */
	public void setIdJeu(Integer idJeu) {
		this.idJeu = idJeu;
	}
	/**
	 * @return the idUtilisateur
	 */
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}
	/**
	 * @param idUtilisateur the idUtilisateur to set
	 */
	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	/**
	 * @return the mois
	 */
	public Integer getMoisJouer() {
		return moisJouer;
	}
	/**
	 * @param mois the mois to set
	 */
	public void setMoisJouer(Integer moisJouer) {
		this.moisJouer = moisJouer;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
}
