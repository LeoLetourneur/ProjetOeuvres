package dao;

import meserreurs.MonException;
import java.util.*;

import metier.*;
import persistance.*;

public class OeuvrePretService {

	public void insertOeuvrePret(Oeuvrepret oeuvrePret)throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "INSERT INTO oeuvrepret  (titre_oeuvrepret, id_proprietaire) values (" +
					"'" + oeuvrePret.getTitreOeuvrepret() +
					"','" + oeuvrePret.getProprietaire().getIdProprietaire() + 
					"')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	public void updateOeuvrePret(Oeuvrepret oeuvrePret) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "UPDATE oeuvrepret SET " +
					"titre_oeuvrevente = '"+oeuvrePret.getTitreOeuvrepret()+"', " +
					"id_proprietaire = '" + oeuvrePret.getProprietaire().getIdProprietaire()+"' " +
					"WHERE id_oeuvrepret = "+oeuvrePret.getIdOeuvrepret();

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	public Oeuvrepret consulterOeuvrePret(int numero) throws MonException {
		String mysql = "SELECT * FROM oeuvrepret WHERE id_oeuvrepret = " + numero;
		List<Oeuvrepret> mesOeuvresPret = consulterListeOeuvresPret(mysql);
		if (mesOeuvresPret.isEmpty())
			return null;
		else {
			return mesOeuvresPret.get(0);
		}
	}
	
	public List<Oeuvrepret> consulterListeOeuvresPret() throws MonException {
		String mysql = "SELECT * FROM oeuvrepret";
		return consulterListeOeuvresPret(mysql);
	}
	
	public List<Oeuvrepret> consulterListeOeuvresPret(int page, int nombreParPage) throws MonException {
		String mysql = "SELECT * FROM oeuvrepret "+
					   "ORDER BY id_oeuvrepret "+
					   "LIMIT "+(page*nombreParPage)+","+nombreParPage;
		return consulterListeOeuvresPret(mysql);
	}
	
	private List<Oeuvrepret> consulterListeOeuvresPret(String mysql) throws MonException {
		List<Object> rs;
		List<Oeuvrepret> mesOeuvresPret = new ArrayList<Oeuvrepret>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				Oeuvrepret oeuvre = new Oeuvrepret();
				
				oeuvre.setIdOeuvrepret(Integer.parseInt(rs.get(index + 0).toString()));
				oeuvre.setTitreOeuvrepret(rs.get(index + 1).toString());
				int idProprietaire = Integer.parseInt(rs.get(index + 2).toString());
				
				ProprietaireService pService = new ProprietaireService();
				Proprietaire proprietaire = pService.consulterProprietaire(idProprietaire);
				oeuvre.setProprietaire(proprietaire);

				index = index + 3;
				mesOeuvresPret.add(oeuvre);
			}

			return mesOeuvresPret;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}
	
	public boolean deleteOeuvrePret(int id) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "DELETE FROM oeuvrepret WHERE id_oeuvrepret = "+id;

			unDialogueBd.insertionBD(mysql);
			return true;
		} catch (MonException e) {
			throw e;
		}
	}
}
