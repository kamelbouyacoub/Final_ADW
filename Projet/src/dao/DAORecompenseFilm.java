package dao;

import java.util.List;

import film.Film;
import film.RecompenseFilm;

public interface DAORecompenseFilm {


	RecompenseFilm get(int code);
	void save(RecompenseFilm  recompenseFilm);
	void remove(RecompenseFilm  recompenseFilm);
	int getNombreRecompenseFilm();
	List<RecompenseFilm> loadAll();
	List<RecompenseFilm> getRecompenseFilm(Film film);
	
}
