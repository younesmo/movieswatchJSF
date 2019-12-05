package com.movieswatch.validators;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

@FacesValidator(value = "ageValidator")
public class AgeValidator implements Validator {

	private final String UNDER18 = "Vous devez avoir plus de 18 ans pour vous inscrire";
	private Logger log = Logger.getLogger(AgeValidator.class);

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		DateTime birthDate = new DateTime((Date) value);
		DateTime now = new DateTime(java.sql.Date.valueOf(LocalDate.now()));

		try {
			Interval interval = new Interval(birthDate, now);
			Period period = interval.toPeriod();
			log.debug("years:" + period.getYears());
			try {
				if (period.getYears() < 18) {
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, UNDER18, null));
				}
			} catch (Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(component.getClientId(facesContext), message);

			}
		} catch (IllegalArgumentException e) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"votre date de naissance ne peut être supérieure à la date du jour", null));
		}
	}
}
