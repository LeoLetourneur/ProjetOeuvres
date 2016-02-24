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
			mysql = "INSERT INTO oeuvrevente  (titre_oeuvrevente, etat_oeuvrevente,prix_oeuvrevente, id_proprietaire) values (" +
					"'" + oeuvreVente.getTitreOeuvre() +
					"','" + oeuvreVente.getEtatOeuvrevente() + 
					"','" + oeuvreVente.getPrixOeuvrevente() +
					"','" + oeuvreVente.getProprietaire().getIdProprietaire() + 
					"')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	public void updateOeuvreVente(Oeuvrevente oeuvreVente) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "UPDATE oeuvrevente SET "+
					"titre_oeuvrevente = '"+oeuvreVente.getTitreOeuvre()+"', "+
					"etat_oeuvrevente = '"+oeuvreVente.getEtatOeuvrevente()+"', "+
					"prix_oeuvrevente = '"+oeuvreVente.getPrixOeuvrevente()+"', "+
					"id_proprietaire = '" + oeuvreVente.getProprietaire().getIdProprietaire()+"' "+
					"WHERE id_oeuvrevente = "+oeuvreVente.getIdOeuvre();

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	public Oeuvrevente consulterOeuvrevente(int numero) throws MonException {
		String mysql = "SELECT * FROM oeuvrevente WHERE id_oeuvrevente = " + numero;
		List<Oeuvrevente> mesOeuvresVentes = consulterListeOeuvresVentes(mysql);
		if (mesOeuvresVentes.isEmpty())
			return null;
		else {
			return mesOeuvresVentes.get(0);
		}
	}
	
	public List<Oeuvrevente> consulterListeOeuvresVentes() throws MonException {
		String mysql = "SELECT * FROM oeuvrevente";
		return consulterListeOeuvresVentes(mysql);
	}
	
	public List<Oeuvrevente> consulterListeOeuvresVentes(int page, int nombreParPage) throws MonException {
		String mysql = "SELECT * FROM oeuvrevente "+
					   "ORDER BY id_oeuvrevente "+
					   "LIMIT "+(page*nombreParPage)+","+nombreParPage;
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
				
				oeuvre.setIdOeuvre(Integer.parseInt(rs.get(index + 0).toString()));
				oeuvre.setTitreOeuvre(rs.get(index + 1).toString());
				oeuvre.setEtatOeuvrevente(rs.get(index + 2).toString());
				oeuvre.setPrixOeuvrevente(Float.parseFloat(rs.get(index + 3).toString())); 
				int idProprietaire = Integer.parseInt(rs.get(index + 4).toString());
				
				ProprietaireService pService = new ProprietaireService();
				Proprietaire proprietaire = pService.consulterProprietaire(idProprietaire);
				oeuvre.setProprietaire(proprietaire);

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
