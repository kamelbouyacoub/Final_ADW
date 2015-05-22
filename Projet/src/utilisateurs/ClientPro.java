package utilisateurs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import film.ModificationFilm;
import film.ModificationPersonne;

@Entity
@Table(name="utilisateur_pro")
public class ClientPro extends Personne {   
    
	@OneToMany(mappedBy="clientProPersonne", cascade=CascadeType.ALL)	
	private List<ModificationPersonne> lstModificationPersonne = new ArrayList<ModificationPersonne>();
	
	@OneToMany(mappedBy="clientProFilm" ,cascade=CascadeType.ALL)	
	private List<ModificationFilm> lstModificationFilm = new ArrayList<ModificationFilm>();
	
	public ClientPro() {
		super();		
	}

	public ClientPro(String nom, String prenom, Date dateNaissance, String photo, String email,String pass, boolean etat ) {
		super(nom, prenom, dateNaissance, photo, email, pass, etat);				
	}

	public List<ModificationPersonne> getLstModificationPersonne() {
		return lstModificationPersonne;
	}

	public void setLstModificationPersonne(
			List<ModificationPersonne> lstModificationPersonne) {
		this.lstModificationPersonne = lstModificationPersonne;
	}

	public List<ModificationFilm> getLstModificationFilm() {
		return lstModificationFilm;
	}

	public void setLstModificationFilm(List<ModificationFilm> lstModificationFilm) {
		this.lstModificationFilm = lstModificationFilm;
	}	
	
		
//	// liste LstModificationPersonne
//	
//	public int sizeLstModificationPersonne() {
//		return lstModificationPersonne.size();
//	}
//
//	public boolean isEmptyLstModificationPersonne() {
//		return lstModificationPersonne.isEmpty();
//	}
//
//	public boolean addLstModificationPersonne(ModificationPersonne e) {
//		return lstModificationPersonne.add(e);
//	}
//
//	public boolean removeLstModificationPersonne(Object o) {
//		return lstModificationPersonne.remove(o);
//	}
//
//	public ModificationPersonne getLstModificationPersonne(int index) {
//		return lstModificationPersonne.get(index);
//	}
//	
//	public boolean containsLstModificationPersonne(Object o) {
//		return lstModificationPersonne.contains(o);
//	}
//	
//	//liste LstModificationFilm
//	
//	public int sizeLstModificationFilm() {
//		return lstModificationFilm.size();
//	}
//
//	public boolean isEmptyLstModificationFilm() {
//		return lstModificationFilm.isEmpty();
//	}
//
//	public boolean addLstModificationFilm(ModificationFilm e) {
//		return lstModificationFilm.add(e);
//	}
//
//	public boolean removeLstModificationFilm(Object o) {
//		return lstModificationFilm.remove(o);
//	}
//
//	public ModificationFilm getLstModificationFilm(int index) {
//		return lstModificationFilm.get(index);
//	}
//	
//	public boolean containsLstModificationFilm(Object o) {
//		return lstModificationFilm.contains(o);
//	}		
	
	
}
