package film;

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
@Table(name = "prix")
public class Prix {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id_prix")
	private int idPrix=-1;
	
	@Column(name = "nom_prix")
	private String nomPrix;
	
	@Column(name ="description")
	private String description;
	
	@Column(name ="etat")
	private boolean etat;

	//@Transient
	@OneToMany(mappedBy = "recompensePrix", cascade = CascadeType.ALL)
	private List<RecompenseFilm> lstRecompenseFilm = new ArrayList<RecompenseFilm>();	
	
	@OneToMany(mappedBy = "prixRecompense", cascade = CascadeType.ALL)
	private List<RecompensePersonneFilm> lstRecompencePersonne = new ArrayList<RecompensePersonneFilm>(); 
	
	
	public Prix(){
		super();
	}
	
	public Prix(String nomPrix, String description, boolean etat) {
		super();
		TestProprietes.testerChaine(nomPrix);
		this.nomPrix = nomPrix;
		this.description = description;
		this.etat = etat;
	}

	public int getIdPrix() {
		return idPrix;
	}

	public void setIdPrix(int idPrix) {
		this.idPrix = idPrix;
	}

	public String getNomPrix() {
		return nomPrix;
	}

	public void setNomPrix(String nomPrix) {
		this.nomPrix = nomPrix;
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

	public List<RecompenseFilm> getLstRecompenseFilm() {
		return lstRecompenseFilm;
	}

	public void setLstRecompenseFilm(List<RecompenseFilm> lstRecompenseFilm) {
		this.lstRecompenseFilm = lstRecompenseFilm;
	}

	public List<RecompensePersonneFilm> getLstRecompencePersonne() {
		return lstRecompencePersonne;
	}

	public void setLstRecompencePersonne(
			List<RecompensePersonneFilm> lstRecompencePersonne) {
		this.lstRecompencePersonne = lstRecompencePersonne;
	}

	
	
//	// liste ListRecompencePersonne
//	
//	public int sizelstRecompencePersonne() {
//		return lstRecompencePersonne.size();
//	}
//
//	public boolean isEmptyLstRecompencePersonne() {
//		return lstRecompencePersonne.isEmpty();
//	}
//
//	public boolean addLstRecompencePersonne(RecompensePersonneFilm e) {
//		return lstRecompencePersonne.add(e);
//	}
//
//	public boolean removeLstRecompencePersonne(Object o) {
//		return lstRecompencePersonne.remove(o);
//	}
//
//	public RecompensePersonneFilm getLstRecompencePersonne(int index) {
//		return lstRecompencePersonne.get(index);
//	}
//
//	public boolean containsLstRecompencePersonne(Object o) {
//		return lstRecompencePersonne.contains(o);
//	}
//
//	// liste ListRecompenseFilm
//
//	public int sizeLstRecompenseFilm() {
//		return lstRecompenseFilm.size();
//	}
//
//	public boolean isEmptyLstRecompenseFilm() {
//		return lstRecompenseFilm.isEmpty();
//	}
//
//	public boolean addLstRecompenseFilm(RecompenseFilm e) {
//		return lstRecompenseFilm.add(e);
//	}
//
//	public boolean removeLstRecompenseFilm(Object o) {
//		return lstRecompenseFilm.remove(o);
//	}
//
//	public RecompenseFilm getlstRecompenseFilm(int index) {
//		return lstRecompenseFilm.get(index);
//	}
//
//	public boolean containslstRecompenseFilm(Object o) {
//		return lstRecompenseFilm.contains(o);
//	}

}
