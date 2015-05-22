package dao;

import java.util.List;

import listes.Listes;
import film.DetailPersonneFilm;
import film.ModificationPersonneFilmFilm;

public class DAOModificationPersonneFilmFilmJPA implements
		DAOModificationPersonneFilmFilm {
	static private DAOModificationPersonneFilmFilmJPA instance= null;
	
	static public DAOModificationPersonneFilmFilm getInstance() {
		if(instance== null)
			instance= new DAOModificationPersonneFilmFilmJPA();
		return instance;
	}
	
	
	
	public ModificationPersonneFilmFilm get(int code) {
		ModificationPersonneFilmFilm modPersonneFilmFilm = DAOJPA.getManager().find(ModificationPersonneFilmFilm.class, code);
		if (modPersonneFilmFilm == null)
			throw new IllegalArgumentException("Modification Personne Film Film n'existe pas!!!");
		return modPersonneFilmFilm;	
		
	}

	public void save(ModificationPersonneFilmFilm modPersonneFilmFilm) {
		
		DAOJPA.getManager().persist(modPersonneFilmFilm);
		DAOJPA.commit(); /* discutable de commiter ici*/

	}

	public void remove(ModificationPersonneFilmFilm modPersonneFilmFilm) {
		if(modPersonneFilmFilm.getIdModification()==-1)
			throw new IllegalArgumentException("Modification film n'existe pas!!!");
		
		DAOJPA.getManager().remove(modPersonneFilmFilm);
		DAOJPA.commit();	
		modPersonneFilmFilm.setIdModification(-1);		
		
		Listes.remove(modPersonneFilmFilm.getModFilmPersonne().getLstModificationPersonneFilmFilm(), modPersonneFilmFilm);
		
		
	}

	public int getNombreModif() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM modification_personne_film_film").getSingleResult();
		return l.intValue(); 	
	}



	@Override
	public List<ModificationPersonneFilmFilm> loadAll() {
		boolean e = false;
		return DAOJPA.getManager().createQuery("select mpf from ModificationPersonneFilmFilm mpf where mpf.etat= :e",ModificationPersonneFilmFilm.class).setParameter("e", e).getResultList();		
			
	}



	@Override
	public void acepterModification(DetailPersonneFilm detail) {
		DAODetailPersonneFilm dao = DAODetailPersonneFilmJPA.getInstance(); 
		dao.save(detail);		
	}

	@Override
	public void updateIdGestionnaire(int idMod, int idGes){
		System.out.println("\n\n\n idGes:" + idGes + "  idMod:" + idMod);
		
		try {
			DAOJPA.getManager().createNativeQuery("UPDATE modification_personne_film_film "
					+ "SET id_gestionnaire =" + idGes +" ,etat = 1" 
					+ " WHERE id_modification_role = " + idMod)					
					.executeUpdate();
			DAOJPA.commit();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

}
