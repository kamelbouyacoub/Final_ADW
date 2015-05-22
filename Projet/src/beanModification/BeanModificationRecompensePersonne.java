package beanModification;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.DAOGestionnaireJPA;
import dao.DAOModificationRecompenseFilmJPA;
import dao.DAOModificationRecompensePersonneJPA;
import dao.DAOPrixJPA;
import utilisateurs.ClientPro;
import utilisateurs.Gestionnaire;
import beans.BeanFilmGeneral;
import beans.BeanPersonneFilm;
import beansSession.BeanSessionPro;
import film.Film;
import film.ModificationRecompensePersonne;
import film.PersonneFilm;
import film.Prix;

public class BeanModificationRecompensePersonne implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idPrix;
	private int dateRemise;
	private String description;

	public int getIdPrix() {
		return idPrix;
	}

	public void setIdPrix(int idPrix) {
		this.idPrix = idPrix;
	}

	public int getDateRemise() {
		return dateRemise;
	}

	public void setDateRemise(int dateRemise) {
		this.dateRemise = dateRemise;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void ajoutRecompense() {
		ClientPro c = findIdSession();
		Film f = filmGeneral();
		PersonneFilm p = findPersonneFilm();
		Gestionnaire g = DAOGestionnaireJPA.getInstance().get(100);
		ModificationRecompensePersonne mrp = new ModificationRecompensePersonne(
				c.getIdUtilisateur(), g.getIdUtilisateur(), f.getIdFilm(),idPrix, dateRemise,
				description, false, p);
		DAOModificationRecompensePersonneJPA.getInstance().save(mrp);

	}

	public List<Prix> getListPrix() {

		try {
			return DAOPrixJPA.getInstance().getLstPrix();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			BeanFilmGeneral b = (BeanFilmGeneral) session
					.getAttribute("beanFilmGeneral");
			film = b.getFilm();
		} catch (Exception e) {
			e.printStackTrace();
			film = new Film();
		}

		return film;
	}

	public PersonneFilm findPersonneFilm() {

		PersonneFilm personne;
		try {
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			BeanPersonneFilm b = (BeanPersonneFilm) session
					.getAttribute("beanPersonneFilm");
			personne = b.getDetailPersonneFilm().getPersonneFilm();
		} catch (Exception e) {
			e.printStackTrace();
			personne = new PersonneFilm();
		}
		return personne;
	}

}
