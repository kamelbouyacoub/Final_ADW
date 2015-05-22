package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import dao.DAOFilm;
import dao.DAOFilmJPA;
import film.Film;


public class BeanFilms implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Film film = new Film();
	private List<Film> lstFilms;
	
	
	
	public BeanFilms() {super();}

	
	   public void ajoutEvent(ActionEvent actionEvent) {
		   film = new Film();
	 
	    }
	    
	    public void deletMessage(ActionEvent actionEvent){
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Film supprimé"));
	    }
	 
	    public void editEvent(int id) {
	    	DAOFilm dao =DAOFilmJPA.getInstance();
	        System.out.print(id);	        
	        dao = DAOFilmJPA.getInstance();
	        film = dao.get(id);
	 
	    }
	 
	    public void edition(ActionEvent actionEvent) {
	    	DAOFilm dao =DAOFilmJPA.getInstance();
	    	dao.save(film);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Film mise à jour"));
	    }
	 
	    public void ajoutFilm(ActionEvent actionEvent) {
	    	System.out.println("Ajouter Film" + film);
	    	DAOFilm dao =DAOFilmJPA.getInstance();
	        dao.save(film);
	        System.out.println("Film sauvgarder" + film);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("film ajouté"));
	        film = new Film();
	    }
	 
	    public void delet(Film film) {
	    	DAOFilm dao =DAOFilmJPA.getInstance();
	        dao.remove(film);
	        
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Film supprimé"));
	 
	    }    
	 
	   
	    public Film getFilm() {	    	
	        return film;
	    }
	 
	    public void setFilm(Film film) {
	        this.film = film;
	    }
	    
	
	    public void setlstFilms(List<Film> listFilms) {
	        this.lstFilms = listFilms;
	    }
	 
		public List<Film>getlstFilms(){		
			try {
				this.lstFilms = DAOFilmJPA.getInstance().loadAll();
				return this.lstFilms;
			} catch (Exception e) {
				e.printStackTrace();
				return null;			
			}		
		}
		
		

}
