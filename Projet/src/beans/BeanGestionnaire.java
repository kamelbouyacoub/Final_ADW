package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import dao.DAOFilm;
import dao.DAOFilmJPA;
import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.Film;
import film.PersonneFilm;

public class BeanGestionnaire implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PersonneFilm personneFilm = new PersonneFilm();
	private Film film = new Film();
	private UploadedFile photo;
	
	public PersonneFilm getPersonneFilm() {
		return personneFilm;
	}
	public void setPersonneFilm(PersonneFilm personneFilm) {
		this.personneFilm = personneFilm;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public UploadedFile getPhoto() {
		return photo;
	}
	public void setPhoto(UploadedFile photo) {
		this.photo = photo;
	}	
	
	//ajouter un nouveau acteur dans personneFilm
	public void saveActeur() {
		PersonneFilm a = new PersonneFilm(personneFilm.getNom(), personneFilm.getPrenom(), 
				personneFilm.getDateNaissance(), personneFilm.getPhoto(), personneFilm.getPopularite(), true);
		DAOPersonneFilm dao = DAOPersonneFilmJPA.getInstance();	
		dao.save(a);

		try {
			uploadPhoto( a.getIdPersonne(), "personne");
		} catch (IOException e) {			
			e.printStackTrace();
			dao.remove(a);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("erreur photo, esseie encore une fois"));						
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Acteur ajouté"));
		nettoyerBean();
	}
	
	//ajouter un nouveau film dans modification film
	public void saveFilm(){
		Film f = new Film(film.getNom(), film.getAnneeSortie(), film.getCout(), film.getPhoto(),film.getNote() , true);
		DAOFilm dao = DAOFilmJPA.getInstance();	
		dao.save(f);

		try {
			uploadPhoto(f.getIdFilm(), "film");
		} catch (IOException e) {			
			e.printStackTrace();
			dao.remove(f);			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("erreur photo, esseie encore une fois"));						
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Film ajouté"));		
		nettoyerBean();
	}

	private void validerPhoto(){
		if(photo.getSize()<=0) {
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Ud. debe seleccionar un archivo de imagen \".png\""));
			return;
		}

		if(!photo.getFileName().endsWith(".png")){
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo debe ser con extensión \".png\""));
			return;
		}

		if(photo.getSize()>2097152){
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo no puede ser más de 2mb"));
			return;
		}
	}

	private void uploadPhoto(int nomPhoto, String dossier)throws IOException {

		InputStream inputStream=null;
		OutputStream outputStream=null;
		System.out.println("\n\n*********HOLA upload1*******");
		try {           

			//ServletContext servletContext=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
			String path= "E:\\workspace\\films_adw\\WebContent\\resources\\images\\" + dossier;           

			outputStream=new FileOutputStream(new File(path +"\\" + nomPhoto + ".png"));
			inputStream=photo.getInputstream();

			int read=0;
			byte[] bytes=new byte[1024];

			while((read=inputStream.read(bytes))!=-1)        	
				outputStream.write(bytes, 0, read);


			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "photo actualizado correctamente"));

		}catch(Exception ex){
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
		}finally{
			if(inputStream!=null)            
				inputStream.close();            

			if(outputStream!=null)            
				outputStream.close();

		}
	}

	private void nettoyerBean(){
		personneFilm = new PersonneFilm();
		film = new Film();
		photo = null;
	}

}
