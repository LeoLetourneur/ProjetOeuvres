package controle;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

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

	protected String displayListe(HttpServletRequest request) {
			
		request.setAttribute("tabTitle", "Liste des prêts");
		//request.setAttribute("module", LISTE_OEUVREPRET);
		
		try {
			OeuvrePretService service = new OeuvrePretService();
			List<Oeuvrepret> listeTotal = service.consulterListeOeuvresPret();
			float nombreOeuvre = Float.parseFloat(listeTotal.size()+"");
			int nombrePage = (int) Math.ceil(nombreOeuvre/nombreParPage);
			request.setAttribute("nbPage", nombrePage);
			
			verifierPage(request, nombrePage);
			
			List<Oeuvrepret> liste = service.consulterListeOeuvresPret((int)page-1,(int)nombreParPage);
			request.setAttribute("oeuvres", liste);
			
		} catch (MonException e) {
			e.printStackTrace();
		}
	
		return "/"+LISTE_OEUVREPRET+".jsp";
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
		
		request.setAttribute("tabTitle", "Nouvelle oeuvre en pret");
		request.setAttribute("module", FORM_OEUVREPRET);
		return "/" + FORM_OEUVREPRET + ".jsp";
	} 

	protected String displayUpdateForm(HttpServletRequest request) {
		
		try {
			ProprietaireService service = new ProprietaireService();
			List<Proprietaire> liste;
			liste = service.consulterListeProprietaires();
			request.setAttribute("proprietaires", liste);
		
			OeuvrePretService serviceO = new OeuvrePretService();
			Oeuvrepret oeuvreAModifier = serviceO.consulterOeuvrePret(Integer.parseInt(request.getParameter("idOeuvre")));
			request.setAttribute("oeuvrePret", oeuvreAModifier);
		} catch (MonException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("tabTitle", "Modification OeuvrePret");
		request.setAttribute("module", FORM_OEUVREPRET);
		return "/" + FORM_OEUVREPRET + ".jsp";
	}
	
	protected String insertNewObject(HttpServletRequest request) {
		
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
			oeuvrePret.setTitreOeuvre(request.getParameter("txtTitre").replace("'", "\\\'"));
			
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
		
		return "/OeuvrePret?action="+LISTE;
	}
	
	protected String deleteObject(HttpServletRequest request) {

		try {
			OeuvrePretService service = new OeuvrePretService();
			int id = Integer.parseInt(request.getParameter("idSelected"));
			service.deleteOeuvrePret(id);
			
		} catch (MonException e) {
			e.printStackTrace();
		}
		
		return "/OeuvrePret?action="+LISTE;
	}
}
