package beansSession;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import tools.Encrypt;
import utilisateurs.Gestionnaire;
import dao.DAOGestionnaire;
import dao.DAOGestionnaireJPA;

public class BeanSessionGestionnaire implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Gestionnaire g = new Gestionnaire();
	private String email=null;
	private String password=null;
	private String type = null;
	private String emailS=null;
	
	public Gestionnaire getG() {
		return g;
	}
	public void setG(Gestionnaire g) {
		this.g = g;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmailS() {
		return emailS;
	}
	public void setEmailS(String emailS) {
		this.emailS = emailS;
	}



	public String login(){
		
		try {
			DAOGestionnaire dao = DAOGestionnaireJPA.getInstance();
			
			g = dao.findEmail(email, Encrypt.sha512(password));
			System.out.println("\n\nID Gestionnaire:" + g.getIdUtilisateur());
			
			
		} catch (Exception e) {			
			//e.printStackTrace();
			nettoyerBean();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Login", "X(" ));
			
		}		
		return creerSession(g);		
		
	}
	
	public String close(){
		nettoyerBean();
		return "/homme";
	}
	
	private void nettoyerBean(){
		email = null;
		password = null;
		type = null;
		emailS=null;
		g= new Gestionnaire();
	}
	
	private String creerSession(Gestionnaire g){		
		String lien = "/home";
		if(g != null){			
			lien ="/gestionnaire";
		}
		return lien;
	}
	
}
