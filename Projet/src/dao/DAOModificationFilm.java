package dao;

import java.util.List;

import film.ModificationFilm;

public interface DAOModificationFilm {

	ModificationFilm get(int code);
	void save(ModificationFilm modification);
	void remove(ModificationFilm modification);
	int getNombreModification();
	List<ModificationFilm> loadAll();
	void updateIdGestionnaire(int idMod, int idGes, int idFilm);
}
