package com.ipartek.tienda.accesoAdatos;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Repository;

import com.ipartek.tienda.bibliotecaPropertties.Utils;
import com.ipartek.tienda.pojo.Producto;


@Repository
public class ProductoSqlDAo  {
	private String urlBD;
	private String usuarioBD;
	private String passwordBD;

	public ProductoSqlDAo(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Properties prop = Utils.leerPropiedades("tienda.properties");
		urlBD = prop.getProperty("url");
		usuarioBD = prop.getProperty("usuario");
		passwordBD = prop.getProperty("password");
		
	}
	public List<Producto> getAll(int numPag) {
		ArrayList<Producto> misProductos = new ArrayList<Producto>();
		String sql = "select * from productos limit ?,8";
		try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, passwordBD)) {
			try (CallableStatement pst = (CallableStatement) conn.prepareCall(sql)) {
				pst.setInt(1, numPag);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String descripcion = rs.getString("descripcion");
						String rutaImagen = rs.getString("rutaImagen");
						long id = rs.getInt("id");
						Double precio=rs.getDouble("precio");
						System.out.println(precio);
						misProductos.add(new Producto(nombre, descripcion,rutaImagen,id,precio));
					}

				} catch (Exception e) {
					System.out.println(e);
				}
			} catch (Exception e) {
				System.out.println("ERROR AL CREAR LA SENTENCIA");
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE CONEXION");
			throw new AccesoDatosException("error getAll libro", e);
		}
		return misProductos;
	}
}
