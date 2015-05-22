package beans;


import java.io.Serializable;
import java.util.List;




import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;





import javax.faces.event.ActionEvent;

import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.PersonneFilm;

public class BeanPersonne implements Serializable{

	private static final long serialVersionUID = 1L;
	private PersonneFilm personneFilm = new PersonneFilm();
	private List<PersonneFilm> lstPersonnes;;	 
	 
	    public BeanPersonne() {  }
	 
	    public void ajoutEvent(ActionEvent actionEvent) {
	        personneFilm = new PersonneFilm();
	 
	    }
	    
	    public void deletMessage(ActionEvent actionEvent){
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("personneFilm supprimé"));
	    }
	 
	    public void editEvent(int id) {
	    	DAOPersonneFilm dao =DAOPersonneFilmJPA.getInstance();
	        System.out.print(id);	        
	        dao = DAOPersonneFilmJPA.getInstance();
	        personneFilm = dao.get(id);
	 
	    }
	 
	    public void edition(ActionEvent actionEvent) {
	    	DAOPersonneFilm dao =DAOPersonneFilmJPA.getInstance();
	        dao = DAOPersonneFilmJPA.getInstance();
	    	dao.save(personneFilm);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("personneFilm mise à jour"));
	    }
	 
	    public void ajoutp(ActionEvent actionEvent) {
	    	DAOPersonneFilm dao =DAOPersonneFilmJPA.getInstance();
	        dao = DAOPersonneFilmJPA.getInstance();
	        dao.save(personneFilm);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("personneFilm ajouté"));
	        personneFilm = new PersonneFilm();
	    }
	 
	    public void delet(PersonneFilm personneFilm) {
	    	DAOPersonneFilm dao =DAOPersonneFilmJPA.getInstance();
	        dao = DAOPersonneFilmJPA.getInstance();
	        dao.remove(personneFilm);
	        
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("personneFilm supprimé"));
	 
	    }    
	 
	   
	    public PersonneFilm getPersonne() {
	        return personneFilm;
	    }
	 
	    public void setPersonne(PersonneFilm personneFilm) {
	        this.personneFilm = personneFilm;
	    }
	    
	
	    public void setLstPersonnes(List<PersonneFilm> listPersonne) {
	        this.lstPersonnes = listPersonne ;
	    }
	 
		public List<PersonneFilm>getLstPersonnes(){		
			try {
				this.lstPersonnes = DAOPersonneFilmJPA.getInstance().loadAll();
				return this.lstPersonnes;
			} catch (Exception e) {
				e.printStackTrace();
				return null;			
			}		
		}	
	
}
