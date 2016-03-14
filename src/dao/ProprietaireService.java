package dao;

import meserreurs.MonException;
import java.util.*;

import metier.*;
import persistance.*;

/**
 * Classe DAO pour les propriétaires
 * 
 * @author GERLAND - LETOURNEUR
 */
public class ProprietaireService {
	
	/**
	 * Consulter un propriétaire par Id
	 * Fabrique et renvoie un objet Proprietaire contenant le résultat de la requète
	 * 
	 * @param numero integer
	 */
	public Proprietaire consulterProprietaire(int numero) throws MonException {
		String mysql = "SELECT * FROM proprietaire WHERE id_proprietaire = " + numero;
		List<Proprietaire> proprietaires = consulterListeProprietaires(mysql);
		if (proprietaires.isEmpty())
			return null;
		else {
			return proprietaires.get(0);
		}
	}

	/**
	 * Consulter tous les propriétaires
	 * Fabrique et renvoie les objets proprietaire contenant le résultat de la requète
	 * 
	 * @throws MonException
	 */
	public List<Proprietaire> consulterListeProprietaires() throws MonException {
		String mysql = "SELECT * FROM proprietaire";
		return consulterListeProprietaires(mysql);
	}

	/**
	 * Construire les objects Proprietaire en fonction de la requête passée en paramêtre
	 * 
	 * @param mysql String
	 * @throws MonException
	 */
	private List<Proprietaire> consulterListeProprietaires(String mysql) throws MonException {
		List<Object> rs;
		List<Proprietaire> proprietaires = new ArrayList<Proprietaire>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				Proprietaire proprietaire = new Proprietaire();
				proprietaire.setIdProprietaire(Integer.parseInt(rs.get(index + 0).toString()));
				proprietaire.setNomProprietaire(rs.get(index + 1).toString());
				proprietaire.setPrenomProprietaire(rs.get(index + 2).toString());
				index = index + 3;
				proprietaires.add(proprietaire);
			}

			return proprietaires;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}
}
