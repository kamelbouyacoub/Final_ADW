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

import listes.Listes;



@Entity
@Table(name="modification_recompense_film")
public class ModificationRecompenseFilm  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_modification_recompense_film	")
	private int idModifRecompenseFilm = -1;
	
	

	@Column(name = "id_utilisateur")
	private int idUtilisateur;
	
	@Column(name = "id_gestionnaire")
	private int idGestionnaire;
	
	@Column(name = "id_prix")
	private int idPrix;
	
	@Column(name = "date_remise")
	private int dateRemise;  
	
	@Column(name = "description")
	private String description; 
	
	@Column(name = "etat")
	private boolean etat; 
	
	@ManyToOne
	@JoinColumn(name = "id_film")
	private Film modRecFilm;
	
	
	public ModificationRecompenseFilm(){
		super();
	}
	
	public ModificationRecompenseFilm(int idUtilisateur,
			int idGestionnaire,int idPrix, int dateRemise,
			String description, boolean etat, Film modRecFilm) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.idGestionnaire = idGestionnaire;		
		this.idPrix = idPrix;
		this.dateRemise = dateRemise;
		this.description = description;
		this.etat = etat;
		this.modRecFilm = modRecFilm;
		Listes.add(modRecFilm.getLstModificationRecompenseFilm(), this);
	}

	public Film getModRecFilm() {
		return modRecFilm;
	}

	public int getIdModifRecompenseFilm() {
		return idModifRecompenseFilm ;
	}

	public void setIdModifRecompenseFilm(int idRecompenseFilm) {
		this.idModifRecompenseFilm  = idRecompenseFilm;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setModRecFilm(Film modRecFilm) {
		this.modRecFilm = modRecFilm;
	}
	
}
