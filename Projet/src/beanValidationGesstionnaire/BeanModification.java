package beanValidationGesstionnaire;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import utilisateurs.Gestionnaire;
import beansSession.BeanSessionGestionnaire;
import dao.DAOPersonneFilmJPA;
import dao.DAOPrixJPA;
import film.PersonneFilm;
import film.Prix;

abstract public class BeanModification {

	abstract public void valider(Object mod);

	abstract public void rejeter(Object mod);

	// pour montrer les nom des prix et personneFilm
	public String getPersonneFilm(int idPersonne) {
		PersonneFilm p = DAOPersonneFilmJPA.getInstance().get(idPersonne);
		return p.getPrenom() + " " + p.getNom();
	}



	// acepter et rejeter modification
	public Gestionnaire findIdSession() {
		Gestionnaire gestionnaire = new Gestionnaire();
		try {
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			BeanSessionGestionnaire g = (BeanSessionGestionnaire) session
					.getAttribute("beanSessionGestionnaire");
			gestionnaire = g.getG();

		} catch (Exception e) {
			e.printStackTrace();
			gestionnaire = new Gestionnaire();
		}
		return gestionnaire;

	}

}
