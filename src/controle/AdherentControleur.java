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
import dao.AdherentService;
import meserreurs.*;

/**
 * Servlet implementation class Controleur
 */
@WebServlet("/Adherent")
public class AdherentControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
	
	private static final String LISTE = "liste";
	private static final String AJOUTER = "ajouter";
	private static final String MODIFIER = "modifier";
	private static final String SUPPRIMER = "supprimer";
	
	private static final String LISTE_ADHERENT = "listeAdherent";
	private static final String FORM_ADHERENT = "formAdherent";
	private static final String INSERER_ADHERENT = "insererAdherent";
	
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdherentControleur() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processusTraiteRequete(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processusTraiteRequete(request, response);
	}

	protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		
		//System.out.println(request.getRequestURI());
		
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
			
			request.setAttribute("tabTitle", "Liste des adhérents");
			request.setAttribute("vue", LISTE);
			//request.setAttribute("module", LISTE_ADHERENT);
			
			try {
				AdherentService service = new AdherentService();
				List<Adherent> listeTotal = service.consulterListeAdherents();
				float nombreAdherent = Float.parseFloat(listeTotal.size()+"");
				int nombrePage = (int) Math.ceil(nombreAdherent/nombreParPage);
				request.setAttribute("nbPage", nombrePage);
				
				List<Adherent> liste = service.consulterListeAdherents((int)page-1,(int)nombreParPage);
				request.setAttribute("adherents", liste);

			} catch (MonException e) {
				e.printStackTrace();
			}

			destinationPage = "/"+LISTE_ADHERENT+".jsp";
		}
		else if (AJOUTER.equals(actionName)) {
			
			request.setAttribute("tabTitle", "Nouvel adhérent");
			request.setAttribute("module", FORM_ADHERENT);
			request.setAttribute("action", "Ajouter");
			destinationPage = "/"+FORM_ADHERENT+".jsp";
		} 
		else if (MODIFIER.equals(actionName)) {
			
			try {
				AdherentService unService = new AdherentService();
				Adherent adherentAModifier = unService.consulterAdherent(Integer.parseInt(request.getParameter("idAdherent")));
				request.setAttribute("adherent", adherentAModifier);
			} catch (MonException e) {
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
				if(request.getParameter("idAdherent") != null 
				&& request.getParameter("idAdherent") != "") {
					id = Integer.parseInt(request.getParameter("idAdherent"));
				}
				
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
				e.printStackTrace();
			}
			
			destinationPage = "/Adherent?action="+LISTE;
		}
		else if (SUPPRIMER.equals(actionName)) {
			
			try {
				AdherentService unService = new AdherentService();
				int id = Integer.parseInt(request.getParameter("idAdherent"));
				unService.deleteAdherent(id);

			} catch (MonException e) {
				e.printStackTrace();
			}
			
			destinationPage = "/Adherent?action="+LISTE;
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
