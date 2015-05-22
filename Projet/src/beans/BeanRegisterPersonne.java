package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.PersonneFilm;

public class BeanRegisterPersonne implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PersonneFilm personne = new PersonneFilm();
	private UploadedFile photo = null;
	private int cod = -1;
	
	
	public BeanRegisterPersonne() {}
	
	public UploadedFile getPhoto() {
		return photo;
	}

	public void setPhoto(UploadedFile photo) {
		this.photo = photo;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}	
	
	public PersonneFilm getPersonne() {
		return personne;
	}

	public void setPersonne(PersonneFilm personne) {
		this.personne = personne;
	}	
		
	public void save(){	
		
		try {
			
			DAOPersonneFilm dao = DAOPersonneFilmJPA.getInstance();	
			if(dao.findNom(personne.getNom(), personne.getPrenom()))
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Personne existe" ));
			else{	
				formater();
				dao.save(personne);
				nettoyer();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", ";)" + personne.getIdPersonne() ));
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	
	}
	
	public List<PersonneFilm>getPersonnes(){		
		try {
			return DAOPersonneFilmJPA.getInstance().loadAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}		
	}
	
	public void uploadPhoto() throws IOException{
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try	{
			
			if(this.photo.getSize()<=0){				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Ud. debe seleccionar un archivo de imagen \".png\""));
				return;
			}

			if(!this.photo.getFileName().endsWith(".png")){					
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo debe ser con extensión \".png\""));
				return;
			}

			if(this.photo.getSize()>2097152){				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo no puede ser más de 2mb"));
				return;
			}			
				
			String dossierPersonne="E:\\git\\projetFilms\\WebContent\\resources\\images\\personne";	
			
			outputStream=new FileOutputStream(new File(dossierPersonne +"\\"+ cod) +".png");
			inputStream=this.photo.getInputstream();			
			
			int read=0;
			byte[] bytes=new byte[1024];		
			
			while((read=inputStream.read(bytes))!=-1)				
				outputStream.write(bytes, 0, read);					
			
		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Avatar actualizado correctamente"));
		
		}catch(Exception ex){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
		}
		finally{
			if(inputStream!=null)			
				inputStream.close();			

			if(outputStream!=null)			
				outputStream.close();			
		}
	}
	
	public void mettreAJourCod(int cod){
		setCod(cod);		
	}
	
	private void formater(){
		personne.setNom(personne.getNom().toLowerCase());
		personne.setPrenom(personne.getPrenom().toLowerCase());
		personne.setEtat(true);
		personne.setPhoto("sans photo");
		personne.setPopularite(0);
	}
	
	private void nettoyer(){
		personne = new PersonneFilm();
	}
	

}
