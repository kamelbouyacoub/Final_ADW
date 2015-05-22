package dao;

import java.util.List;

import film.PersonneFilm;

public class DAOPersonneFilmJPA implements DAOPersonneFilm {

	static private DAOPersonneFilmJPA instance= null;
	
	private DAOPersonneFilmJPA(){}
	
	static public DAOPersonneFilm getInstance() {
		if(instance== null)
			instance= new DAOPersonneFilmJPA();
		return instance;
	}	
	
	@Override
	public PersonneFilm get(int code) {
		PersonneFilm personneFilm = DAOJPA.getManager().find(PersonneFilm.class, code);
		if (personneFilm == null)
			throw new IllegalArgumentException("Relation Personne Film n'existe pas!!!");
		return personneFilm;		
	}

	@Override
	public void save(PersonneFilm personneFilm) {
//		if(personneFilm.getIdPersonne()!=-1)
//			throw new IllegalArgumentException("Modification existant!!!");
				
		DAOJPA.getManager().persist(personneFilm);
		DAOJPA.commit(); /* discutable de commiter ici*/	

	}

	@Override
	public void remove(PersonneFilm personneFilm) {
		if(personneFilm.getIdPersonne()==-1)
			throw new IllegalArgumentException("Modification ne existe pas!!!");
		
		DAOJPA.getManager().remove(personneFilm);
		DAOJPA.commit();	
		personneFilm.setIdPersonne(-1);		
	}

	@Override
	public int getNombrePersonneFilm() {
		Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM personne_film").getSingleResult();
		return l.intValue();
	}

	@SuppressWarnings("finally")
	@Override
	public boolean findNom(String nom, String prenom) {

		try {
			Long l = (Long)DAOJPA.getManager().createNativeQuery("SELECT COUNT(*) FROM personne_film WHERE nom = \'" + nom +"\' and prenom = \'" + prenom +"\'").getSingleResult();
			boolean r = (l>0)?true:false;
			return r;
		} catch (Exception e) {			
			e.printStackTrace();
		}
		finally{
			return false;
		}	
	}
	
	@Override
	public List<PersonneFilm> loadAll() {		
		return DAOJPA.getManager().createQuery("select p from PersonneFilm p",PersonneFilm.class).getResultList();
	}

	@Override
	public void popularite(PersonneFilm  personneFilm) {	
//		personneFilm.setPopularite(personneFilm.getPopularite()+1);
//		System.out.println("\n\n\npopularite=" + personneFilm.getPopularite());
		DAOJPA.getManager().createQuery("UPDATE PersonneFilm p SET p.popularite=p.popularite+1 WHERE p.idPersonne=:personneFilm",
				PersonneFilm.class)
				.setParameter("personneFilm", personneFilm.getIdPersonne()).executeUpdate();
		 DAOJPA.commit();
	}
}
										