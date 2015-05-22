package tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

public class Tool {

	public static Object getSession(String nomClass, String beanSession) throws ClassNotFoundException{
		Object reponse = Class.forName(nomClass);
		HttpSession session  = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		reponse = (Object)session.getAttribute(beanSession);		
		return reponse;
	}
	
	public static void closeSession(String nomClass, String beanSession) throws ClassNotFoundException{
		Object reponse = Class.forName(nomClass);
		HttpSession session  = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		reponse = (Object)session.getAttribute(beanSession);		
		session.invalidate();
	}
	
	

}
