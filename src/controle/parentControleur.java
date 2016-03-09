package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class parentControleur extends HttpServlet {
	protected static final long serialVersionUID = 1L;
	protected static final String ACTION_TYPE = "action";
	
	protected static final String FORM = "form";
	protected static final String LISTE = "liste";
	protected static final String INSERER = "inserer";
	protected static final String AJOUTER = "ajouter";
	protected static final String MODIFIER = "modifier";
	protected static final String SUPPRIMER = "supprimer";
	
	protected static final String ERROR_KEY = "messageErreur";
	protected static final String ERROR_PAGE = "/erreur.jsp";
	
	protected int page;
	protected int nombreParPage;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public parentControleur() {
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
			
			page = 1;
			nombreParPage = 5;
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
			request.setAttribute("vue", LISTE);
			
			destinationPage = this.displayListe(request);
		}
		else if (AJOUTER.equals(actionName)) {
			
			request.setAttribute("vue", FORM);
			request.setAttribute("action", "Ajouter");
			destinationPage = this.displayAddForm(request);
		}
		else if (MODIFIER.equals(actionName)) {
			request.setAttribute("vue", FORM);
			request.setAttribute("action", "Modifier");
			destinationPage = this.displayUpdateForm(request);
		}
		else if (INSERER.equals(actionName)) {
			
			destinationPage = this.insertNewObject(request);
		}
		else if (SUPPRIMER.equals(actionName)) {
			
			destinationPage = this.deleteObject(request);
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
	
	protected abstract String displayListe(HttpServletRequest request);
	protected abstract String displayAddForm(HttpServletRequest request);
	protected abstract String displayUpdateForm(HttpServletRequest request);
	protected abstract String insertNewObject(HttpServletRequest request);
	protected abstract String deleteObject(HttpServletRequest request);
	
	/*
	 * Dans le cas où l'utilisateur affiche plus d'élément par page
	 * il faut vérifier que le numéro de la page actuel est toujours disponible
	 * sinon on retourne à la page 1
	 */
	protected void verifierPage(HttpServletRequest request, int nombrePage) {
		if((int)request.getAttribute("currentPage") > nombrePage) {
			page = 1;
			request.setAttribute("currentPage", page);
		}
	}
}
