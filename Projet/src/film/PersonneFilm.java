package film;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import test_proprietes.TestProprietes;
@Entity
@Table(name="personne_film")
public class PersonneFilm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_personne_film")	
	private int idPersonne=-1;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="date_naissance")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	@Column(name="photo")
	private String photo = "sans photo";
	
	@Column(name="popularite")
	private int popularite;
	
	@Column(name="etat")
	private boolean etat;

	@OneToMany(mappedBy="personneFilm" ,cascade=CascadeType.ALL)
	private List<ModificationPersonne> lstModificationPersonne = new ArrayList<ModificationPersonne>();		
	
	@OneToMany(mappedBy="personneFilm" ,cascade=CascadeType.ALL)
	private List<DetailPersonneFilm> lstDetailPersonneFilm = new ArrayList<DetailPersonneFilm>();
	
	@OneToMany(mappedBy = "personneFilm",cascade=CascadeType.ALL)
	private List<RecompensePersonneFilm> lstRecompensePersonneFilm= new ArrayList<RecompensePersonneFilm>();

	@OneToMany(mappedBy = "personneFilm",cascade=CascadeType.ALL)
	private List<ModificationRecompensePersonne> lstModificationRecompense= new ArrayList<ModificationRecompensePersonne>();
	


	public PersonneFilm() {
		super();		
	}
	
	
	public PersonneFilm(String nom, String prenom,
			Date dateNaissance, String photo, int popularite, boolean etat) {
		super();
		TestProprietes.testerChaine(nom);
		TestProprietes.testerChaine(prenom);
		TestProprietes.testerDate(dateNaissance);
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.photo = photo;
		this.popularite = popularite;
		this.etat = etat;
	}


	public void setIdPersonne(int idPersonne){
		this.idPersonne= idPersonne;
	}
	
	public int getIdPersonne() {
		return idPersonne;
	}	

	public int getPopularite() {
		return popularite;
	}

	public void setPopularite(int popularite) {
		this.popularite = popularite;
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



	public boolean isEtat() {
		return etat;
	}



	public void setEtat(boolean etat) {
		this.etat = etat;
	}



	public List<ModificationPersonne> getLstModificationPersonne() {
		return lstModificationPersonne;
	}



	public void setLstModificationPersonne(
			List<ModificationPersonne> lstModificationPersonne) {
		this.lstModificationPersonne = lstModificationPersonne;
	}



	public List<DetailPersonneFilm> getLstDetailPersonneFilm() {
		return lstDetailPersonneFilm;
	}



	public void setLstDetailPersonneFilm(
			List<DetailPersonneFilm> lstDetailPersonneFilm) {
		this.lstDetailPersonneFilm = lstDetailPersonneFilm;
	}



	public List<RecompensePersonneFilm> getLstRecompensePersonneFilm() {
		return lstRecompensePersonneFilm;
	}



	public void setLstRecompensePersonneFilm(
			List<RecompensePersonneFilm> lstRecompensePersonneFilm) {
		this.lstRecompensePersonneFilm = lstRecompensePersonneFilm;
	}

	public List<ModificationRecompensePersonne> getLstModificationRecompense() {
		return lstModificationRecompense;
	}



	public void setLstModificationRecompense(
			List<ModificationRecompensePersonne> lstModificationRecompense) {
		this.lstModificationRecompense = lstModificationRecompense;
	}
	

}
