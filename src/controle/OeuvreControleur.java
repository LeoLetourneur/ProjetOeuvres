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
@WebServlet("/Oeuvre")
public class OeuvreControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
		
	private static final String LISTE_OEUVRE = "listeOeuvre";
	private static final String FORM_OEUVRE = "formOeuvre";
	
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OeuvreControleur() {
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
		System.out.println(request.getContextPath());
		if (LISTE_OEUVRE.equals(actionName)) {
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
