package dao;

import meserreurs.MonException;
import java.util.*;

import metier.*;
import persistance.*;

/**
 * Classe DAO pour les oeuvres pret
 * 
 * @author GERLAND - LETOURNEUR
 */
public class OeuvrePretService {

	/**
	 * Ajout d'une oeuvre en base de données
	 * 
	 * @param oeuvrePret
	 * @throws MonException
	 */
	public void insertOeuvrePret(Oeuvrepret oeuvrePret)throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "INSERT INTO oeuvrepret  (titre_oeuvrepret, id_proprietaire) values (" +
					"'" + oeuvrePret.getTitreOeuvre() +
					"','" + oeuvrePret.getProprietaire().getIdProprietaire() + 
					"')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	/**
	 * Modification d'une oeuvre en base de données
	 * 
	 * @param adherent
	 * @throws MonException
	 */
	public void updateOeuvrePret(Oeuvrepret oeuvrePret) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "UPDATE oeuvrepret SET " +
					"titre_oeuvrepret = '"+oeuvrePret.getTitreOeuvre()+"', " +
					"id_proprietaire = '" + oeuvrePret.getProprietaire().getIdProprietaire()+"' " +
					"WHERE id_oeuvrepret = "+oeuvrePret.getIdOeuvre();

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	/**
	 * Consulter une oeuvre par Id
	 * Fabrique et renvoie un objet oeuvrepret contenant le résultat de la requète
	 * 
	 * @param numero integer
	 */
	public Oeuvrepret consulterOeuvrePret(int numero) throws MonException {
		String mysql = "SELECT * FROM oeuvrepret WHERE id_oeuvrepret = " + numero;
		List<Oeuvrepret> mesOeuvresPret = consulterListeOeuvresPret(mysql);
		if (mesOeuvresPret.isEmpty())
			return null;
		else {
			return mesOeuvresPret.get(0);
		}
	}
	
	/**
	 * Consulter toutes les oeuvres
	 * Fabrique et renvoie les objets oeuvrepret contenant le résultat de la requète
	 * 
	 * @throws MonException
	 */
	public List<Oeuvrepret> consulterListeOeuvresPret() throws MonException {
		String mysql = "SELECT * FROM oeuvrepret";
		return consulterListeOeuvresPret(mysql);
	}
	
	/**
	 * Consulter les oeuvres par paquet
	 * Fabrique et renvoie les objets oeuvrepret contenant le résultat de la requète
	 * 
	 * @throws MonException
	 */
	public List<Oeuvrepret> consulterListeOeuvresPret(int page, int nombreParPage) throws MonException {
		String mysql = "SELECT * FROM oeuvrepret "+
					   "ORDER BY id_oeuvrepret "+
					   "LIMIT "+(page*nombreParPage)+","+nombreParPage;
		return consulterListeOeuvresPret(mysql);
	}
	
	/**
	 * Construire les objects OeuvrePret en fonction de la requête passée en paramêtre
	 * 
	 * @param mysql String
	 * @throws MonException
	 */
	private List<Oeuvrepret> consulterListeOeuvresPret(String mysql) throws MonException {
		List<Object> rs;
		List<Oeuvrepret> mesOeuvresPret = new ArrayList<Oeuvrepret>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				Oeuvrepret oeuvre = new Oeuvrepret();
				
				oeuvre.setIdOeuvre(Integer.parseInt(rs.get(index + 0).toString()));
				oeuvre.setTitreOeuvre(rs.get(index + 1).toString());
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
	
	/**
	 * Supprimer une oeuvre par Id
	 * 
	 * @param numero integer
	 * @throws MonException
	 */
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
