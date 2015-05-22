package dao;

import java.util.List;

import listes.Listes;
import film.ModificationRecompenseFilm;
import film.RecompenseFilm;

public class DAOModificationRecompenseFilmJPA implements
		DAOModificationRecompenseFilm {

	static private DAOModificationRecompenseFilm instance = null;

	static public DAOModificationRecompenseFilm getInstance() {
		if (instance == null)
			instance = new DAOModificationRecompenseFilmJPA();
		return instance;
	}

	@Override
	public ModificationRecompenseFilm get(int code) {
		ModificationRecompenseFilm modRecompenseFilm = DAOJPA.getManager()
				.find(ModificationRecompenseFilm.class, code);
		if (modRecompenseFilm == null)
			throw new IllegalArgumentException(
					"Modification Personne Film Film n'existe pas!!!");
		return modRecompenseFilm;

	}

	@Override
	public void save(ModificationRecompenseFilm modRecompenseFilm) {

		DAOJPA.getManager().persist(modRecompenseFilm);
		DAOJPA.commit(); /* discutable de commiter ici */

	}

	@Override
	public void remove(ModificationRecompenseFilm modRecompenseFilm) {

		if (modRecompenseFilm.getIdModifRecompenseFilm() == -1)
			throw new IllegalArgumentException(
					"Modification film n'existe pas!!!");

		DAOJPA.getManager().remove(modRecompenseFilm);
		DAOJPA.commit();
		modRecompenseFilm.setIdModifRecompenseFilm(-1);
		Listes.remove(modRecompenseFilm.getModRecFilm()
				.getLstModificationPersonneFilmFilm(), modRecompenseFilm);
	}

	@Override
	public int getNombreModif() {
		Long l = (Long) DAOJPA
				.getManager()
				.createNativeQuery(
						"SELECT COUNT(*) FROM modification_recompense_film WHERE etat =1")
				.getSingleResult();
		return l.intValue();
	}

	@Override
	public List<ModificationRecompenseFilm> loadAll() {
		boolean e =false;
		return DAOJPA
				.getManager()
				.createQuery("select mrf from ModificationRecompenseFilm mrf where mrf.etat = :e",
						ModificationRecompenseFilm.class).setParameter("e", e).getResultList();
	}

	@Override
	public void acepterModification(RecompenseFilm recompense) {
		DAORecompenseFilm dao = DAORecompenseFilmJPA.getInstance();
		dao.save(recompense);
	}

	@Override
	public void updateIdGestionnaire(int idMod, int idGes){
		System.out.println("\n\n\n idGes:" + idGes + "  idMod:" + idMod);
		
		try {
			DAOJPA.getManager().createNativeQuery("UPDATE modification_recompense_film "
					+ "SET id_gestionnaire =" + idGes +" ,etat = 1" 
					+ " WHERE id_modification_recompense_film = " + idMod)					
					.executeUpdate();
			DAOJPA.commit();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}