package com.examples.springboot.form.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex,String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.matches("[0-9]{3}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")) {
			return true;
		}	
		return false;
	}

}
