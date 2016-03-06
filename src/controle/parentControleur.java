package controle;

import java.io.IOException;

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
		}

	}
}
