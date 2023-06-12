package com.examples.springboot.form.app.servises;

import java.util.List;

import com.examples.springboot.form.app.models.domain.Pais;

public interface PaisService {
	
	
	public List<Pais> listar();
	public Pais obtenerPorId(Integer id);
	

}
