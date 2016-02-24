package metier;

import java.io.Serializable;


/**
 * The persistent class for the oeuvrepret database table.
 * 
 */

public class Oeuvrepret extends Oeuvre implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Oeuvrepret(int idOeuvre, String titreOeuvre, Proprietaire proprietaire) {
		super(idOeuvre, titreOeuvre, proprietaire);
	}

	public Oeuvrepret() {
		super();
	}
}