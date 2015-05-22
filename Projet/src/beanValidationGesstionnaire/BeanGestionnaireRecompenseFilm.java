package beanValidationGesstionnaire;

import java.io.Serializable;
import java.util.List;

import utilisateurs.Gestionnaire;
import dao.DAOModificationRecompenseFilm;
import dao.DAOModificationRecompenseFilmJPA;
import dao.DAOPrixJPA;
import dao.DAORecompenseFilm;
import dao.DAORecompenseFilmJPA;
import film.ModificationRecompenseFilm;
import film.Prix;
import film.RecompenseFilm;

public class BeanGestionnaireRecompenseFilm extends BeanModification implements
		Serializable {

	private static final long serialVersionUID = 1L;

	public List<ModificationRecompenseFilm> getListModRecompenseFilm() {
		return DAOModificationRecompenseFilmJPA.getInstance().loadAll();
	}

	public String getNomPrix(int idPrix) {

		return getPrix(idPrix).getNomPrix();
	}

	public Prix getPrix(int idPrix) {
		return DAOPrixJPA.getInstance().get(idPrix);
	}

	@Override
	public void valider(Object mod) {
		ModificationRecompenseFilm modRecompenseFilm = (ModificationRecompenseFilm) mod;
		Gestionnaire g = findIdSession();
		DAOModificationRecompenseFilm dao = DAOModificationRecompenseFilmJPA
				.getInstance();
		// si le gestionnaire est en session
		if (g.getIdUtilisateur() == -1) {
			Prix p = getPrix(modRecompenseFilm.getIdPrix());
			DAORecompenseFilm daoFilm = DAORecompenseFilmJPA.getInstance();
			RecompenseFilm rf = new RecompenseFilm(
					modRecompenseFilm.getDateRemise(), p,
					modRecompenseFilm.getModRecFilm(), modRecompenseFilm.getDescription());
			daoFilm.save(rf);

			// update idGestionaire dans ModificationFilm
			try {
				dao.updateIdGestionnaire(
						modRecompenseFilm.getIdModifRecompenseFilm(),
						g.getIdUtilisateur());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void rejeter(Object mod) {
		ModificationRecompenseFilm modRecompenseFilm = (ModificationRecompenseFilm) mod;
		DAOModificationRecompenseFilm dao = DAOModificationRecompenseFilmJPA
				.getInstance();
		Gestionnaire g = findIdSession();
		try {
			dao.updateIdGestionnaire(
					modRecompenseFilm.getIdModifRecompenseFilm(),
					g.getIdUtilisateur());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
