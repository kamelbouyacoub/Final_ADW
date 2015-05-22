package dao;

import java.util.List;

import listes.Listes;
import film.DetailPersonneFilm;
import film.RecompensePersonneFilm;

public class DAORecompensePersonneFilmJPA implements DAORecompensePersonneFilm {

	static private DAORecompensePersonneFilmJPA instance= null;
	
	private DAORecompensePersonneFilmJPA(){}
	
	static public DAORecompensePersonneFilm getInstance() {
		if(instance== null)
			instance= new DAORecompensePersonneFilmJPA();
		return instance;
	}
	
	
	@Override
	public RecompensePersonneFilm get(int code) {
		RecompensePersonneFilm recompensePersonneFilm = DAOJPA.getManager().find(RecompensePersonneFilm.class, code);
		if (recompensePersonneFilm == null)
			throw new IllegalArgumentException("Recompense Personne-Film n'existe pas!!!");
		return recompensePersonneFilm;	
	}

	@Override
	public void save(RecompensePersonneFilm recompensePersonneFilm) {
//		if(recompensePersonneFilm.getIdRecompense()!=-1)
//			throw new IllegalArgumentException("prix existant!!!");
				
		DAOJPA.getManager().persist(recompensePersonneFilm);
		DAOJPA.commit(); /* discutable de commiter ici*/	
		
	}

	@Override
	public void remove(RecompensePersonneFilm recompensePersonneFilm) {
		if(recompensePersonneFilm.getIdRecompense() ==-1)
			throw new IllegalArgumentException("prix ne existe pas!!!");
		
		DAOJPA.getManager().remove(recompensePersonneFilm);
		DAOJPA.commit();	
		recompensePersonneFilm.setIdRecompense(-1);
		
		Listes.remove(recompensePersonneFilm.getFilmRecompense().getLstRecompensePersonneFilm(), recompensePersonneFilm);
		Listes.remove(recompensePersonneFilm.getPrixRecompense().getLstRecompencePersonne(), recompensePersonneFilm);
		Listes.remove(recompensePersonneFilm.getPersonneFilm().getLstRecompensePersonneFilm(), recompensePersonneFilm);
	}

	@Override
	public int getNombreRecompenseFilm() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM recompense_personne_film").getSingleResult();
		return l.intValue();
	}

	@Override
	public List<RecompensePersonneFilm> getRecompensePersonneFilm(DetailPersonneFilm personneDetails) {		
		return DAOJPA.getManager().createQuery("SELECT rp FROM RecompensePersonneFilm rp WHERE rp.filmRecompense.idFilm=:film",RecompensePersonneFilm.class).setParameter("film", personneDetails.getFilmDetail().getIdFilm()).getResultList();
	}

	
}
