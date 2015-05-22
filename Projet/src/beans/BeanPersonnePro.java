package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import utilisateurs.ClientPro;
import utilisateurs.Gestionnaire;
import beansSession.BeanSessionPro;
import dao.DAOFilmJPA;
import dao.DAOGestionnaireJPA;
import dao.DAOModificationFilm;
import dao.DAOModificationFilmJPA;
import dao.DAOModificationPersonne;
import dao.DAOModificationPersonneJPA;
import dao.DAOPersonneFilmJPA;
import film.Film;
import film.ModificationFilm;
import film.ModificationPersonne;
import film.PersonneFilm;

public class BeanPersonnePro implements Serializable {

	private static final long serialVersionUID = 1L;
	private PersonneFilm personneFilm = new PersonneFilm();
	private ClientPro utilisateur = new ClientPro();
	private Film film = new Film();
	private UploadedFile photo = null;
	
	
	public PersonneFilm getPersonneFilm() {
		return personneFilm;
	}

	public void setPersonneFilm(PersonneFilm personneFilm) {
		this.personneFilm = personneFilm;
	}	
	
	public ClientPro getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(ClientPro utilisateur) {		
		this.utilisateur = utilisateur;
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
	
	
	//pour trouver le client pro qui est en session
	public void findIdSession() {			
		try {
			HttpSession session  = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			BeanSessionPro b =(BeanSessionPro)session.getAttribute("beanSessionPro");
			utilisateur = b.getC();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	//ajouter un nouveau acteur dans modification
	public void saveActeur() {
		//gestionnaire generique
		Gestionnaire g = DAOGestionnaireJPA.getInstance().get(100);
		//acteur generique
		PersonneFilm p = DAOPersonneFilmJPA.getInstance().get(100);
		//donnes à sauver si'l le gestionnaire accepte
		ModificationPersonne mp = new ModificationPersonne(g, utilisateur, p, personneFilm.getNom(), personneFilm.getPrenom(), 
				personneFilm.getDateNaissance(), personneFilm.getPhoto(),
				personneFilm.getPopularite(), "save", true);
		DAOModificationPersonne dao = DAOModificationPersonneJPA.getInstance();	
		dao.save(mp);
		
		try {
			uploadPhoto( mp.getIdModification(), "personne_temp");
		} catch (IOException e) {			
			e.printStackTrace();
			dao.remove(mp);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("erreur photo, esseie encore une fois"));						
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("personneFilm ajouté"));
		nettoyerBean();
	}
	
	//ajouter un nouveau film dans modification film
	public void saveFilm(){
		//gestionnaire generique
		Gestionnaire g = DAOGestionnaireJPA.getInstance().get(100);
		//acteur generique
		Film f = DAOFilmJPA.getInstance().get(100);
		//donnes à sauver si'l le gestionnaire accepte
		
		ModificationFilm mp = new ModificationFilm(g, utilisateur, f, film.getNom(), film.getPhoto(),"save", 
				false, film.getAnneeSortie(), film.getCout(), 0 );
		DAOModificationFilm dao = DAOModificationFilmJPA.getInstance();	
		dao.save(mp);

		try {
			uploadPhoto(mp.getIdModification(), "film_temp");			
		} catch (IOException e) {			
			e.printStackTrace();
			dao.remove(mp);			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("erreur photo, esseie encore une fois"));						
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Film ajouté"));
		nettoyerBean();
		
	}	
	
	private void uploadPhoto(int nomPhoto, String dossier)throws IOException {
        
		InputStream inputStream=null;
        OutputStream outputStream=null;
        System.out.println("\n\n*********HOLA upload1*******");
        try {           
            
            //ServletContext servletContext=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        	String path= "E:\\workspace\\FINAL_ADW_1\\WebContent\\resources\\images\\"+ dossier;           
            
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

	private void nettoyerBean(){
		personneFilm = new PersonneFilm();
		utilisateur = new ClientPro();
		film = new Film();
		photo = null;
	}
}
