package com.movieswatch.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.movieswatch.services.PostalCodeService;
import com.movieswatch.services.PostalCodeServiceImpl;

@FacesValidator(value="cpExistValidator")
public class CPExistValidator implements Validator{

	private final String CP_DOESNT_EXIST= "Le code postal introduit n'existe pas";
	
	//@Inject
	private PostalCodeService cpService;
	
	public CPExistValidator() {
		this.cpService= new PostalCodeServiceImpl();
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String cp= (String)value;
			if(cpService.getByNumber(cp)==null) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, CP_DOESNT_EXIST, null));
			}
		
	}
}


