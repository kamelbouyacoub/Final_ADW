package dao;

import java.util.List;

import film.PersonneFilm;

public interface DAOPersonneFilm {

	PersonneFilm get(int code);
	void save(PersonneFilm  personneFilm);
	void remove(PersonneFilm  personneFilm);
	int getNombrePersonneFilm();
	boolean findNom(String nom, String prenom);
	void popularite(PersonneFilm  personneFilm);
	List<PersonneFilm> loadAll();
	
}
