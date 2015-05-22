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
import test_proprietes.TestProprietes;

@Entity
@Table(name = "recompense_film")
public class RecompenseFilm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_recompense")
	private int idRecompense = -1;

	@Column(name = "date_remise")
	// @Temporal(TemporalType.DATE)
	private int date;

	@Column(name = "description")
	// @Temporal(TemporalType.DATE)
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_film")
	private Film recompenseFilm;

	@ManyToOne
	@JoinColumn(name = "id_prix")
	private Prix recompensePrix;

	public RecompenseFilm() {
		super();
	}

	public RecompenseFilm(int date, Prix recompensePrix, Film recompenseFilm, String description) {

		TestProprietes.testerObjet(recompensePrix);
		TestProprietes.testerObjet(recompenseFilm);
		TestProprietes.testerPersistence(recompensePrix.getIdPrix());
		TestProprietes.testerPersistence(recompenseFilm.getIdFilm());
		this.date = date;
		this.recompensePrix = recompensePrix;
		this.recompenseFilm = recompenseFilm;
		this.description = description;
		Listes.add(recompensePrix.getLstRecompenseFilm(), this);
		Listes.add(recompenseFilm.getLstRecompenseFilm(), this);
		// recompensePrix.addLstRecompenseFilm(this);
		// recompenseFilm.addLstRecompenseFilm(this);
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

	public Film getRecompenseFilm() {
		return recompenseFilm;
	}

	public void setRecompenseFilm(Film recompenseFilm) {
		this.recompenseFilm = recompenseFilm;
	}

	public Prix getRecompensePrix() {
		return recompensePrix;
	}

	public void setRecompensePrix(Prix recompensePrix) {
		this.recompensePrix = recompensePrix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
