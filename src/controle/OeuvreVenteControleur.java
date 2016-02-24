package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.*;
import dao.OeuvreVenteService;
import dao.ProprietaireService;
import meserreurs.*;

/**
 * Servlet implementation class Controleur
 */
@WebServlet("/OeuvreVente")
public class OeuvreVenteControleur extends parentControleur {
	private static final long serialVersionUID = 1L;
	
	private static final String LISTE_OEUVREVENTE = "listeOeuvreVente";
	private static final String FORM_OEUVREVENTE = "formOeuvreVente";

	public OeuvreVenteControleur() {
		super();
	}
	
	protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		
		if (LISTE.equals(actionName)) {
			
			super.processusTraiteRequete(request, response);
			
			request.setAttribute("tabTitle", "Liste des oeuvres en vente");
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
				Oeuvrevente oeuvreAModifier = service.consulterOeuvrevente(Integer.parseInt(request.getParameter("idOeuvre")));
				request.setAttribute("oeuvre", oeuvreAModifier);
			} catch (MonException e) {
				e.printStackTrace();
			}
			request.setAttribute("tabTitle", "Modification adhérent");
			//request.setAttribute("module", FORM_OEUVREVENTE);
			request.setAttribute("action", "Modifier");
			destinationPage = "/"+FORM_OEUVREVENTE+".jsp";
		} 
		else if (INSERER.equals(actionName)) {
			
			try {
				OeuvreVenteService service = new OeuvreVenteService();
				
				int id = -1;
				if(request.getParameter("idOeuvre") != null && request.getParameter("idOeuvre") != "") {
					id = Integer.parseInt(request.getParameter("idOeuvre"));
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
				oeuvre.setTitreOeuvre(request.getParameter("txtTitre"));
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
				int id = Integer.parseInt(request.getParameter("idOeuvre"));
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
