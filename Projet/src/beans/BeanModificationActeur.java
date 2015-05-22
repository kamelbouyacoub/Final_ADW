package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import utilisateurs.ClientPro;
import dao.DAODetailPersonneFilmJPA;
import dao.DAOModificationFilm;
import dao.DAOModificationFilmJPA;
import dao.DAOModificationPersonne;
import dao.DAOModificationPersonneJPA;
import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.DetailPersonneFilm;
import film.Film;
import film.ModificationFilm;
import film.ModificationPersonne;
import film.ModificationPersonneFilmFilm;
import film.PersonneFilm;

public class BeanModificationActeur implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private ModificationPersonne modifPersonneFilm = new ModificationPersonne();
	private List<ModificationPersonne> listModificationPersonneFilm;
	

	   public void ajoutEvent(ActionEvent actionEvent) {
		   modifPersonneFilm = new ModificationPersonne();
	   }
	    
	    public void deletMessage(ActionEvent actionEvent){
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("PersonneFilm Supprimer supprimé"));
	    }
	 
	    public void editEvent(int id) {
	    	DAOModificationPersonne dao =DAOModificationPersonneJPA.getInstance();
	    	modifPersonneFilm = dao.get(id);
	 
	    }
	 
	    public void edition(ActionEvent actionEvent) {
	    	DAOModificationPersonne dao =DAOModificationPersonneJPA.getInstance();
	    	dao.save(modifPersonneFilm);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("modifPersonneFilm mise à jour"));
	    }
	 
	    public void ajoutFilm(ActionEvent actionEvent) {
	    	System.out.println("Ajouter modifPersonneFilm" + modifPersonneFilm);
	    	DAOModificationPersonne dao =DAOModificationPersonneJPA.getInstance();
	        dao.save(modifPersonneFilm);
	        System.out.println("personneFilm sauvgarder" + modifPersonneFilm);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("personneFilm ajouté"));
	        modifPersonneFilm = new ModificationPersonne();
	    }
	 
	    public void delet(ModificationFilm modifFilm) {
	    	DAOModificationPersonne dao =DAOModificationPersonneJPA.getInstance();

	    	dao.remove(modifPersonneFilm);	        
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("modifPersonneFilm supprimé"));
	 
	    }    
	 
	   
	    public ModificationPersonne getModificationPersonne() {
	        return modifPersonneFilm;
	    }
	 
	    public void setModificationPersonne(ModificationPersonne modifPersonneFilm) {
	        this.modifPersonneFilm = modifPersonneFilm;
	    }
	    
	
	    public void setListModificationPersonneFilm(List<ModificationPersonne> listModificationPersonneFilm) {
	        this.listModificationPersonneFilm = listModificationPersonneFilm;
	    }
	 
		public List<ModificationPersonne>getListModificationPersonneFilm(){		
			try {
				this.listModificationPersonneFilm = DAOModificationPersonneJPA.getInstance().loadAll();
				return this.listModificationPersonneFilm;
			} catch (Exception e) {
				e.printStackTrace();
				return null;			
			}		
		}	
		

		public void accepter(ModificationPersonne modifPersonne){
			PersonneFilm p = modifPersonne.getPersonneFilm();
			p.setNom(modifPersonne.getNomMod());
			p.setPrenom(modifPersonne.getPrenomMod());
			p.setDateNaissance(modifPersonne.getDateNaissanceMod());
			p.setPhoto(modifPersonne.getPhotoMod());
			p.setPopularite(modifPersonne.getPopulariteMod());
						p.setEtat(false);
			DAOPersonneFilmJPA.getInstance().save(p);
			modifPersonne.setDescriptionEtat(true);
			rejeter(modifPersonne);
		}
		
		public void rejeter(ModificationPersonne modifPersonne){
			DAOModificationPersonne dao =DAOModificationPersonneJPA.getInstance();
			modifPersonne.setEtat(false);
			modifPersonne.setDescriptionEtat(false);
			dao.save(modifPersonne);
		}
		
		
}
