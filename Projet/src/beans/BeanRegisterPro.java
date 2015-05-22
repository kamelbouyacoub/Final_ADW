package beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.DAOClientPro;
import dao.DAOClientProJPA;
import tools.Encrypt;
import utilisateurs.ClientPro;

public class BeanRegisterPro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ClientPro clientPro = new ClientPro();	
	private String pass = null;
	
	
	public ClientPro getClientPro() {
		return clientPro;
	}
	
	public void setClientPro(ClientPro clientPro) {
		this.clientPro = clientPro;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void save(){	
		
		if (clientPro.getPass().equals(pass)){
			
			DAOClientPro dao = DAOClientProJPA.getInstance();
			
			if(dao.verifierEmail(clientPro)){
				formater();
				clientPro.setPass(Encrypt.sha512(pass));
				dao.save(clientPro);				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", ";)" ));				
			}
			else
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail existant","X)" ));
		}
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password ne coincide pas","X)" ));		
	}	
	
	private void formater(){
		clientPro.setNom(clientPro.getNom().toLowerCase());
		clientPro.setNom(clientPro.getPrenom().toLowerCase());
		clientPro.setEtat(true);
		clientPro.setPhoto("sans photo");
	}
	
	
}
