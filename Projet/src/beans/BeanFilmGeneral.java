package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import beanRecherche.BeanRechercheFilm;
import beansSession.BeanSessionPro;
import dao.DAODetailPersonneFilmJPA;
import dao.DAOFilm;
import dao.DAOFilmJPA;
import dao.DAONoteFilmJPA;
import dao.DAORecompenseFilmJPA;
import film.DetailPersonneFilm;
import film.Film;
import film.NoteFilm;
import film.RecompenseFilm;



public class BeanFilmGeneral implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Film film;
	private List<Film> lstFilms;
	private int note; 
	private double moyenne;
	
	public BeanFilmGeneral() {super();}

	public Film getFilm() {
		return film;
	}

	public void setFilms(Film film) {
		this.film = film;
	}
	
	
	
	public List<Film>getLstFilms(){	
		try {
			
			
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			BeanRechercheFilm RF = (BeanRechercheFilm)session.getAttribute("beanRechercheFilm");
			String s = RF.getRequeteFilm();
			
			if(s == null){
				this.lstFilms = DAOFilmJPA.getInstance().loadAll();
			}else {
				this.lstFilms = DAOFilmJPA.getInstance().loadSpecificFilm(s);	
			
			}
			return this.lstFilms;
		} catch (Exception e) {
			e.printStackTrace();
			return null;			
		}		
	}
	
	public String choisirFilmEvent(int id) {
		
//		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//		BeanSessionPro p = (BeanSessionPro)session.getAttribute("beanSessionPro");
//		int i = p.getC().getIdUtilisateur();
//		
		
		DAOFilm dao =DAOFilmJPA.getInstance();
		System.out.print(id);	        
		film = dao.get(id);
		return "success";
		
		
		
	}
	
		
	public String typeSession(String type){
		
		System.out.println("\n\ntypeSession:" + type);		
		if(type=="pro") return "/p_pro/detailFilm";
		return "/index";
	}
	
	public List<DetailPersonneFilm> getPersonneDansFilm(){
		try {
			return  DAODetailPersonneFilmJPA.getInstance().getLstPersonneFilm(film);
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<RecompenseFilm> getLstRecompenseFilm(){
		try {
			return DAORecompenseFilmJPA.getInstance().getRecompenseFilm(film);
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
	}

	
	public double getMoyenne() {
		moyenne =DAONoteFilmJPA.getInstance().getMoyenne(film.getIdFilm());; 
		return moyenne;
	}
	
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}
	
	
	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}
	
	public void enregistrer(){
		NoteFilm n = new NoteFilm(note, film);
		DAONoteFilmJPA.getInstance().save(n);
		getMoyenne();
	}
	
}
