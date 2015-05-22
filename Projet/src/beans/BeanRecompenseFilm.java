package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.DAORecompenseFilmJPA;
import dao.DAORecompensePersonneFilmJPA;
import film.DetailPersonneFilm;
import film.Film;
import film.RecompenseFilm;
import film.RecompensePersonneFilm;

public class BeanRecompenseFilm implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private RecompenseFilm recompenseFilm;
	private RecompensePersonneFilm recompensePersonneFilm;
	private List<RecompenseFilm> recompenseFilms;
	private List<RecompensePersonneFilm> recompensePersonnesFilms;

	
	public BeanRecompenseFilm() {
		super();
	}

	public RecompenseFilm getRecompenseFilm() {
		return recompenseFilm;
	}

	public void setRecompenseFilm(RecompenseFilm recompenseFilm) {
		this.recompenseFilm = recompenseFilm;
	}


	public RecompensePersonneFilm getRecompensePersonneFilm() {
		return recompensePersonneFilm;
	}

	public void setRecompensePersonneFilm(
			RecompensePersonneFilm recompensePersonneFilm) {
		this.recompensePersonneFilm = recompensePersonneFilm;
	}
	
	public List<RecompenseFilm> getRecompenseFilms() {
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		BeanFilmGeneral RF = (BeanFilmGeneral)session.getAttribute("beanFilmGeneral");
		Film f = RF.getFilm();
		recompenseFilms =DAORecompenseFilmJPA.getInstance().getRecompenseFilm(f);
		return recompenseFilms;
	}

	public void setRecompenseFilms(List<RecompenseFilm> recompenseFilms) {
		this.recompenseFilms = recompenseFilms;
	}	

	public List<RecompensePersonneFilm> getRecompensePersonnesFilms() {
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		BeanPersonneFilm RF = (BeanPersonneFilm)session.getAttribute("beanPersonneFilm");
		DetailPersonneFilm f = RF.getDetailPersonneFilm();
		recompensePersonnesFilms = DAORecompensePersonneFilmJPA.getInstance().getRecompensePersonneFilm(f);
		return recompensePersonnesFilms;
	}

	public void setRecompensePersonnesFilms(
			List<RecompensePersonneFilm> recompensePersonnesFilms) {
		this.recompensePersonnesFilms = recompensePersonnesFilms;
	}
	
}
