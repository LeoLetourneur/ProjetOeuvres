package dao;

import meserreurs.MonException;
import java.util.*;

import metier.*;
import persistance.*;

public class AdherentService {

	/**
	 * Ajout d'un adhérent en base de données
	 * 
	 * @param adherent
	 * @throws MonException
	 */
	public void insertAdherent(Adherent adherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "INSERT INTO adherent  (nom_adherent, prenom_adherent, ville_adherent) values ("+
					"'" + adherent.getNomAdherent() + 
					"','" + adherent.getPrenomAdherent() + 
					"','" + adherent.getVilleAdherent() + 
					"')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	/**
	 * Modification d'un adhérent en base de données
	 * 
	 * @param adherent
	 * @throws MonException
	 */
	public void updateAdherent(Adherent adherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "UPDATE adherent SET "+
					"nom_adherent = '"+adherent.getNomAdherent()+"', "+
					"prenom_adherent = '"+adherent.getPrenomAdherent()+"', "+
					"ville_adherent = '"+adherent.getVilleAdherent()+"' "+
					"WHERE id_adherent = "+adherent.getIdAdherent();

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	/**
	 * Consulter un adhérent par Id
	 * Fabrique et renvoie un objet adhérent contenant le résultat de la requète
	 * 
	 * @param numero integer
	 */
	public Adherent consulterAdherent(int numero) throws MonException {
		String mysql = "SELECT * FROM adherent WHERE id_adherent = " + numero;
		List<Adherent> mesAdh = consulterListeAdherents(mysql);
		if (mesAdh.isEmpty())
			return null;
		else {
			return mesAdh.get(0);
		}
	}

	/**
	 * Consulter tous les adhérents
	 * Fabrique et renvoie les objets adhérent contenant le résultat de la requète
	 * 
	 * @throws MonException
	 */
	public List<Adherent> consulterListeAdherents() throws MonException {
		String mysql = "SELECT * FROM adherent";
		return consulterListeAdherents(mysql);
	}
	
	/**
	 * Consulter les adhérents par paquet
	 * Fabrique et renvoie les objets adhérent contenant le résultat de la requète
	 * 
	 * @throws MonException
	 */
	public List<Adherent> consulterListeAdherents(int page, int nombreParPage) throws MonException {
		String mysql = "SELECT * FROM adherent "+
					   "ORDER BY id_adherent "+
					   "LIMIT "+(page*nombreParPage)+","+nombreParPage;
		return consulterListeAdherents(mysql);
	}

	/**
	 * Construire les objects Adherent en fonction de la requête passée en paramêtre
	 * 
	 * @param mysql String
	 * @throws MonException
	 */
	private List<Adherent> consulterListeAdherents(String mysql) throws MonException {
		List<Object> rs;
		List<Adherent> mesAdherents = new ArrayList<Adherent>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				Adherent unA = new Adherent();
				unA.setIdAdherent(Integer.parseInt(rs.get(index + 0).toString()));
				unA.setNomAdherent(rs.get(index + 1).toString());
				unA.setPrenomAdherent(rs.get(index + 2).toString());
				unA.setVilleAdherent(rs.get(index + 3).toString());
				index = index + 4;
				mesAdherents.add(unA);
			}

			return mesAdherents;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	/**
	 * Supprimer un adhérent par Id
	 * 
	 * @param numero integer
	 * @throws MonException
	 */
	public boolean deleteAdherent(int id) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "DELETE FROM adherent WHERE id_adherent = "+id;

			unDialogueBd.insertionBD(mysql);
			return true;
		} catch (MonException e) {
			throw e;
		}
	}
}
