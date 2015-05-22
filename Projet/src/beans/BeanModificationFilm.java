package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import dao.DAOModificationFilm;
import dao.DAOModificationFilmJPA;
import dao.DAOModificationPersonne;
import dao.DAOModificationPersonneJPA;
import dao.DAOPersonneFilmJPA;
import film.Film;
import film.ModificationFilm;
import film.ModificationPersonne;
import film.PersonneFilm;

public class BeanModificationFilm implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModificationFilm modifFilm = new ModificationFilm();
	private List<ModificationFilm> listModifFilm;
	

	   public void ajoutEvent(ActionEvent actionEvent) {
		   modifFilm = new ModificationFilm();
	   }
	    
	    public void deletMessage(ActionEvent actionEvent){
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Modification Supprimer supprimé"));
	    }
	 
	    public void editEvent(int id) {
	    	DAOModificationFilm dao =DAOModificationFilmJPA.getInstance();
	    	modifFilm = dao.get(id);
	 
	    }
	 
	    public void edition(ActionEvent actionEvent) {
	    	DAOModificationFilm dao =DAOModificationFilmJPA.getInstance();
	    	dao.save(modifFilm);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Modification mise à jour"));
	    }
	 
	    public void ajoutFilm(ActionEvent actionEvent) {
	    	System.out.println("Ajouter Modification" + modifFilm);
	    	DAOModificationFilm dao =DAOModificationFilmJPA.getInstance();
	        dao.save(modifFilm);
	        System.out.println("Modification sauvgarder" + modifFilm);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Modification ajouté"));
	        modifFilm = new ModificationFilm();
	    }
	 
	    public void delet(ModificationFilm modifFilm) {
	    	DAOModificationFilm dao =DAOModificationFilmJPA.getInstance();
	    	System.err.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	    	System.out.println("\n"+modifFilm);
	    	System.err.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	        dao.remove(modifFilm);	        
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Modification supprimé"));
	 
	    }    
	 
	   
	    public ModificationFilm getModfiFilm() {
	        return modifFilm;
	    }
	 
	    public void setModificationFilm(ModificationFilm modifFilm) {
	        this.modifFilm = modifFilm;
	    }
	    
	
	    public void setlistModifFilm(List<ModificationFilm> listModifFilm) {
	        this.listModifFilm = listModifFilm;
	    }
	 
		public List<ModificationFilm>getlistModifFilm(){		
			try {
				this.listModifFilm = DAOModificationFilmJPA.getInstance().loadAll();
				return this.listModifFilm;
			} catch (Exception e) {
				e.printStackTrace();
				return null;			
			}		
		}	
		
		
		public void accepter(ModificationFilm modFilm){
			Film f = modFilm.getFilm();
			f.setAnneeSortie(modFilm.getAnneeSortieMod());
			f.setNom(modFilm.getNomMod());
			f.setCout(modFilm.getCoutMod());
			f.setNote(modFilm.getNoteMod());
			f.setEtat(false);
			f.setPhoto(modFilm.getPhotoMod());
			modFilm.setDescriptionEtat(true);
			rejeter(modFilm);
		}
		
		public void rejeter(ModificationFilm modFilm){
			DAOModificationFilm dao =DAOModificationFilmJPA.getInstance();
			modFilm.setEtat(false);
			modFilm.setDescriptionEtat(false);
			dao.save(modFilm);
		}
		
}
