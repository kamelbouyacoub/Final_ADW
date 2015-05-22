package beanModification;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import utilisateurs.ClientPro;
import utilisateurs.Gestionnaire;
import beans.BeanFilmGeneral;
import beansSession.BeanSessionPro;
import dao.DAOGestionnaireJPA;
import dao.DAOModificationPersonneFilmFilmJPA;
import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.Film;
import film.ModificationPersonneFilmFilm;
import film.PersonneFilm;

public class BeanModificationPersonneFilmFilm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PersonneFilm personneFilm;
	boolean jouer, realiser, produit = false;
	private int idPersonneFilm = -1;
	
	public PersonneFilm getPersonneFilm() {
		return personneFilm;
	}
	public void setPersonneFilm(PersonneFilm personneFilm) {
		this.personneFilm = personneFilm;
	}
	public boolean isJouer() {
		return jouer;
	}
	public void setJouer(boolean jouer) {
		this.jouer = jouer;
	}
	public boolean isRealiser() {
		return realiser;
	}
	public void setRealiser(boolean realiser) {
		this.realiser = realiser;
	}
	public boolean isProduit() {
		return produit;
	}
	public void setProduit(boolean produit) {
		this.produit = produit;
	}
	public int getIdPersonneFilm() {
		return idPersonneFilm;
	}
	public void setIdPersonneFilm(int idPersonneFilm) {
		this.idPersonneFilm = idPersonneFilm;
	}
	
	public void modificationRole() {		
		
		System.out.println("\n\n\n");
		
			if (this.idPersonneFilm !=-1){
				
				try {
					int idU = findIdSession().getIdUtilisateur();
					if ( idU == -1) {
						FacesContext.getCurrentInstance().addMessage(null, 
								new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sans session","" ));
						return;
					}
											
					
					DAOPersonneFilm dao = DAOPersonneFilmJPA.getInstance();					
					this.personneFilm =  dao.get(this.idPersonneFilm);
					Gestionnaire g = DAOGestionnaireJPA.getInstance().get(100);					
					Film film = filmGeneral();					
					
					ModificationPersonneFilmFilm d = new ModificationPersonneFilmFilm(idU,
							g.getIdUtilisateur(), personneFilm.getIdPersonne(), this.jouer,
							this.produit, this.realiser, "UPDATE", true, film);
					DAOModificationPersonneFilmFilmJPA.getInstance().save(d);
					//this.detailPersonneFilm = null;			
					
					
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "Comuniquer avec l'administrateur","" ));
					e.printStackTrace();
				}
			}else{
							
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Choisir","" ));
			}
			netoyerBean();
	}
	
	private void netoyerBean() {
		this.jouer = false;
		this.produit = false;
		this.realiser = false;
		this.idPersonneFilm = -1;
		this.personneFilm = new PersonneFilm();
		
	}

	public ClientPro findIdSession() {
		ClientPro utilisateur;
		try {
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			BeanSessionPro b = (BeanSessionPro) session
					.getAttribute("beanSessionPro");
			utilisateur = b.getC();

		} catch (Exception e) {
			e.printStackTrace();
			utilisateur = new ClientPro();
		}
		return utilisateur;

	}

	public Film filmGeneral() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		BeanFilmGeneral b = (BeanFilmGeneral) session
				.getAttribute("beanFilmGeneral");
		return b.getFilm();
	}
	

}
