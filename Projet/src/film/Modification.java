package film;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import test_proprietes.TestProprietes;

@MappedSuperclass
public abstract class Modification {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_modification")
	private int idModification =-1;
	
	@Column(name="nom")
	private String nomMod;
	
	@Column(name="photo")
	private String photoMod;
	
	@Column(name="description_etat")
	private String descriptionEtat;
	
	@Column(name="etat")
	private boolean etat;
	
	public Modification() {
		super();		
	}

	public Modification( String nomMod, String photoMod, String descriptionEtat, boolean etat) {
		super();
		TestProprietes.testerChaine(nomMod);		
		this.nomMod = nomMod;
		this.photoMod = photoMod;
		this.descriptionEtat = descriptionEtat;
		this.etat = etat;
		
	}

	public int getIdModification() {
		return idModification;
	}
	
	public void setIdModification(int idModification) {
		this.idModification = idModification;
	}
		

	public String getNomMod() {
		return nomMod;
	}

	public void setNomMod(String nomMod) {
		this.nomMod = nomMod;
	}

	public String getPhotoMod() {
		return photoMod;
	}

	public void setPhotoMod(String photoMod) {
		this.photoMod = photoMod;
	}

	public String getDescriptionEtat() {
		return descriptionEtat;
	}

	public void setDescriptionEtat(String descriptionEtat) {
		this.descriptionEtat = descriptionEtat;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}	

}
