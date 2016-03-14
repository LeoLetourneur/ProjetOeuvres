package controle;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import metier.*;
import dao.AdherentService;
import meserreurs.*;

/**
 * Classe Servlet pour les adhérents
 * 
 * @author GERLAND - LETOURNEUR
 */
@WebServlet("/Adherent")
public class AdherentControleur extends parentControleur {
	private static final long serialVersionUID = 1L;
	
	private static final String LISTE_ADHERENT = "listeAdherent";
	private static final String FORM_ADHERENT = "formAdherent";

	public AdherentControleur() {
		super();
	}

	/**
	 * Affichage de la liste d'adhérents
	 * 
	 * @param HttpServletRequest request
	 * @return String Page à afficher
	 * @see controle.parentControleur#displayListe(javax.servlet.http.HttpServletRequest)
	 */
	protected String displayListe(HttpServletRequest request) {
		
		request.setAttribute("tabTitle", "Liste des adhérents");
		request.setAttribute("module", LISTE_ADHERENT);
		
		try {
			AdherentService service = new AdherentService();
			List<Adherent> listeTotal = service.consulterListeAdherents();
			float nombreAdherent = Float.parseFloat(listeTotal.size()+"");
			int nombrePage = (int) Math.ceil(nombreAdherent/nombreParPage);
			request.setAttribute("nbPage", nombrePage);

			verifierPage(request, nombrePage);
			
			List<Adherent> liste = service.consulterListeAdherents((int)page-1,(int)nombreParPage);
			request.setAttribute("adherents", liste);

		} catch (MonException e) {
			e.printStackTrace();
		}

		return "/"+LISTE_ADHERENT+".jsp";
	}
	
	/**
	 * Affichage du formulaire d'ajout
	 * 
	 * @param HttpServletRequest request
	 * @return String Page à afficher
	 * @see controle.parentControleur#displayListe(javax.servlet.http.HttpServletRequest)
	 */
	protected String displayAddForm(HttpServletRequest request) {
			
		request.setAttribute("tabTitle", "Nouvel adhérent");
		request.setAttribute("module", FORM_ADHERENT);
		return "/"+FORM_ADHERENT+".jsp";
	} 

	/**
	 * Affichage du formulaire de modification
	 * 
	 * @param HttpServletRequest request
	 * @return String Page à afficher
	 * @see controle.parentControleur#displayListe(javax.servlet.http.HttpServletRequest)
	 */
	protected String displayUpdateForm(HttpServletRequest request) {
		
		try {
			AdherentService unService = new AdherentService();
			Adherent adherentAModifier = unService.consulterAdherent(Integer.parseInt(request.getParameter("idAdherent")));
			request.setAttribute("adherent", adherentAModifier);
		} catch (MonException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("tabTitle", "Modification adhérent");
		request.setAttribute("module", FORM_ADHERENT);
		
		return "/"+FORM_ADHERENT+".jsp";
	}
	
	/**
	 * Ajout ou modification d'un adhérent
	 * 
	 * @param HttpServletRequest request
	 * @return String Page à afficher
	 * @see controle.parentControleur#displayListe(javax.servlet.http.HttpServletRequest)
	 */
	protected String insertNewObject(HttpServletRequest request) {
		
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
			adherent.setNomAdherent(request.getParameter("txtnom").replace("'", "\\\'"));
			adherent.setPrenomAdherent(request.getParameter("txtprenom").replace("'", "\\\'"));
			adherent.setVilleAdherent(request.getParameter("txtville").replace("'", "\\\'"));
			
			if(id > 0) {
				unService.updateAdherent(adherent);
			} else {
				unService.insertAdherent(adherent);
			}

		} catch (MonException e) {
			e.printStackTrace();
		}
		
		return "/Adherent?action="+LISTE;
	}
	
	/**
	 * Suppression d'un adhérent
	 * 
	 * @param HttpServletRequest request
	 * @return String Page à afficher
	 * @see controle.parentControleur#displayListe(javax.servlet.http.HttpServletRequest)
	 */
	protected String deleteObject(HttpServletRequest request) {

		try {
			AdherentService unService = new AdherentService();
			int id = Integer.parseInt(request.getParameter("idSelected"));
			unService.deleteAdherent(id);

		} catch (MonException e) {
			e.printStackTrace();
		}
		
		return "/Adherent?action="+LISTE;
	}
}
