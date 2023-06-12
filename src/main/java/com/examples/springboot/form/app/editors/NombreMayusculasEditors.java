package com.examples.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

public class NombreMayusculasEditors extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		setValue(text.toUpperCase().trim());
		
	}
	

}
