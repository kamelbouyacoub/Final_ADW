package beanValidationGesstionnaire;

import java.io.Serializable;
import java.util.List;

import utilisateurs.Gestionnaire;
import dao.DAOFilm;
import dao.DAOFilmJPA;
import dao.DAOModificationFilm;
import dao.DAOModificationFilmJPA;
import dao.DAOModificationPersonneFilmFilmJPA;
import dao.DAOModificationRecompensePersonne;
import dao.DAOModificationRecompensePersonneJPA;
import dao.DAOPrixJPA;
import dao.DAORecompensePersonneFilm;
import dao.DAORecompensePersonneFilmJPA;
import film.Film;
import film.ModificationFilm;
import film.ModificationRecompensePersonne;
import film.Prix;
import film.RecompensePersonneFilm;

public class BeanGestionnaireRecompensePersonne extends BeanModification implements Serializable {

	private static final long serialVersionUID = 1L;


	public List<ModificationRecompensePersonne> getListModRecompensePersonne() {
		return DAOModificationRecompensePersonneJPA.getInstance().loadAll();
	}


	public String getNomPrix(int idPrix) {

		return getPrix(idPrix).getNomPrix();
	}

	public Prix getPrix(int idPrix) {
		return DAOPrixJPA.getInstance().get(idPrix);
	}

	@Override
	public void valider(Object mod) {
		ModificationRecompensePersonne modRecompensePersonne = (ModificationRecompensePersonne)mod;
		Gestionnaire g = findIdSession();
		//si le gestionnaire est en session
		if (g.getIdUtilisateur()== -1){
			DAOFilm daoFilm = DAOFilmJPA.getInstance();
			DAORecompensePersonneFilm daoRecompense = DAORecompensePersonneFilmJPA.getInstance();
			
			Film f =daoFilm.get(modRecompensePersonne.getIdFilm());
			Prix p = getPrix(modRecompensePersonne.getIdPrix());
			
			RecompensePersonneFilm r = new RecompensePersonneFilm
					(modRecompensePersonne.getDateRemise(), 
							p, f, modRecompensePersonne.getPersonneFilm(), modRecompensePersonne.getDescription());	
			
			daoRecompense.save(r);
			DAOModificationRecompensePersonne dao = DAOModificationRecompensePersonneJPA.getInstance();
			//update idGestionaire dans ModificationFilm
			try {
				dao.updateIdGestionnaire(modRecompensePersonne.getIdModification(), g.getIdUtilisateur());
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}	
	}


	@Override
	public void rejeter(Object mod) {
		ModificationRecompensePersonne modRecompensePersonne = (ModificationRecompensePersonne)mod;
 		DAOModificationRecompensePersonne dao = DAOModificationRecompensePersonneJPA.getInstance();
		Gestionnaire g = findIdSession();
		//update idGestionaire dans ModificationFilm
		try {
			dao.updateIdGestionnaire(modRecompensePersonne.getIdModification(), g.getIdUtilisateur());
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	
	
}
