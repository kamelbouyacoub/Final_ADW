package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import utilisateurs.ClientPro;
import utilisateurs.Gestionnaire;
import beansSession.BeanSessionPro;
import dao.DAODetailPersonneFilm;
import dao.DAODetailPersonneFilmJPA;
import dao.DAOGestionnaireJPA;
import dao.DAOModificationPersonneFilmFilmJPA;
import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.DetailPersonneFilm;
import film.Film;
import film.ModificationPersonneFilmFilm;
import film.PersonneFilm;
import film.RecompenseFilm;

public class BeanPersonneFilm implements Serializable {

	private static final long serialVersionUID = 1L;

	private DetailPersonneFilm detailPersonneFilm;
	private List<RecompenseFilm> listRecompenseFilm;
	private List<DetailPersonneFilm> listDetailsPersonneFilm;
	private List<PersonneFilm> listPersonneFilm;

	public DetailPersonneFilm getDetailPersonneFilm() {
		return detailPersonneFilm;
	}

	public void setDetailPersonneFilm(DetailPersonneFilm detailPersonneFilm) {
		this.detailPersonneFilm = detailPersonneFilm;
	}

	public List<RecompenseFilm> getListRecompenseFilm() {
		return listRecompenseFilm;
	}

	public void setListRecompenseFilm(List<RecompenseFilm> listRecompenseFilm) {
		this.listRecompenseFilm = listRecompenseFilm;
	}

	public List<DetailPersonneFilm> getListDetailsPersonneFilm() {
		Film f = sessionFilm();
		return DAODetailPersonneFilmJPA.getInstance().getLstPersonneFilm(f);
	}

	public Film sessionFilm() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		BeanFilmGeneral RF = (BeanFilmGeneral) session
				.getAttribute("beanFilmGeneral");
		Film f = RF.getFilm();
		return f;
	}

	public void setListDetailsPersonneFilm(
			List<DetailPersonneFilm> listDetailsPersonneFilm) {
		this.listDetailsPersonneFilm = listDetailsPersonneFilm;
	}

	public List<DetailPersonneFilm> getListActeurFilm() {
		Film f = sessionFilm();
		return DAODetailPersonneFilmJPA.getInstance().getLstActeursFilm(f);
	}

	public List<DetailPersonneFilm> getListProducteurFilm() {
		Film f = sessionFilm();
		return DAODetailPersonneFilmJPA.getInstance().getLstProducteursFilm(f);
	}

	public List<DetailPersonneFilm> getListRealisateurFilm() {
		Film f = sessionFilm();
		return DAODetailPersonneFilmJPA.getInstance().getLstRealisateursFilm(f);
	}

	public String nomPersonneFilm(int id) {
		return DAOPersonneFilmJPA.getInstance().get(id).getNom() + " "
				+ DAOPersonneFilmJPA.getInstance().get(id).getPrenom();

	}

	public List<DetailPersonneFilm> getLstFilmPersone() {
		return DAODetailPersonneFilmJPA.getInstance().getLstFilmPersone(
				detailPersonneFilm.getPersonneFilm());

	}

	public String recupererPersonne(String id) {
		DAODetailPersonneFilm dao = DAODetailPersonneFilmJPA.getInstance();
		detailPersonneFilm = dao.get(id);		
		mettreAjourPopularite(detailPersonneFilm.getPersonneFilm());
		//detailPersonneFilm.getPersonneFilm().setPopularite(detailPersonneFilm.getPersonneFilm().getPopularite()+1);		
		return "visionPersonne";
	}

	public List<PersonneFilm> getListPersonneFilm() {
		return DAOPersonneFilmJPA.getInstance().loadAll();
	}

	public void setListPersonneFilm(List<PersonneFilm> listPersonneFilm) {
		this.listPersonneFilm = listPersonneFilm;
	}
	
	private void mettreAjourPopularite(PersonneFilm p){
		
		try {
			DAOPersonneFilm dao = DAOPersonneFilmJPA.getInstance();
			dao.popularite(p);
			detailPersonneFilm.getPersonneFilm().setPopularite(p.getPopularite());
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}

}

//private PersonneFilm personneFilm;
//boolean jouer, realiser, produit = false;
//private int idPersonneFilm = -1;



//public int getIdPersonneFilm() {
//	return idPersonneFilm;
//}
//
//public void setIdPersonneFilm(int idPersonneFilm) {
//	this.idPersonneFilm = idPersonneFilm;
//}


//public PersonneFilm getPersonneFilm() {
//return personneFilm;
//}
//
//public void setPersonneFilm(PersonneFilm personneFilm) {
//this.personneFilm = personneFilm;
//}	
//public boolean isJouer() {
//return jouer;
//}
//
//public void setJouer(boolean jouer) {
//this.jouer = jouer;
//}
//
//public boolean isRealiser() {
//return realiser;
//}
//
//public void setRealiser(boolean realiser) {
//this.realiser = realiser;
//}
//
//public boolean isProduit() {
//return produit;
//}
//
//public void setProduit(boolean produit) {
//this.produit = produit;
//}


//public void modificationRole() {		
//	
//System.out.println("\n\n\n");
//
//	if (this.idPersonneFilm !=-1){
//		
//		try {
//			int idU = findIdSession().getIdUtilisateur();
//			if ( idU == -1) {
//				FacesContext.getCurrentInstance().addMessage(null, 
//						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sans session","" ));
//				return;
//			}
//									
//			
//			DAOPersonneFilm dao = DAOPersonneFilmJPA.getInstance();					
//			this.personneFilm =  dao.get(this.idPersonneFilm);
//			Gestionnaire g = DAOGestionnaireJPA.getInstance().get(100);					
//			Film film = filmGeneral();					
//			
//			ModificationPersonneFilmFilm d = new ModificationPersonneFilmFilm(idU,
//					g.getIdUtilisateur(), personneFilm.getIdPersonne(), this.jouer,
//					this.produit, this.realiser, "UPDATE", true, film);
//			DAOModificationPersonneFilmFilmJPA.getInstance().save(d);
//			this.detailPersonneFilm = null;			
//			
//			
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null, 
//					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Comuniquer avec l'administrateur","" ));
//			e.printStackTrace();
//		}
//	}else{
//					
//		FacesContext.getCurrentInstance().addMessage(null, 
//				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Choisir","" ));
//	}
//	netoyerBean();
//}
//
//
//private void netoyerBean() {
//this.jouer = false;
//this.produit = false;
//this.realiser = false;
//this.idPersonneFilm = -1;
//this.personneFilm = new PersonneFilm();
//
//}
//
//public ClientPro findIdSession() {
//ClientPro utilisateur;
//try {
//	HttpSession session = (HttpSession) FacesContext
//			.getCurrentInstance().getExternalContext()
//			.getSession(false);
//	BeanSessionPro b = (BeanSessionPro) session
//			.getAttribute("beanSessionPro");
//	utilisateur = b.getC();
//
//} catch (Exception e) {
//	e.printStackTrace();
//	utilisateur = new ClientPro();
//}
//return utilisateur;
//
//}
//
//public Film filmGeneral() {
//
//HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
//		.getExternalContext().getSession(false);
//BeanFilmGeneral b = (BeanFilmGeneral) session
//		.getAttribute("beanFilmGeneral");
//return b.getFilm();
//}
//

