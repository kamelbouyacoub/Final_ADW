package dao;

import utilisateurs.Gestionnaire;
import utilisateurs.Personne;

public class DAOGestionnaireJPA extends DAOJPA implements DAOGestionnaire {
	
	
	static private DAOGestionnaire instance= null;
	
private DAOGestionnaireJPA(){}
	
	static public DAOGestionnaire getInstance() {
		if(instance== null)
			instance= new DAOGestionnaireJPA();
		return instance;
	}

	@Override
	public Gestionnaire get(int code) {
		Gestionnaire client = DAOJPA.getManager().find(Gestionnaire.class, code);
		if (client == null)
			throw new IllegalArgumentException("Gestionnaire n'existe pas!!!");
		return client;		
	}

	@Override
	public void save(Gestionnaire client) {
		if(client.getIdUtilisateur() !=-1)
			throw new IllegalArgumentException("gestionnaire  existant!!!");
		if(verifierEmail(client)){
			DAOJPA.getManager().persist(client);
			DAOJPA.commit(); /* discutable de commiter ici*/	
		}else{
			throw new IllegalArgumentException("Email existant!!!");
		}
	}

	@Override
	public void remove(Gestionnaire client) {
		if(client.getIdUtilisateur()==-1)
			throw new IllegalArgumentException("gestionnaire n'existe pas!!!");
		
		DAOJPA.getManager().remove(client);
		DAOJPA.commit();	
		client.setIdUtilisateur(-1);
	}

	@Override
	public int getNombreClients() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM utilisateur_gestionnaire").getSingleResult();
		return l.intValue();
	}

	@Override
	public boolean verifierEmail(Personne client) {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM utilisateur_gestionnaire WHERE email =\"" 
				 + client.getEmail()+"\"").getSingleResult();
		boolean r = (l>0)?false:true;
		return r;
	}
	
	@Override
	public Gestionnaire findEmail(String email, String pass) {
		
		try {
			return  DAOJPA.getManager().createQuery("select g from Gestionnaire g where g.email = :email and g.pass = :pass", Gestionnaire.class)
					.setParameter("email", email)
					.setParameter("pass", pass)
					.getSingleResult();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return null ;
	}


}
