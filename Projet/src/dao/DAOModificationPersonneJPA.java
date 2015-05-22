package dao;

import java.util.List;

import listes.Listes;
import film.ModificationPersonne;

public class DAOModificationPersonneJPA implements DAOModificationPersonne {
	
static private DAOModificationPersonneJPA instance= null;
	
	private DAOModificationPersonneJPA(){}
	
	static public DAOModificationPersonne getInstance() {
		if(instance== null)
			instance= new DAOModificationPersonneJPA();
		return instance;
	}
	
	@Override
	public ModificationPersonne get(int code) {
		ModificationPersonne modPersonne = DAOJPA.getManager().find(ModificationPersonne.class, code);
		if (modPersonne == null)
			throw new IllegalArgumentException("Modification Personne n'existe pas!!!");
		return modPersonne;		
	}

	@Override
	public void save(ModificationPersonne modification) {
		if(modification.getIdModification()!=-1)
			throw new IllegalArgumentException("Modification personne existant!!!");
				
		DAOJPA.getManager().persist(modification);
		DAOJPA.commit(); /* discutable de commiter ici*/			
	}

	@Override
	public void remove(ModificationPersonne modification) {
		if(modification.getIdModification()==-1)
			throw new IllegalArgumentException("Modification personne ne existe pas!!!");
		
		DAOJPA.getManager().remove(modification);
		DAOJPA.commit();	
		modification.setIdModification(-1);		
		
		Listes.remove(modification.getClientProPersonne().getLstModificationPersonne(), modification);
		Listes.remove(modification.getGestionnairePersonne().getLstModificationPersonne(), modification);
		Listes.remove(modification.getPersonneFilm().getLstModificationPersonne(), modification);
	}

	@Override
	public int getNombreModification() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM modification_personne_film").getSingleResult();
		return l.intValue();
	}
	
	@Override
	public List<ModificationPersonne> loadAll() {
		boolean e = false;
		return DAOJPA.getManager().createQuery("select mp from ModificationPersonne mp where mp.etat = :e ",ModificationPersonne.class).setParameter("e", e).getResultList();		
			
	}
	
	@Override
	public void updateIdGestionnaire(int idMod, int idGes, int idPersonne){
		System.out.println("\n\n\n idGes:" + idGes + "  idMod:" + idMod);
		
		try {
			DAOJPA.getManager().createNativeQuery("UPDATE modification_personne_film "
					+ "SET id_gestionnaire =" + idGes + " ,id_personne_film = " + idPersonne + " ,etat = 1" 
					+ " WHERE id_modification = " + idMod)					
					.executeUpdate();
			DAOJPA.commit();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

}
