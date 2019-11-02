package com.movieswatch.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "passwordConfirmationValidator")
public class PasswordConfirmationValidator implements Validator {

	private static final String PASSWORD_DIFFERENT  = "Le mot de passe et la confirmation doivent être identiques.";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		UIInput passwordComponent= (UIInput) component.getAttributes().get("composantMotDePasse");
		
		String password= (String) passwordComponent.getValue();
		String passwordConfirm= (String) value;
		
		if(passwordConfirm !=null && !password.equals(passwordConfirm)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, PASSWORD_DIFFERENT, null));
		}
		
	}

}
