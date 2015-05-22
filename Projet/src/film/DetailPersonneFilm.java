package film;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import test_proprietes.TestProprietes;
import listes.Listes;

@Entity
@Table(name="detail_personne_film_film")
public class DetailPersonneFilm {
	
	@Id
	@Column(name="id_detail")
	private String idDetailPersonneFilm = null;
	
	@Column(name="produit")
	private boolean produit;
	
	@Column(name="joue")
	private boolean joue;
	
	@Column(name="realise")
	private boolean realise;	
	
	@ManyToOne
	@JoinColumn(name="id_personne_film")	
	private PersonneFilm personneFilm = null;
	
	@ManyToOne
	@JoinColumn(name="id_film")	
	private Film filmDetail = null;
	
	public DetailPersonneFilm() {
		super();
	}
		
	public DetailPersonneFilm(PersonneFilm personneFilm, Film filmDetail, boolean produit,
							  boolean joue, boolean realise) {
		super();
		TestProprietes.testerObjet(personneFilm);
		TestProprietes.testerObjet(filmDetail);
		TestProprietes.testerPersistence(personneFilm.getIdPersonne());
		TestProprietes.testerPersistence(filmDetail.getIdFilm());
		this.personneFilm = personneFilm;
		this.filmDetail = filmDetail;
		this.idDetailPersonneFilm = "" + personneFilm.getIdPersonne() +filmDetail.getIdFilm();
		this.produit = produit;
		this.joue = joue;
		this.realise = realise;
		Listes.add(personneFilm.getLstDetailPersonneFilm(), this);
		Listes.add(filmDetail.getLstDetailPersonneFilm(), this);
		//personneFilm.addLstDetailPersonneFilm(this);
		//filmDetail.addLstDetailPersonneFilm(this);
		
	}

	public String getIdDetailPersonneFilm() {
		return idDetailPersonneFilm;
	}
	
	public void setIdDetailPersonneFilm(String idDetailPersonneFilm){
		this.idDetailPersonneFilm = idDetailPersonneFilm;
	}
	
	public boolean isProduit() {
		return produit;
	}

	public void setProduit(boolean produire) {
		this.produit = produire;
	}

	public boolean isJoue() {
		return joue;
	}

	public void setJoue(boolean joue) {
		this.joue = joue;
	}

	public boolean isRealise() {
		return realise;
	}

	public void setRealise(boolean realise) {
		this.realise = realise;
	}

	public PersonneFilm getPersonneFilm() {
		return personneFilm;
	}

	public void setPersonneFilm(PersonneFilm personneFilm) {
		this.personneFilm = personneFilm;
	}

	public Film getFilmDetail() {
		return filmDetail;
	}

	public void setFilmDetail(Film filmDetail) {
		this.filmDetail = filmDetail;
	}	
	

}
