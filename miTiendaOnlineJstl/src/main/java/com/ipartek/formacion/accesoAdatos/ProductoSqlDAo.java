package com.ipartek.formacion.accesoAdatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Repository;
import com.ipartek.formacion.bibliotecaPropertties.Utils;
import com.ipartek.formacion.pojo.Producto;
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
	public List<Producto> getPorTexto(String texto,int pag) {
		ArrayList<Producto> misProductos = new ArrayList<Producto>();
		String sql = "select * from productos where nombre like ? or descripcion like ? or categoria like ? limit ?,6";
		try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, passwordBD)) {			
			try (CallableStatement pst = (CallableStatement) conn.prepareCall(sql)) {
				pst.setString(1, "%"+texto+"%");
				pst.setString(2, "%"+texto+"%");
				pst.setString(3, "%"+texto+"%");
				pst.setInt(4, pag);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String descripcion = rs.getString("descripcion");
						String rutaImagen = rs.getString("rutaImagen");
						long idd = rs.getLong("id");
						Double precio=rs.getDouble("precio");
						String cate=rs.getString("categoria");
						misProductos.add(new Producto(nombre, descripcion,rutaImagen,idd,precio,cate));
					}

				} catch (Exception e) {
					System.out.println(e);
				}
			} catch (Exception e) {
				System.out.println("ERROR AL CREAR LA SENTENCIA");
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE CONEXION");
			throw new AccesoDatosException("error getPorDni libro", e);
		}
		
		return misProductos;
		
	}
	public Producto getById(long id) {
		Producto  p = null;
		String sql = "select * from productos where id=?";
		try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, passwordBD)) {
			try (CallableStatement pst = (CallableStatement) conn.prepareCall(sql)) {
				pst.setLong(1, id);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String descripcion = rs.getString("descripcion");
						String rutaImagen = rs.getString("rutaImagen");
						long idd = rs.getLong("id");
						Double precio=rs.getDouble("precio");
						String cate=rs.getString("categoria");
						p=new Producto(nombre, descripcion,rutaImagen,idd,precio,cate);
					}

				} catch (Exception e) {
					System.out.println(e);
				}
			} catch (Exception e) {
				System.out.println("ERROR AL CREAR LA SENTENCIA");
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE CONEXION");
			throw new AccesoDatosException("error getLibroXid libro", e);

		}
		return p;
	}
	public ArrayList<Pedido> detallePedido(int idPedido) {
		
		return null;
	}
	public List<Producto> getAllPorCat(int numPag,String categoria) {
		ArrayList<Producto> misProductos = new ArrayList<Producto>();
		String sql = "select * from productos  where categoria=?  limit ?,6";
		try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, passwordBD)) {
			try (CallableStatement pst = (CallableStatement) conn.prepareCall(sql)) {
				pst.setInt(2, numPag);
				pst.setString(1, categoria);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String descripcion = rs.getString("descripcion");
						String rutaImagen = rs.getString("rutaImagen");
						long id = rs.getInt("id");
						Double precio=rs.getDouble("precio");
						String cate=rs.getString("categoria");
						System.out.println(precio);
						misProductos.add(new Producto(nombre, descripcion,rutaImagen,id,precio,cate));
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
	public List<Producto> getAll(int numPag) {
		ArrayList<Producto> misProductos = new ArrayList<Producto>();
		String sql = "select * from productos limit ?,6";
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
						String cate=rs.getString("categoria");
						System.out.println(precio);
						misProductos.add(new Producto(nombre, descripcion,rutaImagen,id,precio,cate));
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
