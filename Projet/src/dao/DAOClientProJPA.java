package dao;


import utilisateurs.ClientPro;
import utilisateurs.Personne;

public class DAOClientProJPA extends DAOJPA implements DAOClientPro {	
	
	static private DAOClientProJPA instance= null;
	
	private DAOClientProJPA(){}
	
	static public DAOClientPro getInstance() {
		if(instance== null)
			instance= new DAOClientProJPA();
		return instance;
	}

	@Override
	public ClientPro get(int code) {
		ClientPro client = DAOJPA.getManager().find(ClientPro.class, code);
		if (client == null)
			throw new IllegalArgumentException("Client n'existe pas!!!");
		return client;		
	}

	@Override
	public void save(ClientPro client) {
		if(client.getIdUtilisateur() !=-1)
			throw new IllegalArgumentException("Client Pro existant!!!");
		if(verifierEmail(client)){
			DAOJPA.getManager().persist(client);
			DAOJPA.commit(); /* discutable de commiter ici*/
		}else{
			throw new IllegalArgumentException("Email existant!!!");
		}
	}

	@Override
	public void remove(ClientPro client) {
		if(client.getIdUtilisateur()==-1)
			throw new IllegalArgumentException("CLient Pro n'existe pas!!!");
		
		DAOJPA.getManager().remove(client);
		DAOJPA.commit();	
		client.setIdUtilisateur(-1);
	}

	@Override
	public int getNombreClients() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM utilisateur_pro").getSingleResult();
		return l.intValue();
	}

	@Override
	public boolean verifierEmail(Personne client) {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM utilisateur_pro WHERE email =\"" 
															 + client.getEmail()+"\"").getSingleResult();
		boolean r = (l>0)?false:true;
		return r;
	}
	
	@Override
	public ClientPro findEmail(String email, String pass) {
		
		try {
			return  DAOJPA.getManager().createQuery("select cp from ClientPro cp where cp.email = :email and cp.pass= :pass",
					ClientPro.class).setParameter("email", email).setParameter("pass", pass).getSingleResult();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return null ;
	}


	
	
}
