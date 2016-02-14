package dao;

import meserreurs.MonException;
import java.util.*;

import metier.*;
import persistance.*;

public class OeuvreVenteService {

	
	public void insertOeuvreVente(Oeuvrevente oeuvreVente)throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "insert into oeuvrevente  (titre_oeuvrevente,etat_oeuvrevente,prix_oeuvrevente, id_proprietaire)  " + "values ('"
					+ oeuvreVente.getTitreOeuvrevente();
			mysql += "','" + oeuvreVente.getEtatOeuvrevente() + "','" + oeuvreVente.getPrixOeuvrevente();
			mysql +=  "','" + oeuvreVente.getProprietaire().getIdProprietaire() + "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	public void updateOeuvreVente(Oeuvrevente oeuvreVente) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "UPDATE oeuvrevente "+
					"SET titre_oeuvrevente = '"+oeuvreVente.getTitreOeuvrevente()+"', "+
					"etat_oeuvrevente = '"+oeuvreVente.getEtatOeuvrevente()+"', "+
					"prix_oeuvrevente = '"+oeuvreVente.getPrixOeuvrevente()+"', "+
					"id_proprietaire = '" + oeuvreVente.getProprietaire().getIdProprietaire()+"' "+
					"WHERE id_oeuvrevente = "+oeuvreVente.getIdOeuvrevente();

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	public Oeuvrevente consulterOeuvrevente(int numero) throws MonException {
		String mysql = "select * from oeuvrevente where id_oeuvrevente=" + numero;
		List<Oeuvrevente> mesOeuvresVentes = consulterListeOeuvresVentes(mysql);
		if (mesOeuvresVentes.isEmpty())
			return null;
		else {
			return mesOeuvresVentes.get(0);
		}
	}
	
	public List<Oeuvrevente> consulterListeOeuvresVentes() throws MonException {
		String mysql = "select * from oeuvrevente";
		return consulterListeOeuvresVentes(mysql);
	}
	
	private List<Oeuvrevente> consulterListeOeuvresVentes(String mysql) throws MonException {
		List<Object> rs;
		List<Oeuvrevente> mesOeuvresVentes = new ArrayList<Oeuvrevente>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				Oeuvrevente oeuvre = new Oeuvrevente();
				
				oeuvre.setIdOeuvrevente(Integer.parseInt(rs.get(index + 0).toString()));
				oeuvre.setTitreOeuvrevente(rs.get(index + 1).toString());
				oeuvre.setEtatOeuvrevente(rs.get(index + 2).toString());
				oeuvre.setPrixOeuvrevente(Integer.parseInt(rs.get(index + 3).toString())); 
				Proprietaire prop = new Proprietaire();
				prop.setIdProprietaire(Integer.parseInt(rs.get(index + 4).toString()));
				oeuvre.setProprietaire(prop);

				index = index + 5;
				mesOeuvresVentes.add(oeuvre);
			}

			return mesOeuvresVentes;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}
	
	public boolean deleteOeuvreVente(int id) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "DELETE FROM oeuvrevente WHERE id_oeuvrevente = "+id;

			unDialogueBd.insertionBD(mysql);
			return true;
		} catch (MonException e) {
			throw e;
		}
	}
}
