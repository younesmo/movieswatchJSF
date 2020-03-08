package com.movieswatch.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.movieswatch.entities.User;
import com.movieswatch.utils.SessionUtils;

/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */


@FacesValidator(value="checkPasswordValidator")
public class CheckPasswordValidator implements Validator {

	private final String WRONG_PASSWORD= "Le mot de passe est incorrect";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String password= (String) value;
		User user= SessionUtils.getCurrentUser();
		
		try {
			if(!password.equals(user.getPassword())) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, WRONG_PASSWORD, null));
		}
		}catch(Exception e) {
			 FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
	         FacesContext facesContext = FacesContext.getCurrentInstance();
	         facesContext.addMessage( component.getClientId( facesContext ), message );
		}
			
			
	}

}
