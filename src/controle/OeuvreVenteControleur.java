package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.*;
import dao.OeuvreVenteService;
import dao.ProprietaireService;
import meserreurs.*;

/**
 * Servlet implementation class Controleur
 */
@WebServlet("/OeuvreVente")
public class OeuvreVenteControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
		
	private static final String LISTE = "liste";
	private static final String AJOUTER = "ajouter";
	private static final String MODIFIER = "modifier";
	private static final String SUPPRIMER = "supprimer";
	
	private static final String LISTE_OEUVREVENTE = "listeOeuvreVente";
	private static final String FORM_OEUVREVENTE = "formOeuvreVente";
	private static final String INSERER_OEUVREVENTE = "insererOeuvreVente";
	
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OeuvreVenteControleur() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processusTraiteRequete(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processusTraiteRequete(request, response);
	}

	protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		
		if (LISTE.equals(actionName)) {
			
			int page = 1;
			int nombreParPage = 5;
			if(request.getParameter("currentPage") != null 
			&& request.getParameter("currentPage") != "") {
				page = Integer.parseInt(request.getParameter("currentPage"));
			}
			if(request.getParameter("currentNumberPerPage") != null 
			&& request.getParameter("currentNumberPerPage") != "") {
				nombreParPage = Integer.parseInt(request.getParameter("currentNumberPerPage"));
			}
			
			request.setAttribute("currentPage", page);
			request.setAttribute("currentNumberPerPage", nombreParPage);
			
			request.setAttribute("tabTitle", "Liste des oeuvres en vente");
			request.setAttribute("vue", LISTE);
			//request.setAttribute("module", LISTE_OEUVREVENTE);
			
			try {
				OeuvreVenteService service = new OeuvreVenteService();
				List<Oeuvrevente> listeTotal = service.consulterListeOeuvresVentes();
				float nombreOeuvre = Float.parseFloat(listeTotal.size()+"");
				int nombrePage = (int) Math.ceil(nombreOeuvre/nombreParPage);
				request.setAttribute("nbPage", nombrePage);
				
				List<Oeuvrevente> liste = service.consulterListeOeuvresVentes((int)page-1,(int)nombreParPage);
				request.setAttribute("oeuvres", liste);

			} catch (MonException e) {
				e.printStackTrace();
			}

			destinationPage = "/"+LISTE_OEUVREVENTE+".jsp";
		}
		else if (AJOUTER.equals(actionName)) {
			
			try {
				ProprietaireService service = new ProprietaireService();
				List<Proprietaire> liste;
				liste = service.consulterListeProprietaires();
				request.setAttribute("proprietaires", liste);
			} catch (MonException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("tabTitle", "Nouvel oeuvre à vendre");
			//request.setAttribute("module", FORM_OEUVREVENTE);
			request.setAttribute("action", "Ajouter");
			destinationPage = "/"+FORM_OEUVREVENTE+".jsp";
		}
		else if (MODIFIER.equals(actionName)) {
			
			try {
				ProprietaireService service = new ProprietaireService();
				List<Proprietaire> liste;
				liste = service.consulterListeProprietaires();
				request.setAttribute("proprietaires", liste);
			} catch (MonException e) {
				e.printStackTrace();
			}
			
			try {
				OeuvreVenteService service = new OeuvreVenteService();
				Oeuvrevente oeuvreAModifier = service.consulterOeuvrevente(Integer.parseInt(request.getParameter("idOeuvrevente")));
				request.setAttribute("oeuvre", oeuvreAModifier);
			} catch (MonException e) {
				e.printStackTrace();
			}
			request.setAttribute("tabTitle", "Modification adhérent");
			//request.setAttribute("module", FORM_OEUVREVENTE);
			request.setAttribute("action", "Modifier");
			destinationPage = "/"+FORM_OEUVREVENTE+".jsp";
		} 
		else if (INSERER_OEUVREVENTE.equals(actionName)) {
			
			try {
				OeuvreVenteService service = new OeuvreVenteService();
				
				int id = -1;
				if(request.getParameter("idOeuvrevente") != null && request.getParameter("idOeuvrevente") != "") {
					id = Integer.parseInt(request.getParameter("idOeuvrevente"));
				}
				
				Oeuvrevente oeuvre;
				if(id > 0) {
					oeuvre = service.consulterOeuvrevente(id);
				} else {
					oeuvre = new Oeuvrevente();
				}
				
				ProprietaireService serviceP = new ProprietaireService();
				Proprietaire proprietaire = serviceP.consulterProprietaire(Integer.parseInt(request.getParameter("txtProprietaire")));
				
				oeuvre.setEtatOeuvrevente("L");
				oeuvre.setTitreOeuvrevente(request.getParameter("txtTitre"));
				oeuvre.setPrixOeuvrevente(Float.parseFloat(request.getParameter("txtPrix")));
				oeuvre.setProprietaire(proprietaire);
				
				if(id > 0) {
					service.updateOeuvreVente(oeuvre);
				} else {
					service.insertOeuvreVente(oeuvre);
				}

			} catch (MonException e) {
				e.printStackTrace();
			}
			
			destinationPage = "/OeuvreVente?action="+LISTE;
		}
		else if (SUPPRIMER.equals(actionName)) {
			
			try {
				OeuvreVenteService service = new OeuvreVenteService();
				int id = Integer.parseInt(request.getParameter("idOeuvrevente"));
				service.deleteOeuvreVente(id);

			} catch (MonException e) {
				e.printStackTrace();
			}
			
			destinationPage = "/OeuvreVente?action="+LISTE;
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
