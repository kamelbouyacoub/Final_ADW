package beansSession;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import tools.Encrypt;
import utilisateurs.ClientPro;
import dao.DAOClientPro;
import dao.DAOClientProJPA;

public class BeanSessionPro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ClientPro c = new ClientPro();
	private String email=null;
	private String password=null;
	private String type = null;
	private String emailS=null;
	private String passwordS=null;
	
	
	public BeanSessionPro() {		
	}	
	
	
	public ClientPro getC() {
		return c;
	}

	public void setC(ClientPro c) {
		this.c = c;
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

	public String getPasswordS() {
		return passwordS;
	}

	public void setPasswordS(String passwordS) {
		this.passwordS = passwordS;
	}
	
	public String login(){
		
		try {
			DAOClientPro dao = DAOClientProJPA.getInstance();
			c = dao.findEmail(email,Encrypt.sha512(password));
			System.out.println("\n\nID:" + c.getIdUtilisateur());
			
			
		} catch (Exception e) {			
			e.printStackTrace();
			nettoyerBean();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Login", "X(" ));
			
		}		
		return "/home";		
		
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
		passwordS=null;
		c= new ClientPro();
	}
	
}
