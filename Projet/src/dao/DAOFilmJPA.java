package dao;

import java.util.List;

import film.Film;

public class DAOFilmJPA implements DAOFilm {

	static private DAOFilmJPA instance= null;
	private static final String REQUETE_TITRE = "SELECT f FROM Film f WHERE f.nom LIKE :titre AND f.etat = 1";
			
			
	private DAOFilmJPA(){}
	
	static public DAOFilm getInstance() {
		if(instance== null)
			instance= new DAOFilmJPA();
		return instance;
	}
	
	@Override
	public Film get(int code) {
		Film film = DAOJPA.getManager().find(Film.class, code);		
		if (film == null)
			throw new IllegalArgumentException("Le Film n'existe pas!!!");
		return film;
	}

	@Override
	public void save(Film film) {
		/*if(film.getIdFilm() !=-1)
			throw new IllegalArgumentException("Film existant!!!");*/
				
		DAOJPA.getManager().persist(film);
		DAOJPA.commit(); /* discutable de commiter ici*/		

	}

	@Override
	public void remove(Film film) {
		if(film.getIdFilm()==-1)
			throw new IllegalArgumentException("Film n'existe pas!!!");
		
		DAOJPA.getManager().remove(film);
		DAOJPA.commit();	
		film.setIdFilm(-1);		
	}

	@Override
	public int getNombreFilm() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM film").getSingleResult();
		return l.intValue();
	}

	@Override
	public List<Film> loadAll() {		
		return DAOJPA.getManager().createQuery("select f from Film f where f.etat = 1 ",Film.class).getResultList();
	}
	
	@Override
	public List<Film> loadSpecificFilm(String film){
		return DAOJPA.getManager()		
		.createQuery(REQUETE_TITRE, Film.class)
		.setParameter("titre", "%" + film + "%").getResultList();
		
	}

}
