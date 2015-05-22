package utilisateurs;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import test_proprietes.TestProprietes;



@MappedSuperclass
public abstract class Personne {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_utilisateur")	
	private int idUtilisateur=-1;
		
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="date_naissance")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	@Column(name="photo")
	private String photo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="pass")
	private String pass;
		
	@Column(name="etat")
	private boolean etat = true;
	
		
	public Personne() {	
		super(); 
	}	
	
	public Personne(String nom, String prenom, Date dateNaissance, String photo, String email, String pass, boolean etat) {
		super();
		//Attention tester les proprietées
		
		TestProprietes.testerChaine(nom);
		TestProprietes.testerChaine(prenom);
		TestProprietes.testerDate(dateNaissance);
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.photo = photo;
		this.email=email;
		this.pass=pass;
		this.etat = etat;
		
	}	
	
	public void setIdUtilisateur(int idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}
	
	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

		
}
