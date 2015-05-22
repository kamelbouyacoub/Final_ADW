package dao;

import java.util.List;

import film.Prix;

public interface DAOPrix {

	Prix get(int code);
	void save(Prix  prix);
	void remove(Prix  prix);
	int getNombrePrix();
	List<Prix> getLstPrix();
	
}
