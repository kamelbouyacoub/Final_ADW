package dao;

import java.util.List;

import listes.Listes;
import film.Film;
import film.RecompenseFilm;

public class DAORecompenseFilmJPA implements DAORecompenseFilm {

	static private DAORecompenseFilmJPA instance= null;
	
	private DAORecompenseFilmJPA(){}
	
	static public DAORecompenseFilm getInstance() {
		if(instance== null)
			instance= new DAORecompenseFilmJPA();
		return instance;
	}	
	
	@Override
	public RecompenseFilm get(int code) {
		RecompenseFilm recompenseFilm = DAOJPA.getManager().find(RecompenseFilm.class, code);
		if (recompenseFilm == null)
			throw new IllegalArgumentException("Recompense Film n'existe pas!!!");
		return recompenseFilm;	
	}

	@Override
	public void save(RecompenseFilm recompenseFilm) {
		if(recompenseFilm.getIdRecompense()!=-1)
			throw new IllegalArgumentException("prix existant!!!");
				
		DAOJPA.getManager().persist(recompenseFilm);
		DAOJPA.commit(); /* discutable de commiter ici*/	
		
	}

	@Override
	public void remove(RecompenseFilm recompenseFilm) {
		if(recompenseFilm.getIdRecompense() ==-1)
			throw new IllegalArgumentException("prix ne existe pas!!!");
		
		DAOJPA.getManager().remove(recompenseFilm);
		DAOJPA.commit();	
		recompenseFilm.setIdRecompense(-1);
		
		Listes.remove(recompenseFilm.getRecompenseFilm().getLstRecompenseFilm(), recompenseFilm);
		Listes.remove(recompenseFilm.getRecompensePrix().getLstRecompenseFilm(), recompenseFilm);
	}

	@Override
	public int getNombreRecompenseFilm() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM recompense_film").getSingleResult();
		return l.intValue();
	}

	@Override
	public List<RecompenseFilm> loadAll() {		
		return DAOJPA.getManager().createQuery("SELECT r FROM RecompenseFilm r",RecompenseFilm.class).getResultList();
	}

	@Override
	public List<RecompenseFilm> getRecompenseFilm(Film film) {		
		return DAOJPA.getManager().createQuery("SELECT r FROM RecompenseFilm r WHERE r.recompenseFilm.idFilm=:film",RecompenseFilm.class).setParameter("film", film.getIdFilm()).getResultList();
	}

	
}
