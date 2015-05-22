package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DAOJPA {
	private static EntityManagerFactory emf= null;
	private static EntityManager em= null;
	private static EntityTransaction tx= null;
	
	public static EntityManager getManager(){
		
		if(em == null){
			emf = Persistence.createEntityManagerFactory("films");
			em = emf.createEntityManager();
			tx = em.getTransaction();
		}
		if(!tx.isActive()) {
			tx.begin();
		}
		return em;
		
	}//fin getManager
	
	public static void commit() {
		em.flush();
		//em.clear();
		tx.commit();
	}//fin commit
	
	public static void rollback() {
		tx.rollback();
	}//fin rollback
	
	public static void close() {
		em.close();em= null;
		emf.close();emf= null;
	}//fin close
	
	public static void viderBase() {
		getManager().createQuery("DELETE FROM Article").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE article AUTO_INCREMENT = 1").executeUpdate();
		getManager().createQuery("DELETE FROM Auteur").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE auteur AUTO_INCREMENT = 1").executeUpdate();
		getManager().createQuery("DELETE FROM Page").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE page AUTO_INCREMENT = 1").executeUpdate();
		
		getManager().createNativeQuery("DELETE FROM contient").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE contient AUTO_INCREMENT = 1").executeUpdate();
		
	}//fin viderBase

}//fin class DAOJPA
