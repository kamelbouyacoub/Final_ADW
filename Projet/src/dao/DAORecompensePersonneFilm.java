package dao;

import java.util.List;

import film.DetailPersonneFilm;
import film.RecompensePersonneFilm;

public interface DAORecompensePersonneFilm {

	RecompensePersonneFilm get(int code);
	void save(RecompensePersonneFilm  recompensePersonneFilm);
	void remove(RecompensePersonneFilm  recompensePersonneFilm);
	int getNombreRecompenseFilm();
	public List<RecompensePersonneFilm> getRecompensePersonneFilm(DetailPersonneFilm personneDetails);
	
	
}
