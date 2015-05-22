package testBD;


import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import tools.Tool;
import utilisateurs.ClientPro;
import utilisateurs.Gestionnaire;
import beansSession.BeanSessionPro;
import dao.DAOClientProJPA;
import dao.DAODetailPersonneFilm;
import dao.DAODetailPersonneFilmJPA;
import dao.DAOGestionnaireJPA;
import dao.DAOModificationPersonne;
import dao.DAOModificationPersonneJPA;
import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.DetailPersonneFilm;
import film.Film;
import film.ModificationPersonne;
import film.PersonneFilm;

public class TestUtilisateur {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("films");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
//		DAOClientPro dao = DAOClientProJPA.getInstance();
//		
//		
//		
//		System.out.println("\n***********************************************\n");
//		System.out.println("\n Lista de films: " + daoG.listarFilms().size());
//		System.out.println("\n***********************************************\n");
		
		/*tx.begin();		
		em.persist(typeOcasionnel);			
		em.persist(typePro);	
		em.persist( typeGestionaire);		
		tx.commit();*/  
		
		
		//test utilisateur avec client, client pro, gestionaire
//		tx.begin();
//		ClientPro client = new ClientPro("Maria","Quiroz", Calendar.getInstance().getTime(), "Sin Foto","maria@hotmail.com","1254781",true);		
//		em.persist(client);
//		tx.commit();
		
//		tx.begin();
//		Gestionnaire clientG = new Gestionnaire("Carlos","Sanin", Calendar.getInstance().getTime(), "Sin Foto","carlos@hotmail.com","1254781",true);		
//		em.persist(clientG);
//		tx.commit();
		
//		tx.begin();
//		PersonneFilm acteur = new PersonneFilm("Mel","Gibson",Calendar.getInstance().getTime(),"Sans photo", 10, true );		
//		em.persist(acteur);
//		tx.commit();
		
//		tx.begin();
//		Film film = new Film("Dragon Ball", Calendar.getInstance().getTime(), 1000, "Sans Photo", 2, true);		
//		em.persist(film);
//		tx.commit();
		
//		Gestionnaire ges = (Gestionnaire)em.createQuery("SELECT g FROM Gestionnaire g  WHERE g.idUtilisateur=1").getSingleResult();
//		ClientPro pro = (ClientPro)em.createQuery("SELECT p FROM ClientPro p  WHERE p.idUtilisateur=1").getSingleResult();
//		Film film = (Film)em.createQuery("SELECT f FROM Film f  WHERE f.idFilm=2").getSingleResult();

//		ModificationFilm modif_film = new ModificationFilm(ges, pro, film, "titanic", "pas photo","Accepter", true, Calendar.getInstance().getTime(), 10 , 10 ); 
//		
//		System.out.println("///////////////////////////////////////////////////////////////////////////////////////////\n");
//		System.out.println("///////////////////////////////////////////////////////////////////////////////////////////\n");
//		System.out.println("//////////////////////////////"+ges.getIdUtilisateur()+ pro.getIdUtilisateur()+film.getIdFilm()+ "\n");
//		System.out.println("///////////////////////////////////////////////////////////////////////////////////////////\n");
//		System.out.println("///////////////////////////////////////////////////////////////////////////////////////////\n");
//		tx.begin();				
//		em.persist(modif_film);
//		tx.commit();

//		PersonneFilm(String nom, String prenom, Date dateNaissance, String photo, int popularite, boolean etat) {
		
//		tx.begin();
//		PersonneFilm personne = new PersonneFilm("leonardo","Di caprio",Calendar.getInstance().getTime(),"pas photo", 9,true);		
//		em.persist(personne);
//		tx.commit();
//		
//		Film film = (Film)em.createQuery("SELECT f FROM Film f  WHERE f.idFilm=2").getSingleResult();
//		Gestionnaire ges = (Gestionnaire)em.createQuery("SELECT g FROM Gestionnaire g  WHERE g.idUtilisateur=1").getSingleResult();
//		ClientPro pro = (ClientPro)em.createQuery("SELECT p FROM ClientPro p  WHERE p.idUtilisateur=1").getSingleResult();
//		PersonneFilm personne = (PersonneFilm)em.createQuery("SELECT p FROM PersonneFilm p  WHERE p.idPersonne=2").getSingleResult();
//		
////		public ModificationPersonne(Gestionnaire gestionnairePersonne, ClientPro clientProPersonne, PersonneFilm personneFilm,
////				String nomMod, String prenomMod, Date dateNaissanceMod, String photoMod,int populariteMod,String descriptionEtat, boolean etat )
//		
//		ModificationPersonne modifPersonne = new ModificationPersonne(ges, pro, personne, "leo", "pato",Calendar.getInstance().getTime(),"pas photo",
//				10,"rejetée",false);
//		
//		
//		DetailPersonneFilm detail = new DetailPersonneFilm(personne, film, true,
//				  true, true); 
//		
		
//		tx.begin();
//		em.persist(detail);
//		tx.commit();
		
		
		//Prix prix = new Prix("baby one more time", "c'est très sexy", true);
//		Film film = (Film)em.createQuery("SELECT f FROM Film f  WHERE f.idFilm=1").getSingleResult();
//		Prix prix = (Prix)em.createQuery("SELECT p FROM Prix p  WHERE p.idPrix=2").getSingleResult();
//		PersonneFilm personne = (PersonneFilm)em.createQuery("SELECT pf FROM PersonneFilm pf  WHERE pf.idPersonne =2").getSingleResult();
//		
//		
//		RecompensePersonneFilm recompense_personne_film = new RecompensePersonneFilm(Calendar.getInstance().getTime(), prix, film, personne);
//		
//		tx.begin();
//		em.persist(recompense_personne_film);
//		tx.commit();
		
		//ClientPro client = new ClientPro("Tola","Quiroz", Calendar.getInstance().getTime(), "Sin Foto","maria@hotmail.com","1254781",true);
		//dao.save(client);
		
//		dao = DAOClientProJPA.getInstance();
//		ClientPro cp = dao.findEmail("carlossanin@gmail.com");	
//		System.out.printf(cp.toString());	
		
		Film film = (Film)em.createQuery("SELECT f FROM Film f  WHERE f.idFilm=2").getSingleResult();
//		DAODetailPersonneFilm dao = DAODetailPersonneFilmJPA.getInstance();
//		
//		dao.getLstActeuresFilm(film);		
//		System.out.println("\n Lista d'acteurs: " + dao.getLstActeuresFilm(film).size());
//		for(DetailPersonneFilm result : dao.getLstActeuresFilm(film))
//			System.out.println(result.getPersonneFilm().getNom());
//		
//		dao.getLstProduecteursFilm(film);
//		System.out.println("\n Lista de producteurs: " + dao.getLstProduecteursFilm(film).size());
//		for(DetailPersonneFilm result : dao.getLstProduecteursFilm(film))
//			System.out.println(result.getPersonneFilm().getNom());
//		
//		dao.getLstRealisateursFilm(film);
//		System.out.println("\n Lista de realisateurs: " + dao.getLstRealisateursFilm(film).size());
//		for(DetailPersonneFilm result : dao.getLstRealisateursFilm(film))
//			System.out.println(result.getPersonneFilm().getNom());
//		
		PersonneFilm personne = (PersonneFilm)em.createQuery("SELECT p FROM PersonneFilm p  WHERE p.idPersonne=2").getSingleResult();
//		DAOPersonneFilm daoP = DAOPersonneFilmJPA.getInstance();
//		daoP.popularite(personne);
		
		//gestionnaire generique
		Gestionnaire g = DAOGestionnaireJPA.getInstance().get(100);		
		//on cherche le client en session
		ClientPro c = DAOClientProJPA.getInstance().get(100);
		//acteur generique
		PersonneFilm p = DAOPersonneFilmJPA.getInstance().get(100);
		//donnes à sauver si'l le gestionnaire accepte
		ModificationPersonne mp = new ModificationPersonne(g, c, p, "sanin", "carlos", 
				Calendar.getInstance().getTime(),"sans photo",
				0, "save", true);
		DetailPersonneFilm d = new DetailPersonneFilm(personne, film, false, true, false);
		
		
		DAODetailPersonneFilm dao = DAODetailPersonneFilmJPA.getInstance();	
//		DetailPersonneFilm d  = dao.get("22");
//		d.setProduit(false);
//		d.setJoue(true);
//		d.setRealise(false);
		dao.save(d);
		
	}

}
