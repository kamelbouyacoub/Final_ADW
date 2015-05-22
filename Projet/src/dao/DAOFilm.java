package dao;

import java.util.List;

import film.Film;

public interface DAOFilm {

	Film get(int code);
	void save(Film film);
	void remove(Film film);
	int getNombreFilm();
	List<Film> loadAll();
	List<Film> loadSpecificFilm(String film);
	
}
