package test_proprietes;


import java.util.Date;



public class TestProprietes {


	public TestProprietes() {
		super();
	}


	// Tester un Objet
	public static void testerObjet(Object o) {
		if (o==null) 
			throw new IllegalArgumentException("Object null");
	}


	// Tester une Chaine de charactère
	public static void testerChaine(String chaine) {
		if ((chaine==null) || (chaine.trim().length()==0))
			throw new IllegalArgumentException("Champ vide ou null");
	}

	//Tester une Date
	public static void testerDate(Date date) {
		if (date==null)
			throw new IllegalArgumentException("Date Null");

	}
	
	//tester persistence d'un objet
	public static void testerPersistence(int id){
//		if(id == -1)
//			//throw new IllegalArgumentException("L'objet n'existe pas");
	}

	/* Validation de l'adresse email */
	public static void testerEmail( String email )  {
		if ( email != null ) {
			if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				throw new IllegalArgumentException( "Merci de saisir une adresse mail valide.");
			}else {
				throw new IllegalArgumentException( "Merci de saisir une adresse mail." );
			}
		}
	}
	
	
}
