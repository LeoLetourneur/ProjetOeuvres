package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.*;
import dao.AdherentService;
import dao.OeuvrePretService;
import dao.ProprietaireService;
import meserreurs.*;

/**
 * Servlet implementation class Controleur
 */
@WebServlet("/OeuvrePret")
public class OeuvrePretControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
		
	private static final String LISTE_OEUVREPRET = "listeOeuvrePret";
	private static final String FORM_OEUVREPRET = "formOeuvrePret";
	private static final String AJOUTER_OEUVREPRET = "ajouterOeuvrePret";
	private static final String MODIFIER_OEUVREPRET = "modifierOeuvrePret";
	private static final String SUPPRIMER_OEURVREPRET = "supprimerOeuvrePret";
	private static final String INSERER_OEUVREPRET = "insererOeuvrePret";
	
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OeuvrePretControleur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processusTraiteRequete(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processusTraiteRequete(request, response);
	}

	protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		
		System.out.println(request.getRequestURI());
		
		if (LISTE_OEUVREPRET.equals(actionName)) {
			request.setAttribute("tabTitle", "Liste des oeuvres en pret");
			request.setAttribute("module", LISTE_OEUVREPRET);
			
			try {

				OeuvrePretService opService = new OeuvrePretService();
				request.setAttribute("oeuvres", opService.consulterListeOeuvresPret());

			} catch (MonException e) {
				e.printStackTrace();
			}

			destinationPage = "/"+LISTE_OEUVREPRET+".jsp";
		}
		else if (AJOUTER_OEUVREPRET.equals(actionName)) {
			request.setAttribute("tabTitle", "Nouvelle oeuvre en pret");
			request.setAttribute("module", FORM_OEUVREPRET);
			request.setAttribute("action", "Ajouter");
			destinationPage = "/" + FORM_OEUVREPRET + ".jsp";
		}
		else if (MODIFIER_OEUVREPRET.equals(actionName)) {
			
			try {
				OeuvrePretService unService = new OeuvrePretService();
				Oeuvrepret oeuvreAModifier = unService.consulterOeuvrePret(Integer.parseInt(request.getParameter("idOeuvrePret")));
				request.setAttribute("oeuvrePret", oeuvreAModifier);
			} catch (MonException e) {
				e.printStackTrace();
			}
			request.setAttribute("tabTitle", "Modification OeuvrePret");
			request.setAttribute("module", FORM_OEUVREPRET);
			request.setAttribute("action", "Modifier");
			destinationPage = "/" + FORM_OEUVREPRET + ".jsp";
		}
		else if (INSERER_OEUVREPRET.equals(actionName)) {
			try {
				OeuvrePretService unService = new OeuvrePretService();
				int id = -1;
				
				if(request.getParameter("idOeuvrePret") != null && request.getParameter("idOeuvrePret") != "")
					id = Integer.parseInt(request.getParameter("idOeuvrePret"));
				
				Oeuvrepret oeuvrePret;
				
				if(id > 0) {
					oeuvrePret = unService.consulterOeuvrePret(id);
				} else {
					oeuvrePret = new Oeuvrepret();
				}
				oeuvrePret.setTitreOeuvrepret(request.getParameter("txtTitre"));
				
				ProprietaireService pService = new ProprietaireService();
				Proprietaire proprietaire = pService.consulterProprietaire(Integer.parseInt(request.getParameter("idProprietaire")));
				oeuvrePret.setProprietaire(proprietaire);
				
				if(id > 0) {
					unService.updateOeuvrePret(oeuvrePret);
				} else {
					unService.insertOeuvrePret(oeuvrePret);
				}
			} catch (MonException e) {
				e.printStackTrace();
			}
			request.setAttribute("tabTitle", "Liste des oeuvres prêts");
			request.setAttribute("module", LISTE_OEUVREPRET);
			destinationPage = "/OeuvrePret?action="+LISTE_OEUVREPRET;
		}
		else if (SUPPRIMER_OEURVREPRET.equals(actionName)) {
			try {
				OeuvrePretService unService = new OeuvrePretService();
				int id = Integer.parseInt(request.getParameter("idOeuvrePret"));
				boolean success = unService.deleteOeuvrePret(id);
				
			} catch (MonException e) {
				e.printStackTrace();
			}
			request.setAttribute("tabTitle", "Liste des oeuvres prêts");
			request.setAttribute("module", LISTE_OEUVREPRET);
			destinationPage = "/OeuvrePret?action="+LISTE_OEUVREPRET;
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
