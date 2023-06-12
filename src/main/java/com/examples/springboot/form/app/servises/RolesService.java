package com.examples.springboot.form.app.servises;

import java.util.List;

import com.examples.springboot.form.app.models.domain.Role;

public interface RolesService {
	
	public List<Role> listar();
	public Role obtenerPorId(Integer id);
	
}
