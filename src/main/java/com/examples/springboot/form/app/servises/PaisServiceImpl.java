package com.examples.springboot.form.app.servises;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.examples.springboot.form.app.models.domain.Pais;

@Service
public class PaisServiceImpl implements PaisService {

	private List<Pais> lista;
	
	
	public PaisServiceImpl() {
		
		this.lista = Arrays.asList(
				new Pais(1,"MX","Mexico"),
				new Pais(2,"ES","España"),
				new Pais(3,"CL","Chile"),
				new Pais(4,"AR","Agentina"),
				new Pais(5,"BR","Brasil"),
				new Pais(6,"CO","Colombia"),
				new Pais(7,"VE","Venezuela"));
	}

	@Override
	public List<Pais> listar() {
	
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado =null;
		for(Pais pais: this.lista) {
			if(id == pais.getId()) {
				resultado = pais;
				break;
				
			}
		}
		return resultado;
	}

}
