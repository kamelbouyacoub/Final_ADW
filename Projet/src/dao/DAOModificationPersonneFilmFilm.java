package dao;

import java.util.List;

import film.DetailPersonneFilm;
import film.ModificationPersonneFilmFilm;

public interface DAOModificationPersonneFilmFilm {

	ModificationPersonneFilmFilm get(int code);
	void save(ModificationPersonneFilmFilm client);
	void remove(ModificationPersonneFilmFilm client);
	int getNombreModif();
	List<ModificationPersonneFilmFilm> loadAll();
	void acepterModification(DetailPersonneFilm detail);
	void updateIdGestionnaire(int idMod, int idGes);
}
