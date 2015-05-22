package film;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import listes.Listes;
import test_proprietes.TestProprietes;
import utilisateurs.ClientPro;
import utilisateurs.Gestionnaire;

@Entity
@Table(name="modification_personne_film")
public class ModificationPersonne extends Modification {
	
	@Column(name="prenom")
	private String prenomMod;
	
	@Column(name="date_naissance")
	@Temporal(TemporalType.DATE)
	private Date dateNaissanceMod;
	
	@Column(name="popularite")
	private int populariteMod;
	
	@ManyToOne
	@JoinColumn(name="id_utilisateur")	
	private ClientPro clientProPersonne = null;
	
	
	@ManyToOne
	@JoinColumn(name="id_personne_film")	
	private PersonneFilm personneFilm = null;
	

	@ManyToOne
	@JoinColumn(name="id_gestionnaire")	
	private Gestionnaire gestionnairePersonne = null;
	
	public ModificationPersonne() {
		super();		
	}
	
	public ModificationPersonne(Gestionnaire gestionnairePersonne, ClientPro clientProPersonne, PersonneFilm personneFilm,
			String nomMod, String prenomMod, Date dateNaissanceMod, String photoMod,int populariteMod,String descriptionEtat, boolean etat ) {
		
		super(nomMod, photoMod, descriptionEtat, etat);
		
		TestProprietes.testerObjet(clientProPersonne);
		TestProprietes.testerObjet(gestionnairePersonne);
		TestProprietes.testerObjet(personneFilm);
		TestProprietes.testerDate(dateNaissanceMod);
		TestProprietes.testerChaine(prenomMod);
		TestProprietes.testerPersistence(clientProPersonne.getIdUtilisateur());
		TestProprietes.testerPersistence(gestionnairePersonne.getIdUtilisateur());
		TestProprietes.testerPersistence(personneFilm.getIdPersonne());		
		this.gestionnairePersonne=gestionnairePersonne;
		this.clientProPersonne=clientProPersonne;
		this.personneFilm=personneFilm;
		this.prenomMod=prenomMod;
		this.dateNaissanceMod=dateNaissanceMod;
		this.populariteMod=populariteMod;
		Listes.add(clientProPersonne.getLstModificationPersonne(), this);
		Listes.add(gestionnairePersonne.getLstModificationPersonne(), this);
		Listes.add(personneFilm.getLstModificationPersonne(), this);
//		clientProPersonne.addLstModificationPersonne(this);
//		gestionnairePersonne.addLstModificationPersonne(this);
//		personneFilm.addLstModificationPersonne(this);
		
	}


	public String getPrenomMod() {
		return prenomMod;
	}

	public void setPrenomMod(String prenomMod) {
		this.prenomMod = prenomMod;
	}

	public Date getDateNaissanceMod() {
		return dateNaissanceMod;
	}

	public void setDateNaissanceMod(Date dateNaissanceMod) {
		this.dateNaissanceMod = dateNaissanceMod;
	}

	public int getPopulariteMod() {
		return populariteMod;
	}

	public void setPopulariteMod(int populariteMod) {
		this.populariteMod = populariteMod;
	}

	
	public ClientPro getClientProPersonne() {
		return clientProPersonne;
	}

	
	public void setClientProPersonne(ClientPro clientProPersonne) {
		this.clientProPersonne = clientProPersonne;
	}

	
	public PersonneFilm getPersonneFilm() {
		return personneFilm;
	}

	
	public void setPersonneFilm(PersonneFilm personneFilm) {
		this.personneFilm = personneFilm;
	}

	
	public Gestionnaire getGestionnairePersonne() {
		return gestionnairePersonne;
	}

	
	public void setGestionairePersonne(Gestionnaire gestionairePersonne) {
		this.gestionnairePersonne = gestionairePersonne;
	}
	
		
}
