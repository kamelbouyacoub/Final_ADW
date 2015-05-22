package dao;

import java.util.List;

import film.ModificationRecompensePersonne;
import film.RecompensePersonneFilm;

public interface DAOModificationRecompensePersonne {

	ModificationRecompensePersonne get(int code);

	void save(ModificationRecompensePersonne modif);

	void remove(ModificationRecompensePersonne modif);

	int getNombreModif();

	List<ModificationRecompensePersonne> loadAll();

	void acepterModification(RecompensePersonneFilm recompense);

	void updateIdGestionnaire(int idMod, int idGes);

}
