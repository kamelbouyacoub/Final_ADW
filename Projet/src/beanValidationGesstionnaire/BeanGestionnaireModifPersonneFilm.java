package beanValidationGesstionnaire;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import beansSession.BeanSessionGestionnaire;
import utilisateurs.Gestionnaire;
import dao.DAOModificationFilm;
import dao.DAOModificationFilmJPA;
import dao.DAOModificationPersonne;
import dao.DAOModificationPersonneJPA;
import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.ModificationPersonne;
import film.PersonneFilm;

public class BeanGestionnaireModifPersonneFilm  extends BeanModification implements Serializable {

	private static final long serialVersionUID = 1L;

	private ModificationPersonne modPersonne;

	public List<ModificationPersonne> getLstModPersonne() {
		return DAOModificationPersonneJPA.getInstance().loadAll();
	}

	public ModificationPersonne getModPersonne() {
		return modPersonne;
	}

	public void setModPersonne(ModificationPersonne modPersonne) {
		this.modPersonne = modPersonne;
	}


	@Override
	public void valider(Object mod) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("fonction accepterModificationPersonneFilm");
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		Gestionnaire g = findIdSession();
		ModificationPersonne modPersonne = (ModificationPersonne)mod;
		// si le gestionnaire est en session
		if (g.getIdUtilisateur() == -1) {
			DAOModificationPersonne dao = DAOModificationPersonneJPA
					.getInstance();
			PersonneFilm p = new PersonneFilm();
			DAOPersonneFilm daoPersonne = DAOPersonneFilmJPA.getInstance();

			// nouveau film save
			if (modPersonne.getPersonneFilm().getIdPersonne() == 100) {
				p = new PersonneFilm(modPersonne.getNomMod(),
						modPersonne.getPrenomMod(),
						modPersonne.getDateNaissanceMod(),
						modPersonne.getPhotoMod(),
						modPersonne.getPopulariteMod(), true);
			} else {// update film
				try {
					p = daoPersonne.get(modPersonne.getPersonneFilm()
							.getIdPersonne());
					p.setNom(modPersonne.getNomMod());
					p.setPrenom(modPersonne.getPrenomMod());
					p.setDateNaissance(modPersonne.getDateNaissanceMod());
					p.setPopularite(modPersonne.getPopulariteMod());
					daoPersonne.save(p);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// update idGestionaire dans ModificationFilm
			try {
				dao.updateIdGestionnaire(modPersonne.getIdModification(), g
						.getIdUtilisateur(), modPersonne.getPersonneFilm()
						.getIdPersonne());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void rejeter(Object mod) {
		ModificationPersonne modPersonne = (ModificationPersonne)mod;
		DAOModificationFilm dao = DAOModificationFilmJPA.getInstance();
		Gestionnaire g = findIdSession();
		System.out.println("ID = "
				+ modPersonne.getPersonneFilm().getIdPersonne());
		// update idGestionaire dans ModificationFilm
		try {
			dao.updateIdGestionnaire(modPersonne.getIdModification(), g
					.getIdUtilisateur(), modPersonne.getPersonneFilm()
					.getIdPersonne());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
