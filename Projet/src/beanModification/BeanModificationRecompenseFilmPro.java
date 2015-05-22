package beanModification;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import utilisateurs.ClientPro;
import utilisateurs.Gestionnaire;
import beans.BeanFilmGeneral;
import beansSession.BeanSessionPro;
import dao.DAOGestionnaireJPA;
import dao.DAOModificationRecompenseFilmJPA;
import dao.DAOPrixJPA;
import film.Film;
import film.ModificationRecompenseFilm;
import film.Prix;

public class BeanModificationRecompenseFilmPro implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idPrix;
	private int dateRemise;
	private String description;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDateRemise() {
		return dateRemise;
	}

	public void setDateRemise(int dateRemise) {
		this.dateRemise = dateRemise;
	}

	public int getIdPrix() {
		return idPrix;
	}

	public void setIdPrix(int idPrix) {
		this.idPrix = idPrix;
	}

	public List<Prix> getListPrix(){
		
		try {
			return DAOPrixJPA.getInstance().getLstPrix();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	public void ajoutRecompense(){
		Film film = filmGeneral();
		ClientPro c = findIdSession();
		Gestionnaire g = DAOGestionnaireJPA.getInstance().get(100); 
		ModificationRecompenseFilm mr = new ModificationRecompenseFilm( c.getIdUtilisateur(), g.getIdUtilisateur(),  idPrix	, dateRemise, description, false, film);
		DAOModificationRecompenseFilmJPA.getInstance().save(mr);
		
	}
	
	public ClientPro findIdSession() {
		ClientPro utilisateur;
		try {
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			BeanSessionPro b = (BeanSessionPro) session
					.getAttribute("beanSessionPro");
			utilisateur = b.getC();

		} catch (Exception e) {
			e.printStackTrace();
			utilisateur = new ClientPro();
		}
		return utilisateur;

	}
	
	public Film filmGeneral() {
		Film film;
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false);
			BeanFilmGeneral b = (BeanFilmGeneral) session
					.getAttribute("beanFilmGeneral");
			film = b.getFilm();
		} catch (Exception e) {			
			e.printStackTrace();
			film = new Film();
		}
		
		return film;
	}
	

}
