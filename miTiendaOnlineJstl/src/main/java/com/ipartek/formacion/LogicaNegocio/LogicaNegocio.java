package com.ipartek.formacion.LogicaNegocio;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.accesoAdatos.UsuarioSqlDao;
import com.ipartek.formacion.pojo.Usuario;
@Service
public  class LogicaNegocio {
	@Autowired
	private UsuarioSqlDao userDao;
	public  Usuario comprobarUsuario(String user, String password) {
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) userDao.getAll();
		for (int i = 0; i < usuarios.size(); i++) {
			//System.out.println(usuarios.get(i).getPassword() + usuarios.get(i).getUser());
			if (usuarios.get(i).getUser().equalsIgnoreCase(user)
					&& usuarios.get(i).getUser().equalsIgnoreCase(password)) {
				Usuario usuarioFormado=new Usuario(usuarios.get(i).getId(),usuarios.get(i).getUser(),usuarios.get(i).getPassword());
				return usuarioFormado;
			}
		}
		return null;
	}

}
