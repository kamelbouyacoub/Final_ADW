package dao;

import java.util.List;

import film.ModificationPersonne;

public interface DAOModificationPersonne {
	ModificationPersonne get(int code);
	void save(ModificationPersonne modification);
	void remove(ModificationPersonne modification);
	int getNombreModification();
	public List<ModificationPersonne> loadAll();
	void updateIdGestionnaire(int idMod, int idGes, int idPersonne);
			
}
