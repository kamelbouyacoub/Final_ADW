package film;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import listes.Listes;



@Entity
@Table(name="modification_personne_film_film")
public class ModificationPersonneFilmFilm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_modification_role")
	private int idModification = -1;
	
	@Column(name = "id_utilisateur")
	private int idUtilisateur;
	
	@Column(name = "id_gestionnaire")
	private int idGestionnaire = 1; 
	
	@Column(name = "id_personne")
	private int idPersonneFilm;
	
	@Column(name = "jouer")
	private boolean jouer;
	
	@Column(name = "produit")
	private boolean produit;
	
	@Column(name = "realiser")
	private boolean realiser;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "etat")
	private boolean etat;

	@ManyToOne
	@JoinColumn(name = "id_film")
	private Film modFilmPersonne;	
	
	
	public ModificationPersonneFilmFilm(){
		super();
	}


	public ModificationPersonneFilmFilm(
			int idUtilisateur, int idGestionnaire, int idPersonneFilm, boolean jouer, boolean produit,
			boolean realiser, String description, boolean etat,
			Film modFilmPersonne) {
			super();
			this.idUtilisateur = idUtilisateur;
			this.idGestionnaire = idGestionnaire;
			this.idPersonneFilm = idPersonneFilm;
			this.jouer = jouer;
			this.produit = produit;
			this.realiser = realiser;
			this.description = description;
			this.etat = etat;
			this.modFilmPersonne = modFilmPersonne;
			Listes.add(modFilmPersonne.getLstModificationPersonneFilmFilm(), this);
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

	public int getIdPersonneFilm() {
		return idPersonneFilm;
	}

	public void setIdPersonneFilm(int idPersonneFilm) {
		this.idPersonneFilm = idPersonneFilm;
	}

	public boolean isJouer() {
		return jouer;
	}

	public void setJouer(boolean jouer) {
		this.jouer = jouer;
	}

	public boolean isProduit() {
		return produit;
	}

	public void setProduit(boolean produit) {
		this.produit = produit;
	}

	public boolean isRealiser() {
		return realiser;
	}

	public void setRealiser(boolean realiser) {
		this.realiser = realiser;
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
	
	public Film getModFilmPersonne() {
		return modFilmPersonne;
	}

	public void setModFilmPersonne(Film modFilmPersonne) {
		this.modFilmPersonne = modFilmPersonne;
	}	
	

}
