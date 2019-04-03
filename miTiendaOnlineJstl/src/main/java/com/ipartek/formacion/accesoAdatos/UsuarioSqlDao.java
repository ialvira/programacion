package com.ipartek.formacion.accesoAdatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

import com.ipartek.formacion.bibliotecaPropertties.Utils;
import com.ipartek.formacion.pojo.Usuario;


@Repository
public class UsuarioSqlDao implements CrudAble<Usuario> {
	TreeMap<Long, Usuario> usuarios = new TreeMap<Long, Usuario>();
	private String urlBD;
	private String usuarioBD;
	private String passwordBD;

	public UsuarioSqlDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties prop = Utils.leerPropiedades("biblioteca.properties");
		urlBD = prop.getProperty("url");
		usuarioBD = prop.getProperty("usuario");
		passwordBD = prop.getProperty("password");
		
	}
	public  Boolean comprobarUsuario(String user, String password) {
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) getAll();
		for (int i = 0; i < usuarios.size(); i++) {
			System.out.println(usuarios.get(i).getPassword() + usuarios.get(i).getUser());
			if (usuarios.get(i).getUser().equalsIgnoreCase(user)
					&& usuarios.get(i).getUser().equalsIgnoreCase(password)) {
				return true;
			}
		}
		return false;
	}
	public List<Usuario> getAll() {
		ArrayList<Usuario> misUsuarios = new ArrayList<Usuario>();
		String sql="select * from usuarios";
		try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, passwordBD)) {	
			try (CallableStatement pst = (CallableStatement) conn.prepareCall(sql)) {
				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {
						long id = rs.getLong("id");
						String user = rs.getString("user");
						String pass = rs.getString("password");
						misUsuarios.add(new Usuario(id,user, pass));
					}

				} catch (Exception e) {
					System.out.println(e);
				}
			} catch (Exception e) {
				System.out.println("ERROR AL CREAR LA SENTENCIA");
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE CONEXION");
			System.out.println(e.getMessage());
		}
		return misUsuarios;
	}
	public boolean insert(Usuario pojo) {
		//insertarReg(pojo);
		return true;
	}
	@Override
	public boolean update(Usuario pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Usuario getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}



	}