package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import dao.DAODetailPersonneFilmJPA;
import dao.DAOFilm;
import dao.DAOFilmJPA;
import dao.DAOModificationPersonneFilmFilm;
import dao.DAOModificationPersonneFilmFilmJPA;
import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.DetailPersonneFilm;
import film.Film;
import film.ModificationPersonneFilmFilm;
import film.PersonneFilm;

public class BeanModificationActeurFilm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ModificationPersonneFilmFilm modPersfilm = new ModificationPersonneFilmFilm();
	private List<ModificationPersonneFilmFilm> lstModif;
	
	public BeanModificationActeurFilm() {super();}

	
	   public void ajoutEvent(ActionEvent actionEvent) {
		   modPersfilm = new ModificationPersonneFilmFilm();
	 
	    }
	    
	    public void deletMessage(ActionEvent actionEvent){
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("ModificationPersonneFilmFilm supprimé"));
	    }
	 
	    public void editEvent(int id) {
	    	DAOModificationPersonneFilmFilm dao =DAOModificationPersonneFilmFilmJPA.getInstance();
	    	modPersfilm = dao.get(id);
	 
	    }
	 
	    public void edition(ActionEvent actionEvent) {
	    	DAOModificationPersonneFilmFilm dao =DAOModificationPersonneFilmFilmJPA.getInstance();
	    	dao.save(modPersfilm);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("modPersfilm mise à jour"));
	    }
	 
	    public void ajoutFilm(ActionEvent actionEvent) {
	    	DAOModificationPersonneFilmFilm dao =DAOModificationPersonneFilmFilmJPA.getInstance();
	        dao.save(modPersfilm);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("film ajouté"));
	        modPersfilm = new ModificationPersonneFilmFilm();
	    }
	 
	    public void delet(Film film) {
	    	DAOModificationPersonneFilmFilm dao =DAOModificationPersonneFilmFilmJPA.getInstance();
	        dao.remove(modPersfilm);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("modPersfilm supprimé"));
	 
	    }    
	 
	   
	    public ModificationPersonneFilmFilm getModPersFilm() {
	        return modPersfilm;
	    }
	 
	    public void setModPersFilm(ModificationPersonneFilmFilm modPersfilm) {
	        this.modPersfilm = modPersfilm;
	    }
	    
	
	    public void setLstModif(List<ModificationPersonneFilmFilm> lstModif) {
	        this.lstModif = lstModif;
	    }
	 
		public List<ModificationPersonneFilmFilm> getLstModif(){		
			try {
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				this.lstModif = DAOModificationPersonneFilmFilmJPA.getInstance().loadAll();
				System.out.println("\n" + lstModif.size());
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				return this.lstModif;
			} catch (Exception e) {
				e.printStackTrace();
				return null;			
			}		
		}	

		public PersonneFilm acteur(int id){
			DAOPersonneFilm dao = DAOPersonneFilmJPA.getInstance();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n "+modPersfilm.getIdPersonneFilm()+"\n\n\n\n\n\n\n\n\n\n\n");
			return dao.get(id);
			
		}
		
		public void accepter(ModificationPersonneFilmFilm detailPersonne){
			PersonneFilm p = acteur(detailPersonne.getIdPersonneFilm());
			Film f = detailPersonne.getModFilmPersonne();
			DetailPersonneFilm d = new DetailPersonneFilm(p, f, detailPersonne.isProduit(), detailPersonne.isJouer(), detailPersonne.isRealiser());
			DAODetailPersonneFilmJPA.getInstance().save(d);
		}
		
}
