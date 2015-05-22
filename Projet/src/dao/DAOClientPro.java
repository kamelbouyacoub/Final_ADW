package dao;

import utilisateurs.ClientPro;
import utilisateurs.Personne;


public interface DAOClientPro {

	ClientPro get(int code);
	void save(ClientPro client);
	void remove(ClientPro client);
	int getNombreClients();
	boolean verifierEmail(Personne client);
	ClientPro findEmail(String email, String pass);
}
