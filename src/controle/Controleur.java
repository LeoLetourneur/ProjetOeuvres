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
import dao.OeuvreVenteService;
import meserreurs.*;

/**
 * Servlet implementation class Controleur
 */
@WebServlet("/Controleur")
public class Controleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
	
	private static final String LISTE_ADHERENT = "listeAdherent";
	private static final String FORM_ADHERENT = "formAdherent";
	private static final String AJOUTER_ADHERENT = "ajouterAdherent";
	private static final String MODIFIER_ADHERENT = "modifierAdherent";
	private static final String SUPPRIMER_ADHERENT = "supprimerAdherent";
	private static final String INSERER_ADHERENT = "insererAdherent";
	
	private static final String LISTE_OEUVRE = "listeOeuvre";
	private static final String FORM_OEUVRE = "formOeuvre";
	
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controleur() {
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
		
		// execute l'action
		if (LISTE_ADHERENT.equals(actionName)) {
			request.setAttribute("tabTitle", "Liste des adhérents");
			request.setAttribute("module", LISTE_ADHERENT);
			
			try {

				AdherentService unService = new AdherentService();
				request.setAttribute("mesAdherents", unService.consulterListeAdherents());

			} catch (MonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			destinationPage = "/"+LISTE_ADHERENT+".jsp";
		}
		else if (AJOUTER_ADHERENT.equals(actionName)) {
			request.setAttribute("tabTitle", "Nouvel adhérent");
			request.setAttribute("module", FORM_ADHERENT);
			request.setAttribute("action", "Ajouter");
			destinationPage = "/"+FORM_ADHERENT+".jsp";
		} 
		else if (MODIFIER_ADHERENT.equals(actionName)) {
			
			try {
				AdherentService unService = new AdherentService();
				Adherent adherentAModifier = unService.consulterAdherent(Integer.parseInt(request.getParameter("idAdherent")));
				request.setAttribute("adherent", adherentAModifier);
			} catch (MonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("tabTitle", "Modification adhérent");
			request.setAttribute("module", FORM_ADHERENT);
			request.setAttribute("action", "Modifier");
			destinationPage = "/"+FORM_ADHERENT+".jsp";
		} 
		else if (INSERER_ADHERENT.equals(actionName)) {
			try {
				AdherentService unService = new AdherentService();
				int id = -1;
				
				if(request.getParameter("idAdherent") != null && request.getParameter("idAdherent") != "")
					id = Integer.parseInt(request.getParameter("idAdherent"));
				
				Adherent adherent;
				
				if(id > 0) {
					adherent = unService.consulterAdherent(id);
				} else {
					adherent = new Adherent();
				}
				adherent.setNomAdherent(request.getParameter("txtnom"));
				adherent.setPrenomAdherent(request.getParameter("txtprenom"));
				adherent.setVilleAdherent(request.getParameter("txtville"));
				
				if(id > 0) {
					unService.updateAdherent(adherent);
				} else {
					unService.insertAdherent(adherent);
				}

			} catch (MonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("tabTitle", "Liste des adhérents");
			request.setAttribute("module", LISTE_ADHERENT);
			destinationPage = "/Controleur?action="+LISTE_ADHERENT;
		}
		else if (SUPPRIMER_ADHERENT.equals(actionName)) {
			try {
				AdherentService unService = new AdherentService();
				int id = Integer.parseInt(request.getParameter("idAdherent"));
				boolean success = unService.deleteAdherent(id);

			} catch (MonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("tabTitle", "Liste des adhérents");
			request.setAttribute("module", LISTE_ADHERENT);
			destinationPage = "/Controleur?action="+LISTE_ADHERENT;
		}
		else if (LISTE_OEUVRE.equals(actionName)) {
			request.setAttribute("tabTitle", "Liste des oeuvres");
			request.setAttribute("module", LISTE_OEUVRE);
			
			try {

				OeuvreVenteService ovService = new OeuvreVenteService();
				request.setAttribute("oeuvres", ovService.consulterListeOeuvresVentes());

			} catch (MonException e) {
				e.printStackTrace();
			}

			destinationPage = "/"+LISTE_OEUVRE+".jsp";
		}
		else {
			String messageErreur = "[" + actionName + "] n'est pas une action valide.";
			request.setAttribute(ERROR_KEY, messageErreur);
		}
		
		// Redirection vers la page jsp appropriee
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
		dispatcher.forward(request, response);

	}
}
