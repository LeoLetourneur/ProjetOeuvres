package dao;

import meserreurs.MonException;
import java.util.*;

import metier.*;
import persistance.*;

public class ReservationService {
	
	public void insertReservation(Reservation reservation)throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "INSERT INTO reservation  (id_oeuvrevente, id_adherent, date_reservation) values (" +
					"'" + reservation.getOeuvrevente().getIdOeuvrevente() +
					"','" + reservation.getAdherent().getIdAdherent() +
					"','" + reservation.getDate() +
					"')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	public void updateReservation(Reservation reservation) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "UPDATE reservation SET " +
					"date_reservation = '" + reservation.getDate() + "' " +
					"WHERE id_oeuvrevente = " + reservation.getOeuvrevente().getIdOeuvrevente() +
					"AND id_adherent = " + reservation.getAdherent().getIdAdherent();

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}
	
	public Reservation consulterReservation(int idOeuvreVente, int idAdherent) throws MonException {
		String mysql = "SELECT * FROM reservation WHERE id_oeuvrevente = " + idOeuvreVente + "AND id_adherent = " + idAdherent;
		List<Reservation> mesReservations = consulterListeReservations(mysql);
		if (mesReservations.isEmpty())
			return null;
		else {
			return mesReservations.get(0);
		}
	}
	
	public List<Reservation> consulterListeReservations() throws MonException {
		String mysql = "SELECT * FROM reservation";
		return consulterListeReservations(mysql);
	}
	
	private List<Reservation> consulterListeReservations(String mysql) throws MonException {
		List<Object> rs;
		List<Reservation> mesReservations = new ArrayList<Reservation>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				Reservation reservation = new Reservation();
				
				int idAdherent = Integer.parseInt(rs.get(index + 0).toString());
				AdherentService aService = new AdherentService();
				Adherent adherent = aService.consulterAdherent(idAdherent);
				
				int idOeuvreVente = Integer.parseInt(rs.get(index + 1).toString());
				OeuvreVenteService oService = new OeuvreVenteService();
				Oeuvrevente oeuvre = oService.consulterOeuvrevente(idOeuvreVente);
				
				reservation.setAdherent(adherent);
				reservation.setOeuvrevente(oeuvre);
				reservation.setDate(new Date(rs.get(index + 3).toString()));

				index = index + 4;
				mesReservations.add(reservation);
			}

			return mesReservations;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}
	
	public boolean deleteReservation(int idOeuvreVente, int idAdherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "DELETE FROM reservation WHERE id_oeuvrevente = " + idOeuvreVente + "AND id_adherent = " + idAdherent;

			unDialogueBd.insertionBD(mysql);
			return true;
		} catch (MonException e) {
			throw e;
		}
	}

	
}