package dao;

import meserreurs.MonException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import metier.*;
import persistance.*;

/**
 * Classe DAO pour les réservations
 * 
 * @author GERLAND - LETOURNEUR
 */
public class ReservationService {
	
	/**
	 * Ajout d'une réservation en base de données
	 * 
	 * @param oeuvrePret
	 * @throws MonException
	 */
	public void insertReservation(Reservation reservation)throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "INSERT INTO reservation  (id_oeuvrevente, id_adherent, date_reservation, statut) values (" +
					"'" + reservation.getOeuvrevente().getIdOeuvre() +
					"','" + reservation.getAdherent().getIdAdherent() +
					"','" + new SimpleDateFormat("yyyy-MM-dd").format(reservation.getDate()) +
					"','" + reservation.getStatut() +
					"')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	/**
	 * Modification d'une réservation en base de données
	 * 
	 * @param adherent
	 * @throws MonException
	 */
	public void updateReservation(Reservation reservation, int oldOeuvre, int oldAdherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "UPDATE reservation SET " +
					" date_reservation = '" + new SimpleDateFormat("yyyy-MM-dd").format(reservation.getDate()) + "', " +
					" id_oeuvrevente = '" + reservation.getOeuvrevente().getIdOeuvre() +  "', " +
					" id_adherent = '" + reservation.getAdherent().getIdAdherent() +  "' " +
					" WHERE id_oeuvrevente = " + oldOeuvre +
					" AND id_adherent = " + oldAdherent;
			
			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	/**
	 * Consulter une réservation par Id
	 * Fabrique et renvoie un objet reservation contenant le résultat de la requète
	 * 
	 * @param numero integer
	 */
	public Reservation consulterReservation(int idOeuvreVente, int idAdherent) throws MonException {
		String mysql = "SELECT * FROM reservation WHERE id_oeuvrevente = " + idOeuvreVente + " AND id_adherent = " + idAdherent;
		List<Reservation> mesReservations = consulterListeReservations(mysql);
		if (mesReservations.isEmpty())
			return null;
		else {
			return mesReservations.get(0);
		}
	}
	
	/**
	 * Consulter toutes les reservations
	 * Fabrique et renvoie les objets reservation contenant le résultat de la requète
	 * 
	 * @throws MonException
	 */
	public List<Reservation> consulterListeReservations() throws MonException {
		String mysql = "SELECT * FROM reservation";
		return consulterListeReservations(mysql);
	}
	
	/**
	 * Consulter les reservations par paquet
	 * Fabrique et renvoie les objets reservation contenant le résultat de la requète
	 * 
	 * @throws MonException
	 */
	public List<Reservation> consulterListeReservations(int page, int nombreParPage) throws MonException {
		String mysql = "SELECT * FROM reservation "+
					   "ORDER BY id_oeuvrevente "+
					   "LIMIT "+(page*nombreParPage)+","+nombreParPage;
		return consulterListeReservations(mysql);
	}
	
	/**
	 * Construire les objects Reservation en fonction de la requête passée en paramêtre
	 * 
	 * @param mysql String
	 * @throws MonException
	 */
	private List<Reservation> consulterListeReservations(String mysql) throws MonException {
		List<Object> rs;
		List<Reservation> mesReservations = new ArrayList<Reservation>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				Reservation reservation = new Reservation();
				
				int idOeuvreVente = Integer.parseInt(rs.get(index + 0).toString());
				OeuvreVenteService oService = new OeuvreVenteService();
				Oeuvrevente oeuvre = oService.consulterOeuvrevente(idOeuvreVente);
				
				int idAdherent = Integer.parseInt(rs.get(index + 1).toString());
				AdherentService aService = new AdherentService();
				Adherent adherent = aService.consulterAdherent(idAdherent);
								
				reservation.setAdherent(adherent);
				reservation.setOeuvrevente(oeuvre);
				
				Date date = null;
				try {
					date = new SimpleDateFormat("yyyy-mm-dd").parse(rs.get(index + 2).toString());
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				
				reservation.setDate(date);

				index = index + 4;
				mesReservations.add(reservation);
			}

			return mesReservations;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}
	
	/**
	 * Supprimer une reservation par Id
	 * 
	 * @param numero integer
	 * @throws MonException
	 */
	public boolean deleteReservation(int idOeuvreVente, int idAdherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "DELETE FROM reservation WHERE id_oeuvrevente = " + idOeuvreVente + " AND id_adherent = " + idAdherent;

			unDialogueBd.insertionBD(mysql);
			return true;
		} catch (MonException e) {
			throw e;
		}
	}

	
}
