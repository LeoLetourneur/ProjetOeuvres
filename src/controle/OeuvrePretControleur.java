package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.*;
import dao.OeuvrePretService;
import dao.ProprietaireService;
import meserreurs.*;

/**
 * Servlet implementation class Controleur
 */
@WebServlet("/OeuvrePret")
public class OeuvrePretControleur extends parentControleur {
	private static final long serialVersionUID = 1L;
		
	private static final String LISTE_OEUVREPRET = "listeOeuvrePret";
	private static final String FORM_OEUVREPRET = "formOeuvrePret";

	public OeuvrePretControleur() {
		super();
	}

	protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		
		if (LISTE.equals(actionName)) {
			
			super.processusTraiteRequete(request, response);
			
			request.setAttribute("tabTitle", "Liste des prêts");
			//request.setAttribute("module", LISTE_OEUVREPRET);
			
			try {
				OeuvrePretService service = new OeuvrePretService();
				List<Oeuvrepret> listeTotal = service.consulterListeOeuvresPret();
				float nombreOeuvre = Float.parseFloat(listeTotal.size()+"");
				int nombrePage = (int) Math.ceil(nombreOeuvre/nombreParPage);
				request.setAttribute("nbPage", nombrePage);
				
				List<Oeuvrepret> liste = service.consulterListeOeuvresPret((int)page-1,(int)nombreParPage);
				request.setAttribute("oeuvres", liste);
				
			} catch (MonException e) {
				e.printStackTrace();
			}

			destinationPage = "/"+LISTE_OEUVREPRET+".jsp";
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
			
			request.setAttribute("tabTitle", "Nouvelle oeuvre en pret");
			request.setAttribute("module", FORM_OEUVREPRET);
			request.setAttribute("action", "Ajouter");
			request.setAttribute("vue", FORM);
			destinationPage = "/" + FORM_OEUVREPRET + ".jsp";
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
				OeuvrePretService service = new OeuvrePretService();
				Oeuvrepret oeuvreAModifier = service.consulterOeuvrePret(Integer.parseInt(request.getParameter("idOeuvre")));
				request.setAttribute("oeuvrePret", oeuvreAModifier);
			} catch (MonException e) {
				e.printStackTrace();
			}
			request.setAttribute("tabTitle", "Modification OeuvrePret");
			request.setAttribute("module", FORM_OEUVREPRET);
			request.setAttribute("action", "Modifier");
			request.setAttribute("vue", FORM);
			destinationPage = "/" + FORM_OEUVREPRET + ".jsp";
		}
		else if (INSERER.equals(actionName)) {
			try {
				OeuvrePretService service = new OeuvrePretService();
				
				int id = -1;
				if(request.getParameter("idOeuvre") != null && request.getParameter("idOeuvre") != "") {
					id = Integer.parseInt(request.getParameter("idOeuvre"));
				}
				
				Oeuvrepret oeuvrePret;
				if(id > 0) {
					oeuvrePret = service.consulterOeuvrePret(id);
				} else {
					oeuvrePret = new Oeuvrepret();
				}
				oeuvrePret.setTitreOeuvre(request.getParameter("txtTitre"));
				
				ProprietaireService pService = new ProprietaireService();
				Proprietaire proprietaire = pService.consulterProprietaire(Integer.parseInt(request.getParameter("txtProprietaire")));
				oeuvrePret.setProprietaire(proprietaire);
				
				if(id > 0) {
					service.updateOeuvrePret(oeuvrePret);
				} else {
					service.insertOeuvrePret(oeuvrePret);
				}
			} catch (MonException e) {
				e.printStackTrace();
			}
			
			destinationPage = "/OeuvrePret?action="+LISTE;
		}
		else if (SUPPRIMER.equals(actionName)) {
			try {
				OeuvrePretService service = new OeuvrePretService();
				int id = Integer.parseInt(request.getParameter("idSelected"));
				service.deleteOeuvrePret(id);
				
			} catch (MonException e) {
				e.printStackTrace();
			}
			
			destinationPage = "/OeuvrePret?action="+LISTE;
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
