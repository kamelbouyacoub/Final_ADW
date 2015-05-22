package listes;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import utilisateurs.ClientPro;
import utilisateurs.Gestionnaire;
import film.Film;
import film.ModificationPersonne;
import film.PersonneFilm;

public class TestListes {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("films");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		ArrayList <ModificationPersonne> lstPersonne =new  ArrayList<ModificationPersonne>();

		Film film = (Film)em.createQuery("SELECT f FROM Film f  WHERE f.idFilm=2").getSingleResult();
		Gestionnaire ges = (Gestionnaire)em.createQuery("SELECT g FROM Gestionnaire g  WHERE g.idUtilisateur=1").getSingleResult();
		ClientPro pro = (ClientPro)em.createQuery("SELECT p FROM ClientPro p  WHERE p.idUtilisateur=1").getSingleResult();
		PersonneFilm personne = (PersonneFilm)em.createQuery("SELECT p FROM PersonneFilm p  WHERE p.idPersonne=2").getSingleResult();

		/*public ModificationPersonne(Gestionnaire gestionnairePersonne, ClientPro clientProPersonne, PersonneFilm personneFilm,
				String nomMod, String prenomMod, Date dateNaissanceMod, String photoMod,int populariteMod,String descriptionEtat, boolean etat )*/

		ModificationPersonne modifPersonne = new ModificationPersonne(ges, pro, personne, "leo", "pato",Calendar.getInstance().getTime(),"pas photo",
				10,"rejetée",false);

		Class<Film> userFilm = Film.class;
		System.out.println("\n\n\n\n\n\n"+userFilm.getName()+"\n\n\n\n");

		System.out.println(modifPersonne.toString());

		Listes.add(lstPersonne,modifPersonne);
		System.out.println(lstPersonne.get(0).getNomMod());	


	}
}
