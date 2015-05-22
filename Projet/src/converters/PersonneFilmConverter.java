package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;





import dao.DAOPersonneFilm;
import dao.DAOPersonneFilmJPA;
import film.PersonneFilm;
import beans.BeanPersonneFilm;


@FacesConverter("personneFilmConverteConverter")
public class PersonneFilmConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
	 if(value != null && value.trim().length() > 0) {
            try {
            	
            	  System.out.println("value: "+  value);
            	  int id = Integer.parseInt(value);
            	  DAOPersonneFilm dao = DAOPersonneFilmJPA.getInstance();
            	  System.out.println("value: "+  dao.get(id).getNom() );
//                PersonneFilm service = (PersonneFilm) fc.getExternalContext().getApplicationMap().get("PersonneFilm");
//                System.out.println("\n\n\n\nconverte"+service.getIdPersonne());
                return dao.get(id);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
		
		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
            return object.toString();
        }
        else {
            return null;
        }
		

	}

}
