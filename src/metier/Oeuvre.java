package metier;

import java.io.Serializable;


/**
 * The persistent class for the oeuvre database table.
 * 
 */

public class Oeuvre implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int idOeuvre;
	protected String titreOeuvre;
	protected Proprietaire proprietaire;

	
	public Oeuvre(int idOeuvre, String titreOeuvre, Proprietaire proprietaire) {
		super();
		this.idOeuvre = idOeuvre;
		this.titreOeuvre = titreOeuvre;
		this.proprietaire = proprietaire;
	}

	public Oeuvre() {}

	/********
	 * GETTER
	 ********/
	public int getIdOeuvre() {
		return this.idOeuvre;
	}

	public String getTitreOeuvre() {
		return this.titreOeuvre;
	}
	
	public Proprietaire getProprietaire() {
		return this.proprietaire;
	}
	
	/********
	 * SETTER
	 ********/
	public void setIdOeuvre(int idOeuvre) {
		this.idOeuvre = idOeuvre;
	}
	
	public void setTitreOeuvre(String titreOeuvre) {
		this.titreOeuvre = titreOeuvre;
	}
	
	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

}