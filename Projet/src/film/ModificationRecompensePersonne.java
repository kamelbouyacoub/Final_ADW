package film;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import listes.Listes;


@Entity
@Table(name="modification_recompense_personne")
public class ModificationRecompensePersonne {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_modification_recompense_personne")
	private int idModification = -1;
	
	

	@Column(name = "id_utilisateur")
	private int idUtilisateur;
	
	@Column(name = "id_gestionnaire")
	private int idGestionnaire;
	
	@Column(name = "id_prix")
	private int idPrix;
	
	@Column(name = "id_film")
	private int idFilm;
	
	

	@Column(name = "date_remise")
	private int dateRemise;  
	
	@Column(name = "description")
	private String description; 
	
	@Column(name = "etat")
	private boolean etat; 
	
	@ManyToOne
	@JoinColumn(name = "id_personne_film")
	private PersonneFilm personneFilm;

	

	public ModificationRecompensePersonne() {
		super();
	}


	public ModificationRecompensePersonne(int idUtilisateur,
			int idGestionnaire, int idPrix, int idFilm,  int dateRemise, String description,
			boolean etat, PersonneFilm personneFilm) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.idGestionnaire = idGestionnaire;
		this.idPrix = idPrix;
		this.dateRemise = dateRemise;
		this.description = description;
		this.etat = etat;
		this.idFilm = idFilm;
		this.personneFilm = personneFilm;
		
		Listes.add(personneFilm.getLstModificationRecompense(), this);
	}
	
	
	
	
	public int getIdModification() {
		return idModification;
	}

	public void setIdModification(int idModification) {
		this.idModification = idModification;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getIdGestionnaire() {
		return idGestionnaire;
	}

	public void setIdGestionnaire(int idGestionnaire) {
		this.idGestionnaire = idGestionnaire;
	}

	public int getIdPrix() {
		return idPrix;
	}

	public void setIdPrix(int idPrix) {
		this.idPrix = idPrix;
	}

	public int getDateRemise() {
		return dateRemise;
	}

	public void setDateRemise(int dateRemise) {
		this.dateRemise = dateRemise;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public PersonneFilm getPersonneFilm() {
		return personneFilm;
	}

	public void setPersonneFilm(PersonneFilm personneFilm) {
		this.personneFilm = personneFilm;
	}

	public int getIdFilm() {
		return idFilm;
	}


	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}
	
}
