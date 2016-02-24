package metier;

import java.io.Serializable;


/**
 * The persistent class for the oeuvrevente database table.
 * 
 */

public class Oeuvrevente extends Oeuvre implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String etatOeuvrevente;
	private float prixOeuvrevente;
	
	public Oeuvrevente(int idOeuvre, String etatOeuvrevente, float prixOeuvrevente, String titreOeuvre, Proprietaire proprietaire) {
		super(idOeuvre, titreOeuvre, proprietaire);

		this.etatOeuvrevente = etatOeuvrevente;
		this.prixOeuvrevente = prixOeuvrevente;
	}

	public Oeuvrevente() {
		super();
	}

	public String getEtatOeuvrevente() {
		return this.etatOeuvrevente;
	}

	public void setEtatOeuvrevente(String etatOeuvrevente) {
		this.etatOeuvrevente = etatOeuvrevente;
	}

	public float getPrixOeuvrevente() {
		return this.prixOeuvrevente;
	}

	public void setPrixOeuvrevente(float prixOeuvrevente) {
		this.prixOeuvrevente = prixOeuvrevente;
	}
}