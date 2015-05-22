package dao;

import java.util.List;

import listes.Listes;
import film.ModificationFilm;

public class DAOModificationFilmJPA implements DAOModificationFilm {

	static private DAOModificationFilmJPA instance= null;
	
	private DAOModificationFilmJPA(){}
	
	static public DAOModificationFilm getInstance() {
		if(instance== null)
			instance= new DAOModificationFilmJPA();
		return instance;
	}
	
	
	@Override
	public ModificationFilm get(int code) {
		ModificationFilm modFilm = DAOJPA.getManager().find(ModificationFilm.class, code);
		if (modFilm == null)
			throw new IllegalArgumentException("Modification film n'existe pas!!!");
		return modFilm;		
	}

	@Override
	public void save(ModificationFilm modification) {
//		if(modification.getIdModification()!=-1)
//			throw new IllegalArgumentException("Modification film existant!!!");
				
		DAOJPA.getManager().persist(modification);
		DAOJPA.commit(); /* discutable de commiter ici*/	

	}

	@Override
	public void remove(ModificationFilm modification) {
		if(modification.getIdModification()==-1)
			throw new IllegalArgumentException("Modification film n'existe pas!!!");
		
		DAOJPA.getManager().remove(modification);
		DAOJPA.commit();	
		modification.setIdModification(-1);		
		
		Listes.remove(modification.getClientProFilm().getLstModificationFilm(), modification);
		Listes.remove(modification.getGestionnaireFilm().getLstModificationFilm(), modification.getIdModification());
		Listes.remove(modification.getFilm().getLstModificationFilm(), modification);
	}

	@Override
	public int getNombreModification() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM modification_film").getSingleResult();
		return l.intValue(); 	
	}	
	
	@Override
	public List<ModificationFilm> loadAll() {
		boolean e = false;
		return DAOJPA.getManager().createQuery("select mf from ModificationFilm mf where mf.etat = :e",ModificationFilm.class).setParameter("e", e).getResultList();
	}
	
	@Override
	public void updateIdGestionnaire(int idMod, int idGes, int idFilm){
		System.out.println("\n\n\n idGes:" + idGes + "  idMod:" + idMod);
		
		try {
			DAOJPA.getManager().createNativeQuery("UPDATE modification_film "
					+ "SET id_gestionnaire =" + idGes + " ,id_film = " + idFilm + " ,etat = 1" 
					+ " WHERE id_modification = " + idMod)					
					.executeUpdate();
			DAOJPA.commit();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

}
