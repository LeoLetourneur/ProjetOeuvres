package controle;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

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
	
	protected String displayListe(HttpServletRequest request) {
			
		request.setAttribute("tabTitle", "Liste des ventes");
		//request.setAttribute("module", LISTE_OEUVREVENTE);
		
		try {
			OeuvreVenteService service = new OeuvreVenteService();
			List<Oeuvrevente> listeTotal = service.consulterListeOeuvresVentes();
			float nombreOeuvre = Float.parseFloat(listeTotal.size()+"");
			int nombrePage = (int) Math.ceil(nombreOeuvre/nombreParPage);
			request.setAttribute("nbPage", nombrePage);
			
			verifierPage(request, nombrePage);
			
			List<Oeuvrevente> liste = service.consulterListeOeuvresVentes((int)page-1,(int)nombreParPage);
			request.setAttribute("oeuvres", liste);
	
		} catch (MonException e) {
			e.printStackTrace();
		}
	
		return "/"+LISTE_OEUVREVENTE+".jsp";
	}
	
	protected String displayAddForm(HttpServletRequest request) {
			
		try {
			ProprietaireService service = new ProprietaireService();
			List<Proprietaire> liste;
			liste = service.consulterListeProprietaires();
			request.setAttribute("proprietaires", liste);
		} catch (MonException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("tabTitle", "Nouvel oeuvre à vendre");
		request.setAttribute("module", FORM_OEUVREVENTE);
		return "/"+FORM_OEUVREVENTE+".jsp";
	} 

	protected String displayUpdateForm(HttpServletRequest request) {
			
		try {
			ProprietaireService service = new ProprietaireService();
			List<Proprietaire> liste;
			liste = service.consulterListeProprietaires();
			request.setAttribute("proprietaires", liste);
		
			OeuvreVenteService serviceO = new OeuvreVenteService();
			Oeuvrevente oeuvreAModifier = serviceO.consulterOeuvrevente(Integer.parseInt(request.getParameter("idOeuvre")));
			request.setAttribute("oeuvre", oeuvreAModifier);
		} catch (MonException e) {
			e.printStackTrace();
		}
		request.setAttribute("tabTitle", "Modification oeuvre vente");
		request.setAttribute("module", FORM_OEUVREVENTE);
		return "/"+FORM_OEUVREVENTE+".jsp";
	}
	
	protected String insertNewObject(HttpServletRequest request) {
		
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
		
		return "/OeuvreVente?action="+LISTE;
	}
	
	protected String deleteObject(HttpServletRequest request) {

		try {
			OeuvreVenteService service = new OeuvreVenteService();
			int id = Integer.parseInt(request.getParameter("idSelected"));
			service.deleteOeuvreVente(id);

		} catch (MonException e) {
			e.printStackTrace();
		}
		
		return "/OeuvreVente?action="+LISTE;
	}
}
