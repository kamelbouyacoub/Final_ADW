package dao;

import utilisateurs.Gestionnaire;
import utilisateurs.Personne;


public interface DAOGestionnaire {
	Gestionnaire get(int code);
	void save(Gestionnaire client);
	void remove(Gestionnaire client);
	int getNombreClients();
	boolean verifierEmail(Personne client);
	Gestionnaire findEmail(String email, String pass);
}