package dao;

import java.util.List;

import film.ModificationRecompenseFilm;
import film.RecompenseFilm;

public interface DAOModificationRecompenseFilm {

	ModificationRecompenseFilm get(int code);

	void save(ModificationRecompenseFilm client);

	void remove(ModificationRecompenseFilm client);

	int getNombreModif();

	List<ModificationRecompenseFilm> loadAll();

	void acepterModification(RecompenseFilm recompense);

	void updateIdGestionnaire(int idMod, int idGes);
}
