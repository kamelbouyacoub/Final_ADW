package beanValidationGesstionnaire;

import java.io.Serializable;
import java.util.List;

import utilisateurs.Gestionnaire;
import dao.DAODetailPersonneFilm;
import dao.DAODetailPersonneFilmJPA;
import dao.DAOModificationPersonneFilmFilm;
import dao.DAOModificationPersonneFilmFilmJPA;
import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.DetailPersonneFilm;
import film.ModificationPersonneFilmFilm;
import film.PersonneFilm;

public class BeanGestionnaireModifRole extends BeanModification implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<ModificationPersonneFilmFilm> getLstModRole() {
		return DAOModificationPersonneFilmFilmJPA.getInstance().loadAll();
	}
	
	
	@Override
	public void valider(Object mod) {
		ModificationPersonneFilmFilm modRole = (ModificationPersonneFilmFilm )mod;
		Gestionnaire g = findIdSession();
		//si le gestionnaire est en session
		if (g.getIdUtilisateur()== -1){
			DAOPersonneFilm daoPersonne = DAOPersonneFilmJPA.getInstance();
			DAODetailPersonneFilm daoDetail = DAODetailPersonneFilmJPA.getInstance(); 
			DAOModificationPersonneFilmFilm dao = DAOModificationPersonneFilmFilmJPA.getInstance();
			PersonneFilm p = daoPersonne.get(modRole.getIdPersonneFilm());
			DetailPersonneFilm d = daoDetail.get(p.getIdPersonne()+""+modRole.getModFilmPersonne().getIdFilm());
			//nouveau details personne film 
			//	
			if(d != null){
				d.setJoue(modRole.isJouer());
				d.setRealise(modRole.isRealiser());
				d.setProduit(modRole.isProduit());
	
	
			}else{
				d = new DetailPersonneFilm(p , modRole.getModFilmPersonne(), modRole.isProduit(), modRole.isJouer(), modRole.isRealiser());
				
			}
	
			try {
				daoDetail.save(d);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//update idGestionaire dans ModificationFilm
			try {
				dao.updateIdGestionnaire(modRole.getIdModification(), g.getIdUtilisateur());
			} catch (Exception e) {			
				e.printStackTrace();
			}	
		}	
		
	}

	@Override
	public void rejeter(Object mod) {
		ModificationPersonneFilmFilm modPersonneFilm = (ModificationPersonneFilmFilm)mod;
		DAOModificationPersonneFilmFilm dao = DAOModificationPersonneFilmFilmJPA.getInstance();
		Gestionnaire g = findIdSession();
		try {
			dao.updateIdGestionnaire(modPersonneFilm.getIdModification(), g.getIdUtilisateur());
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	
	
}
