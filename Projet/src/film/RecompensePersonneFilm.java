package film;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import test_proprietes.TestProprietes;
import listes.Listes;

@Entity
@Table(name="recompense_personne_film")
public class RecompensePersonneFilm  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_recompense")
	private int idRecompense=-1;
	
	@Column(name = "date_remise") 
	//@Temporal(TemporalType.DATE)
	private int date;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "id_prix")
	private Prix prixRecompense=null;	
	
	@ManyToOne
	@JoinColumn(name = "id_film")
	private Film filmRecompense;	
	
	@ManyToOne
	@JoinColumn(name ="id_personne_film")
	private PersonneFilm personneFilm=null;	

	
	public RecompensePersonneFilm() {
		super();		
	}
	public RecompensePersonneFilm(int date, Prix prixRecompense, Film filmRecompense, PersonneFilm personeFilm , String description) {
		
		TestProprietes.testerObjet(prixRecompense);
		TestProprietes.testerObjet(filmRecompense);
		TestProprietes.testerObjet(personeFilm);
		TestProprietes.testerPersistence(prixRecompense.getIdPrix());
		TestProprietes.testerPersistence(filmRecompense.getIdFilm());
		TestProprietes.testerPersistence(personeFilm.getIdPersonne());		
		this.date = date;	
		this.description = description;
		this.prixRecompense = prixRecompense;
		this.filmRecompense = filmRecompense;
		this.personneFilm = personeFilm;
		Listes.add(prixRecompense.getLstRecompencePersonne(), this);
		Listes.add(filmRecompense.getLstRecompensePersonneFilm(), this);
		Listes.add(personeFilm.getLstRecompensePersonneFilm(), this);
//		prixRecompense.addLstRecompencePersonne(this);	
//		filmRecompense.addLstRecompensePersonneFilm(this);
//		personeFilm.addLstRecompensePersonneFilm(this);
	}
	
	public int getIdRecompense() {
		return idRecompense;
	}
	
	public void setIdRecompense(int idRecompense) {
		this.idRecompense = idRecompense;
	}
	
	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}	
	
	public PersonneFilm getPersonneFilm() {
		return personneFilm;
	}
	public void setPersonneFilm(PersonneFilm personneFilm) {
		this.personneFilm = personneFilm;
	}
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Prix getPrixRecompense() {
		return prixRecompense;
	}

	public void setPrixRecompense(Prix prixRecompense) {
		this.prixRecompense = prixRecompense;
	}

	public Film getFilmRecompense() {
		return filmRecompense;
	}

	public void setFilmRecompense(Film filmRecompense) {
		this.filmRecompense = filmRecompense;
	}	

}
