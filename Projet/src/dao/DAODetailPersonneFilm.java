package dao;

import java.util.List;

import film.DetailPersonneFilm;
import film.Film;
import film.PersonneFilm;

public interface DAODetailPersonneFilm {

	DetailPersonneFilm get(String code);
	void save(DetailPersonneFilm  detailPersonneFilm);
	void remove(DetailPersonneFilm  detailPersonneFilm);
	int getNombreDetailPersonneFilm();
	List<DetailPersonneFilm> getLstPersonneFilm(Film film);//personnes dans film
	List<DetailPersonneFilm> getLstFilmPersone(PersonneFilm personne); //film dans lesquels a joué un personne
	List<DetailPersonneFilm> getLstActeursFilm(Film film);//liste d'acteures dans un film
	List<DetailPersonneFilm> getLstRealisateursFilm(Film film);//liste de realisateurs dans un film
	List<DetailPersonneFilm> getLstProducteursFilm(Film film);//liste de producteurs dans un film
	List<DetailPersonneFilm> loadAll();//todos los films avec ses details
	DetailPersonneFilm getRolePersonneFilm(PersonneFilm personne, Film Film);
}
