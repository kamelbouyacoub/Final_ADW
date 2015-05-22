package film;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import listes.Listes;
import test_proprietes.TestProprietes;
import utilisateurs.ClientPro;
import utilisateurs.Gestionnaire;

@Entity
@Table(name="modification_film")
public class ModificationFilm extends Modification{
	
	@Column(name="annee_sortie")
	//@Temporal(TemporalType.DATE)
	private int anneeSortieMod;
	
	@Column(name="cout")
	private double coutMod;
	
	@Column(name="note")
	private double noteMod;
	
	@ManyToOne	
	@JoinColumn(name="id_utilisateur")
	private ClientPro clientProFilm = null;
	
	@ManyToOne
	@JoinColumn(name="id_film")
	private Film film = null;
	
	@ManyToOne
	@JoinColumn(name="id_gestionnaire")	
	private Gestionnaire gestionnaireFilm = null;
	
	public ModificationFilm() {
		super();		
	}
	
	public ModificationFilm(Gestionnaire gestionnaire, ClientPro clientPro, Film film,String nomMod, String photoMod,
			String descriptionEtat, boolean etat, int anneeSortieMod, double coutMod, double noteMod ) {
		
		super(nomMod, photoMod, descriptionEtat, etat);
		
		TestProprietes.testerObjet(clientPro);
		TestProprietes.testerObjet(gestionnaire);
		TestProprietes.testerObjet(film);
		//TestProprietes.testerDate( anneeSortieMod);
		TestProprietes.testerPersistence(clientPro.getIdUtilisateur());
		TestProprietes.testerPersistence(gestionnaire.getIdUtilisateur());
		TestProprietes.testerPersistence(film.getIdFilm());
		this.clientProFilm = clientPro;
		this.gestionnaireFilm = gestionnaire;
		this.film = film;
		this.anneeSortieMod=anneeSortieMod;
		this.coutMod=coutMod;
		this.noteMod=noteMod;
		Listes.add(clientPro.getLstModificationFilm(), this);
		Listes.add(gestionnaire.getLstModificationFilm(), this);
		Listes.add(film.getLstModificationFilm(), this);
//		clientPro.addLstModificationFilm(this);
//		gestionnaire.addLstModificationFilm(this);
//		film.addLstModificationFilm(this);
//		
	}
	
	
	public int getAnneeSortieMod() {
		return anneeSortieMod;
	}
	
	public void setAnneeSortieMod(int anneeSortieMod) {
		this.anneeSortieMod = anneeSortieMod;
	}
	
	public double getCoutMod() {
		return coutMod;
	}
	
	public void setCoutMod(double coutMod) {
		this.coutMod = coutMod;
	}
	
	public double getNoteMod() {
		return noteMod;
	}
	
	public void setNoteMod(double noteMod) {
		this.noteMod = noteMod;
	}

	
	public ClientPro getClientProFilm() {
		return clientProFilm;
	}

	
	public void setClientProFilm(ClientPro clientPro) {
		this.clientProFilm = clientPro;
	}

	
	public Film getFilm() {
		return film;
	}

	
	public void setPersonneFilm(Film film) {
		this.film = film;
	}

	
	public Gestionnaire getGestionnaireFilm() {
		return gestionnaireFilm;
	}

	
	public void setGestionaire(Gestionnaire gestionnaireFilm) {
		this.gestionnaireFilm = gestionnaireFilm;
	}
	
	
}
