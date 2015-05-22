package film;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import test_proprietes.TestProprietes;

@Entity
@Table(name="film")
public class Film implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_film")
	private int idFilm = -1;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "annee_sortie")
	//@Temporal(TemporalType.DATE)
	private int anneeSortie;
	
	@Column(name = "cout")	
	private double cout;
	
	@Column(name = "photo")
	private String photo = "sans photo";
	
	@Column(name = "note")
	private double note = 0;
	
	@Column(name = "etat")
	private boolean etat;
	
	@OneToMany(mappedBy="film", cascade=CascadeType.ALL)
	private List<ModificationFilm> lstModificationFilm = new ArrayList<ModificationFilm>();
		
	@OneToMany(mappedBy="filmDetail", cascade=CascadeType.ALL)
	private List<DetailPersonneFilm> lstDetailPersonneFilm = new ArrayList<DetailPersonneFilm>();	
	
	@OneToMany(mappedBy="filmRecompense", cascade=CascadeType.ALL)
	private List<RecompensePersonneFilm> lstRecompensePersonneFilm=new ArrayList<RecompensePersonneFilm>();	
	
	@OneToMany(mappedBy="recompenseFilm", cascade=CascadeType.ALL)
	private List<RecompenseFilm> lstRecompenseFilm =new ArrayList<RecompenseFilm>();;
	
	@OneToMany(mappedBy="modFilmPersonne", cascade=CascadeType.ALL)
	private List<ModificationPersonneFilmFilm> lstModificationPersonneFilmFilm =new ArrayList<ModificationPersonneFilmFilm>();;
	
	@OneToMany(mappedBy="modRecFilm", cascade=CascadeType.ALL)
	private List<ModificationRecompenseFilm> lstModificationRecompenseFilm =new ArrayList<ModificationRecompenseFilm>();;
	
	@OneToMany(mappedBy="filmNote", cascade=CascadeType.ALL)
	private List<NoteFilm> lstNoteFilm = new ArrayList<NoteFilm>();

	
	public Film() {
		super();		
	}

	public Film(String nom, int anneeSortie, double cout, String photo, double note, boolean etat) {
		super();
		TestProprietes.testerChaine(nom);
		this.nom = nom;
		this.anneeSortie = anneeSortie;
		this.cout = cout;
		this.photo = photo;
		this.note = note;
		this.etat = etat;
	}

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm){
		this.idFilm = idFilm;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAnneeSortie() {
		return anneeSortie;
	}

	public void setAnneeSortie(int anneeSortie) {
		this.anneeSortie = anneeSortie;
	}

	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public List<ModificationFilm> getLstModificationFilm() {
		return lstModificationFilm;
	}

	public void setLstModificationFilm(List<ModificationFilm> lstModificationFilm) {
		this.lstModificationFilm = lstModificationFilm;
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

	public List<RecompenseFilm> getLstRecompenseFilm() {
		return lstRecompenseFilm;
	}

	public void setLstRecompenseFilm(List<RecompenseFilm> lstRecompenseFilm) {
		this.lstRecompenseFilm = lstRecompenseFilm;
	}

	public List<ModificationPersonneFilmFilm> getLstModificationPersonneFilmFilm() {
		return lstModificationPersonneFilmFilm;
	}

	public void setLstModificationPersonneFilmFilm(
			List<ModificationPersonneFilmFilm> lstModificationPersonneFilmFilm) {
		this.lstModificationPersonneFilmFilm = lstModificationPersonneFilmFilm;
	}
	

	public List<ModificationRecompenseFilm> getLstModificationRecompenseFilm() {
		return lstModificationRecompenseFilm;
	}

	public void setLstModificationRecompenseFilm(
			List<ModificationRecompenseFilm> lstModificationRecompenseFilm) {
		this.lstModificationRecompenseFilm = lstModificationRecompenseFilm;
	}

	public List<NoteFilm> getLstNoteFilm() {
		return lstNoteFilm;
	}

	public void setLstNoteFilm(List<NoteFilm> lstNoteFilm) {
		this.lstNoteFilm = lstNoteFilm;
	}

	@Override
	public String toString() {
		return "Film [idFilm=" + idFilm + ", nom=" + nom + ", anneeSortie="
				+ anneeSortie + ", cout=" + cout + ", photo=" + photo
				+ ", note=" + note + ", etat=" + etat + "]";
	}
	
	
	
	
}
