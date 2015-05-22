package beanRecherche;

import java.io.Serializable;

public class BeanRechercheFilm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String requeteFilm;
	
	public BeanRechercheFilm(){
		super();	
	}
	
	public String getRequeteFilm() {
		return requeteFilm;
	}

	public void setRequeteFilm(String requeteFilm) {
		this.requeteFilm = requeteFilm;
	}

	

	
}
