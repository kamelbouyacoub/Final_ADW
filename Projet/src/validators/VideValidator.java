package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class VideValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String label;
        
        HtmlInputText htmlInputText=(HtmlInputText) component;
        
        if(htmlInputText.getLabel()==null || htmlInputText.getLabel().trim().equals(""))
        {
            label=htmlInputText.getId();
        }
        else
        {
            label=htmlInputText.getLabel();
        }
        
        if(value.toString().trim().equals(""))
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", label+": C'est un champ obligatoire"));
        }
    }
}