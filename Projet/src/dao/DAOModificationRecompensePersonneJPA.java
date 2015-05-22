package dao;

import java.util.List;

import listes.Listes;
import film.ModificationRecompensePersonne;
import film.RecompensePersonneFilm;

public class DAOModificationRecompensePersonneJPA implements
		DAOModificationRecompensePersonne {

	static private DAOModificationRecompensePersonne instance= null;
	
	static public DAOModificationRecompensePersonne getInstance() {
		if(instance== null)
			instance= new DAOModificationRecompensePersonneJPA();
		return instance;
	}
	
	
	public DAOModificationRecompensePersonneJPA() {
		super();

	}
	
	
	

	@Override
	public ModificationRecompensePersonne get(int code) {
		ModificationRecompensePersonne modRecompensePersonne = DAOJPA.getManager().find(ModificationRecompensePersonne.class, code);
		if (modRecompensePersonne == null)
			throw new IllegalArgumentException("Modification Recompense Personne n'existe pas!!!");
		return modRecompensePersonne;	
		
	}

	@Override
	public void save(ModificationRecompensePersonne modif) {

		DAOJPA.getManager().persist(modif);
		DAOJPA.commit(); /* discutable de commiter ici*/

	}

	@Override
	public void remove(ModificationRecompensePersonne modif) {

		if(modif.getIdModification() ==-1)
			throw new IllegalArgumentException("Recompense Personne n'existe pas!!!");
		
		DAOJPA.getManager().remove(modif);
		DAOJPA.commit();	
		modif.setIdModification(-1);		
		Listes.remove(modif.getPersonneFilm().getLstModificationRecompense(), modif);	
	}

	@Override
	public int getNombreModif() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM modification_recompense_film WHERE etat = 0 ").getSingleResult();
		return l.intValue(); 	
	}

	@Override
	public List<ModificationRecompensePersonne> loadAll() {
		boolean e = false;
		return DAOJPA.getManager().createQuery("select mfp from ModificationRecompensePersonne mfp where mfp.etat= :e "
				,ModificationRecompensePersonne.class).setParameter("e", e).getResultList();
	}

	@Override
	public void acepterModification(RecompensePersonneFilm recompense) {
		DAORecompensePersonneFilm dao = DAORecompensePersonneFilmJPA.getInstance(); 
		dao.save(recompense);		

	}
	
	
	@Override
	public void updateIdGestionnaire(int idMod, int idGes){
		System.out.println("\n\n\n idGes:" + idGes + "  idMod:" + idMod);
		
		try {
			DAOJPA.getManager().createNativeQuery("UPDATE modification_recompense_personne "
					+ "SET id_gestionnaire =" + idGes +" ,etat = 1" 
					+ " WHERE id_modification_recompense_personne = " + idMod)					
					.executeUpdate();
			DAOJPA.commit();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

}
