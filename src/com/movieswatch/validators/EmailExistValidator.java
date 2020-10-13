package com.movieswatch.validators;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.movieswatch.services.UserService;
import com.movieswatch.services.UserServiceImpl;

/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */


@FacesValidator(value= "emailExistValidator")
public class EmailExistValidator implements Validator {
	
    private static final String EMAIL_EXIST = "Cette adresse email est deja  utiliser";
    
    //@Inject
    private UserService userService;

    public EmailExistValidator() {
    	this.userService= new UserServiceImpl();
    }
    
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String email= (String) value;
		
		try {
			
			if(userService.findByEmail(email)!=null) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, EMAIL_EXIST, null));
			}
		}catch(Exception e) {
			 FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
	         FacesContext facesContext = FacesContext.getCurrentInstance();
	         facesContext.addMessage( component.getClientId( facesContext ), message );
		}
	}

}
