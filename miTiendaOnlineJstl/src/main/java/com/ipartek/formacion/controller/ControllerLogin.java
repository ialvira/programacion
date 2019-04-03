package com.ipartek.formacion.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.ipartek.formacion.LogicaNegocio.LogicaNegocio;
import com.ipartek.formacion.accesoAdatos.ProductoSqlDAo;
import com.ipartek.formacion.pojo.Usuario;

@Controller
public class ControllerLogin {
	@Autowired
	private LogicaNegocio logica;
	@Autowired
	private ProductoSqlDAo pro;
	@PostMapping("/login")
	public String logVerdad(Usuario user,Model mo,HttpSession ses) {
		Usuario usuarioAcertado=null;
		if ((usuarioAcertado=logica.comprobarUsuario(user.getUser(), user.getPassword()))!=null) {
			ses.setAttribute("miusu", usuarioAcertado);
			mo.addAttribute("productos",pro.getAll(0));
			return "home";			
		}
		else
			return "in";
		
	}
	
}
