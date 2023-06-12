package com.examples.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.examples.springboot.form.app.servises.PaisService;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport{

	@Autowired
	private PaisService service;
	@Override
	public void setAsText(String idString) throws IllegalArgumentException {
	
		if(idString != null && idString.length()>0) {
			try {
				Integer id = Integer.parseInt(idString);
				this.setValue(service.obtenerPorId(id));
			}catch(NumberFormatException e) {
				setValue(null);
			}
			
		}else {
			setValue(null);
		}
		
	}
	

}
