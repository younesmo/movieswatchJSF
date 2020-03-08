package com.movieswatch.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */


@FacesValidator(value = "passwordConfirmationValidator")
public class PasswordConfirmationValidator implements Validator {

	private static final String PASSWORD_DIFFERENT  = "Le mot de passe et la confirmation doivent être identiques.";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		UIInput passwordComponent= (UIInput) component.getAttributes().get("passwordComponent");
		
		String password= (String) passwordComponent.getValue();
		String passwordConfirm= (String) value;
		try {
		if(passwordConfirm !=null && !password.equals(passwordConfirm)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, PASSWORD_DIFFERENT, null));
		}
		}catch(Exception e) {
			 FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
	         FacesContext facesContext = FacesContext.getCurrentInstance();
	         facesContext.addMessage( component.getClientId( facesContext ), message );
		}
		
	}

}
