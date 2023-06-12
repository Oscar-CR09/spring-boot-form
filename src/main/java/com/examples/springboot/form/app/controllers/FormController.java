package com.examples.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

//import java.util.HashMap;
//import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.examples.springboot.form.app.editors.NombreMayusculasEditors;
import com.examples.springboot.form.app.editors.PaisPropertyEditor;
import com.examples.springboot.form.app.editors.RolesEditor;
import com.examples.springboot.form.app.models.domain.Pais;
import com.examples.springboot.form.app.models.domain.Role;
import com.examples.springboot.form.app.models.domain.Usuario;
import com.examples.springboot.form.app.servises.PaisService;
import com.examples.springboot.form.app.servises.RolesService;
import com.examples.springboot.form.app.validation.UsuarioValidador;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private RolesService roleService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	@Autowired
	private RolesEditor roleEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"fechaNacimiento",new CustomDateEditor(dateFormat,true));
		
		binder.registerCustomEditor(String.class,"nombre",new NombreMayusculasEditors());
		binder.registerCustomEditor(String.class,"apellido",new NombreMayusculasEditors());
		
		binder.registerCustomEditor(Pais.class,"pais",paisEditor);
		binder.registerCustomEditor(Role.class,"roles",roleEditor);
		
	}
	
	@ModelAttribute("genero")
	public List<String>genero(){
		return Arrays.asList("Hombre", "Mujer");
		
	}
	
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return paisService.listar();

	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles(){
		return this.roleService.listar();
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap(){
		Map<String,String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERADOR", "Moderador");

		return roles;
	}
	
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERADOR");
		return roles;
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		Map<String,String> paises = new HashMap<String, String>();
		paises.put("MX", "Mexico");
		paises.put("ES", "España");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("BR", "Bracil");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");
		return paises;
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Oscar");
		usuario.setApellido("Cervantes");
		usuario.setIdentificador("123.453.332-K");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Algun valor secreto ****** ");
		usuario.setPais(new Pais(1,"MX","Mexico"));
		usuario.setRoles(Arrays.asList(new Role(2,"Usuario","ROLE_USER")));
		model.addAttribute("titulo","Formulario usuario");
		model.addAttribute("usuario",usuario);
		
		
		return "form";
		
	}
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario,BindingResult result, Model model) {

		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Resultado form");
			return "form";
		}

		return "redirect:/ver";
		
	}
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario", required=false) Usuario usuario,Model model,SessionStatus status) {
		
		if (usuario==null) {
			return "redirect:/form";
		}
		model.addAttribute("titulo","Resultado form");
		
		status.setComplete();
		return "resultado";
	}
}

