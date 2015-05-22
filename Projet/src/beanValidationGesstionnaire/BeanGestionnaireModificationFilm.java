package beanValidationGesstionnaire;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xpath.internal.operations.Mod;

import utilisateurs.Gestionnaire;
import beansSession.BeanSessionGestionnaire;
import dao.DAOFilm;
import dao.DAOFilmJPA;
import dao.DAOModificationFilm;
import dao.DAOModificationFilmJPA;
import dao.DAOModificationPersonne;
import dao.DAOModificationPersonneFilmFilmJPA;
import dao.DAOModificationPersonneJPA;
import dao.DAOModificationRecompenseFilmJPA;
import dao.DAOModificationRecompensePersonneJPA;
import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import dao.DAOPrixJPA;
import film.Film;
import film.ModificationFilm;
import film.ModificationPersonne;
import film.ModificationPersonneFilmFilm;
import film.ModificationRecompenseFilm;
import film.ModificationRecompensePersonne;
import film.PersonneFilm;
	
public class BeanGestionnaireModificationFilm extends BeanModification implements Serializable {

	private static final long serialVersionUID = 1L;	

	// obtenir listes pour les ongles
	public List<ModificationFilm> getLstModFilm() {
		return DAOModificationFilmJPA.getInstance().loadAll();
	}

	



	@Override
	public void valider(Object mod) {
		System.out.println("fonction accepterModificationFilm");
		ModificationFilm modFilm = (ModificationFilm)mod;
		Gestionnaire g = findIdSession();
		//si le gestionnaire est en session
		if (g.getIdUtilisateur()== -1){
			DAOModificationFilm dao = DAOModificationFilmJPA.getInstance();
			Film f = new Film();
			DAOFilm daoFilm = DAOFilmJPA.getInstance();
	
	
			//nouveau film save
			if(modFilm.getFilm().getIdFilm() == 100){
				f = new Film(modFilm.getNomMod(), modFilm.getAnneeSortieMod(), modFilm.getCoutMod(), "sans photo",0, true);
				try {
					daoFilm.save(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{//update film			
				try {
					f = daoFilm.get(modFilm.getFilm().getIdFilm());
					f.setNom(modFilm.getNomMod());
					f.setAnneeSortie(modFilm.getAnneeSortieMod());
					
					f.setCout(modFilm.getCoutMod());
					daoFilm.save(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
			//update idGestionaire dans ModificationFilm
			try {
				dao.updateIdGestionnaire(modFilm.getIdModification(), g.getIdUtilisateur(), f.getIdFilm());
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}	
	}




	@Override
	public void rejeter(Object mod) {
		ModificationFilm modFilm = (ModificationFilm)mod;
		DAOModificationFilm dao = DAOModificationFilmJPA.getInstance();
		Gestionnaire g = findIdSession();
		System.out.println("ID = "+modFilm.getFilm().getIdFilm());
		//update idGestionaire dans ModificationFilm
		try {
			dao.updateIdGestionnaire(modFilm.getIdModification(), g.getIdUtilisateur(), modFilm.getFilm().getIdFilm());
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}



	
	
	
}
