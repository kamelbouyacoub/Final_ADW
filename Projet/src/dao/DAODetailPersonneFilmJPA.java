package dao;

import java.util.List;

import listes.Listes;
import film.DetailPersonneFilm;
import film.Film;
import film.PersonneFilm;

public class DAODetailPersonneFilmJPA implements DAODetailPersonneFilm {

	static private DAODetailPersonneFilmJPA instance = null;

	private DAODetailPersonneFilmJPA() {
	}

	static public DAODetailPersonneFilm getInstance() {
		if (instance == null)
			instance = new DAODetailPersonneFilmJPA();
		return instance;
	}

	@Override
	public DetailPersonneFilm get(String code) {
		DetailPersonneFilm detailPersonneFilm = DAOJPA.getManager().find(DetailPersonneFilm.class, code);
		if (detailPersonneFilm == null)
			return null;
		else
			return detailPersonneFilm;
		
	}

	@Override
	public void save(DetailPersonneFilm detailPersonneFilm) {
		DAOJPA.getManager().persist(detailPersonneFilm);
		DAOJPA.commit(); /* discutable de commiter ici */

	}

	@Override
	public void remove(DetailPersonneFilm detailPersonneFilm) {
		if (detailPersonneFilm.getIdDetailPersonneFilm() == null)
			throw new IllegalArgumentException(
					"detailPersonneFilm n'existe pas!!!");

		DAOJPA.getManager().remove(detailPersonneFilm);
		DAOJPA.commit();
		detailPersonneFilm.setIdDetailPersonneFilm(null);
		Listes.remove(detailPersonneFilm.getFilmDetail()
				.getLstDetailPersonneFilm(), detailPersonneFilm);
		Listes.remove(detailPersonneFilm.getPersonneFilm()
				.getLstDetailPersonneFilm(), detailPersonneFilm);

		// ((Film)detailPersonneFilm.getFilmDetail()).removeLstDetailPersonneFilm(detailPersonneFilm);
		// ((PersonneFilm)detailPersonneFilm.getPersonneFilm()).removeLstDetailPersonneFilm(detailPersonneFilm);

	}

	@Override
	public int getNombreDetailPersonneFilm() {
		Long l = (Long) DAOJPA
				.getManager()
				.createNativeQuery(
						"SELECT COUNT(*) FROM detail_personne_film_film")
				.getSingleResult();
		return l.intValue();
	}

	@Override
	public List<DetailPersonneFilm> getLstPersonneFilm(Film film) {// personnes
																	// dans film
		return DAOJPA
				.getManager()
				.createQuery(
						"SELECT d FROM DetailPersonneFilm d WHERE d.filmDetail.idFilm=:film",
						DetailPersonneFilm.class)
				.setParameter("film", film.getIdFilm()).getResultList();
	}

	@Override
	public List<DetailPersonneFilm> getLstFilmPersone(PersonneFilm personne) {// films
																				// dans
																				// lesquels
																				// a
																				// joué
																				// un
																				// personne
		return DAOJPA
				.getManager()
				.createQuery(
						"select d from DetailPersonneFilm d where d.personneFilm.idPersonne=:personne",
						DetailPersonneFilm.class)
				.setParameter("personne", personne.getIdPersonne())
				.getResultList();

	}

	@Override
	public List<DetailPersonneFilm> loadAll() { // tout
		return DAOJPA
				.getManager()
				.createQuery("select d from DetailPersonneFilm d",
						DetailPersonneFilm.class).getResultList();
	}

	@Override
	public DetailPersonneFilm getRolePersonneFilm(PersonneFilm personne,
			Film film) {
		return DAOJPA
				.getManager()
				.createQuery(
						"select d from DetailPersonneFilm d where d.personneFilm.idPersonne=:personne and d.filmDetail.idFilm=:film",
						DetailPersonneFilm.class)
				.setParameter("personne", personne.getIdPersonne())
				.setParameter("film", film.getIdFilm()).getSingleResult();
	}

	@Override
	public List<DetailPersonneFilm> getLstActeursFilm(Film film) {// liste
																	// d'acteures
																	// dans un
																	// film
		return DAOJPA
				.getManager()
				.createQuery(
						"select d from DetailPersonneFilm d where d.joue=true and d.filmDetail.idFilm=:film",
						DetailPersonneFilm.class)
				.setParameter("film", film.getIdFilm()).getResultList();
	}

	@Override
	public List<DetailPersonneFilm> getLstRealisateursFilm(Film film) {// liste
																		// de
																		// realisateurs
																		// dans
																		// un
																		// film
		return DAOJPA
				.getManager()
				.createQuery(
						"select d from DetailPersonneFilm d where d.realise=true and d.filmDetail.idFilm=:film",
						DetailPersonneFilm.class)
				.setParameter("film", film.getIdFilm()).getResultList();
	}

	@Override
	public List<DetailPersonneFilm> getLstProducteursFilm(Film film) {// liste
																		// de
																		// producteurs
																		// dans
																		// un
																		// film
		return DAOJPA
				.getManager()
				.createQuery(
						"select d from DetailPersonneFilm d where d.produit=true and d.filmDetail.idFilm=:film",
						DetailPersonneFilm.class)
				.setParameter("film", film.getIdFilm()).getResultList();
	}

}
