package controle;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdherentService;
import dao.OeuvreVenteService;
import dao.ReservationService;
import meserreurs.MonException;
import metier.Adherent;
import metier.Oeuvrevente;
import metier.Reservation;

@WebServlet("/Reservation")
public class ReservationControleur extends parentControleur {
	private static final long serialVersionUID = 1L;

	private static final String LISTE_RESERVATION = "listeReservation";
	private static final String FORM_RESERVATION = "formReservation";
	
	public ReservationControleur(){
		super();
	}
	
	protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		
		if (LISTE.equals(actionName)) {
			
			super.processusTraiteRequete(request, response);
			
			request.setAttribute("tabTitle", "Liste des réservations");
			
			try {
				ReservationService service = new ReservationService();
				List<Reservation> listeTotal = service.consulterListeReservations();
				float nombreReservation = Float.parseFloat(listeTotal.size()+"");
				int nombrePage = (int) Math.ceil(nombreReservation/nombreParPage);
				request.setAttribute("nbPage", nombrePage);
				
				verifierPage(request, nombrePage);
				
				List<Reservation> liste = service.consulterListeReservations((int)page-1,(int)nombreParPage);
				request.setAttribute("reservations", liste);

			} catch (MonException e) {
				e.printStackTrace();
			}

			destinationPage = "/"+LISTE_RESERVATION+".jsp";
		}
		else if (AJOUTER.equals(actionName)) {
			
			try {
				AdherentService aService = new AdherentService();
				OeuvreVenteService oService = new OeuvreVenteService();
				
				List<Adherent> adherents;
				adherents = aService.consulterListeAdherents();
				request.setAttribute("adherents", adherents);
				
				if(request.getParameter("idOeuvre") != null && request.getParameter("idOeuvre") != "") {
					Oeuvrevente oeuvre = oService.consulterOeuvrevente(Integer.parseInt(request.getParameter("idOeuvre").toString()));
					request.setAttribute("oeuvre", oeuvre);
				} 
				else {
					List<Oeuvrevente> oeuvres;
					oeuvres = oService.consulterListeOeuvresVentes();
					request.setAttribute("oeuvres", oeuvres);
				}
				
			} catch (MonException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("tabTitle", "Nouvelle réservation");
			request.setAttribute("module", FORM_RESERVATION);
			request.setAttribute("action", "Ajouter");
			request.setAttribute("vue", FORM);
            //request.setAttribute("vueRetour", request.getParameter("vueRetour").toString());
			destinationPage = "/"+FORM_RESERVATION+".jsp";
		}
		else if (MODIFIER.equals(actionName)) {
			
			try {
				AdherentService aService = new AdherentService();
				List<Adherent> adherents;
				adherents = aService.consulterListeAdherents();
				request.setAttribute("adherents", adherents);
				
				OeuvreVenteService oService = new OeuvreVenteService();
				List<Oeuvrevente> oeuvres;
				oeuvres = oService.consulterListeOeuvresVentes();
				request.setAttribute("oeuvres", oeuvres);
				
				Oeuvrevente oeuvre = oService.consulterOeuvrevente(Integer.parseInt(request.getParameter("idOeuvre").toString()));
				request.setAttribute("oeuvre", oeuvre);
			
				ReservationService service = new ReservationService();
				Reservation reservationAModifier = service.consulterReservation(
						Integer.parseInt(request.getParameter("idOeuvre")), 
						Integer.parseInt(request.getParameter("idAdherent")));
				
				request.setAttribute("reservation", reservationAModifier);
			} catch (MonException e) {
				e.printStackTrace();
			}
			request.setAttribute("tabTitle", "Modification reservation");
			request.setAttribute("module", FORM_RESERVATION);
			request.setAttribute("action", "Modifier");
			request.setAttribute("vue", FORM);
			destinationPage = "/"+FORM_RESERVATION+".jsp";
		} 
		else if (INSERER.equals(actionName)) {
			
			try {
				ReservationService service = new ReservationService();
				boolean ajout = false;
				Reservation reservation = null;
				
				if(request.getParameter("oldOeuvre") != "" 
				&& request.getParameter("oldAdherent") != "") {
					reservation= service.consulterReservation(
						Integer.parseInt(request.getParameter("oldOeuvre").toString()),
						Integer.parseInt(request.getParameter("oldAdherent").toString()));
				}
				
				if(reservation == null) {
					ajout = true;
					reservation = new Reservation();
				}
				
				OeuvreVenteService oService = new OeuvreVenteService();
				Oeuvrevente oeuvre = oService.consulterOeuvrevente(Integer.parseInt(request.getParameter("idOeuvre").toString()));
				
				AdherentService aService = new AdherentService();
				Adherent adherent = aService.consulterAdherent(Integer.parseInt(request.getParameter("idAdherent").toString()));
				
				Date date = null;
				try {
					date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("txtDate"));
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				
				reservation.setAdherent(adherent);
				reservation.setOeuvrevente(oeuvre);
				reservation.setDate(date);
				reservation.setStatut("confirmee");
				
				if(ajout) {
					service.insertReservation(reservation);
				} else {
					int oldOeuvre = Integer.parseInt(request.getParameter("oldOeuvre").toString());
					int oldAdherent = Integer.parseInt(request.getParameter("oldAdherent").toString());
					service.updateReservation(reservation, oldOeuvre, oldAdherent);
				}

			} catch (MonException e) {
				e.printStackTrace();
			}
			
			destinationPage = "/Reservation?action="+LISTE;
		}

		else if (SUPPRIMER.equals(actionName)) {
			
			try {
				ReservationService service = new ReservationService();
				int idOeuvre = Integer.parseInt(request.getParameter("idSelected"));
				int idAdherent = Integer.parseInt(request.getParameter("idSelected2"));
				service.deleteReservation(idOeuvre, idAdherent);

			} catch (MonException e) {
				e.printStackTrace();
			}
			
			destinationPage = "/Reservation?action="+LISTE;
		}
		else {
			String messageErreur = "Erreur 404 - [" + actionName + "] Ressource introuvable !";
			request.setAttribute(ERROR_KEY, messageErreur);
			request.setAttribute("tabTitle", "Erreur 404");
			request.setAttribute("module", "erreur");
		}
		
		// Redirection vers la page jsp appropriee
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
		dispatcher.forward(request, response);

	}
}
