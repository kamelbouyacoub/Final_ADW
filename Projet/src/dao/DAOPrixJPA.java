package dao;

import java.util.List;

import film.Prix;

public class DAOPrixJPA implements DAOPrix {

	static private DAOPrixJPA instance= null;
	
	private DAOPrixJPA(){}
	
	static public DAOPrix getInstance() {
		if(instance== null)
			instance= new DAOPrixJPA();
		return instance;
	}
	
	
	public Prix get(int code) {
		Prix prix = DAOJPA.getManager().find(Prix.class, code);
		if (prix == null)
			throw new IllegalArgumentException("Prix n'existe pas!!!");
		return prix;		
	}

	@Override
	public void save(Prix prix) {
		if(prix.getIdPrix()!=-1)
			throw new IllegalArgumentException("prix existant!!!");
				
		DAOJPA.getManager().persist(prix);
		DAOJPA.commit(); /* discutable de commiter ici*/	

	}

	@Override
	public void remove(Prix prix) {
		if(prix.getIdPrix() ==-1)
			throw new IllegalArgumentException("prix ne existe pas!!!");
		
		DAOJPA.getManager().remove(prix);
		DAOJPA.commit();	
		prix.setIdPrix(-1);	

	}

	@Override
	public int getNombrePrix() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM prix").getSingleResult();
		return l.intValue();
	}

	@Override
	public List<Prix> getLstPrix() {		
		return DAOJPA.getManager().createQuery("SELECT p FROM Prix p",Prix.class).getResultList();
	}

}
