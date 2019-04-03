package com.ipartek.formacion.accesoAdatos;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.springframework.stereotype.Repository;

import com.ipartek.formacion.bibliotecaPropertties.Utils;
import com.ipartek.formacion.pojo.Carrito;
import com.ipartek.formacion.pojo.Producto;
import com.ipartek.formacion.pojo.Usuario;
import com.mysql.cj.jdbc.CallableStatement;
@Repository
public class PedidoSqlDao {
	private String urlBD;
	private String usuarioBD;
	private String passwordBD;

	public PedidoSqlDao() {
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
	public ArrayList<Pedido> cargarPedidos(int idUsuario,int pagina) {
		Double cantidad=0.00;
		Boolean exixte=false;
		ArrayList<Pedido> misPedidosAux = new ArrayList<Pedido>();
		ArrayList<Pedido> misPedidos = new ArrayList<Pedido>();
		String sql = "select numeroPedido,fechaPedido,importeArticulos from pedidos where usuarios_id=?";
		try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, passwordBD)) {
			try (CallableStatement pst = (CallableStatement) conn.prepareCall(sql)) {
				pst.setInt(1, idUsuario);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						int numPedido=rs.getInt("numeroPedido");
						Date fechaP=rs.getDate("fechaPedido");
						Double importeArt=rs.getDouble("importeArticulos");
						misPedidosAux.add(new Pedido(numPedido, fechaP, importeArt));
					}

				} catch (Exception e) {
					System.out.println(e);
				}
			} catch (Exception e) {
				System.out.println("ERROR AL CREAR LA SENTENCIA aqui");
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE CONEXION");
			throw new AccesoDatosException("error getAll libro", e);
		}
		for(int i=0;i<misPedidosAux.size();i++) {
			int ax=misPedidosAux.get(i).getNumeroPedido();
			cantidad=misPedidosAux.get(i).getImporteArticulos()+cantidad;
		if(misPedidos.size()!=0)
			for(int u=0;u<misPedidos.size();u++) {
				if(misPedidos.get(u).getNumeroPedido()==misPedidosAux.get(i).getNumeroPedido()) {
					misPedidos.get(u).setImporteArticulos(misPedidos.get(u).getImporteArticulos()+misPedidosAux.get(i).getImporteArticulos());
					exixte=true;
					break;
				}
			}
			if(misPedidos.size()==0 || !exixte)
				misPedidos.add(misPedidosAux.get(i));
			
			exixte=false;
		}
		return misPedidos;
		
	}
	public boolean insert(Carrito pojo) {
		String sql="SELECT numeroPedido FROM tiendaonline.pedidos order by numeroPedido desc limit 1"; 
		int numeroActualPedido=0;
		try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, passwordBD)) {	
			try (CallableStatement pst = (CallableStatement) conn.prepareCall(sql)) {
				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {
						int numeroPedido = rs.getInt("numeroPedido");
						numeroActualPedido=numeroPedido+1;
					}

				} catch (Exception e) {
					System.out.println(e);
					numeroActualPedido=1;
				}
			} catch (Exception e) {
				System.out.println("ERROR AL CREAR LA SENTENCIA");
				numeroActualPedido=1;
			}

		} catch (SQLException e) {
			numeroActualPedido=1;
			System.out.println("ERROR DE CONEXION");
			System.out.println(e.getMessage());
		}
		 sql = "insert into pedidos (numeroPedido,fechaPedido,usuarios_id,productos_id,cantidadProducto,importeArticulos) values (?,?,?,?,?,?)";
		try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, passwordBD)) { // insert the data
			try (CallableStatement pst = (CallableStatement) conn.prepareCall(sql)) {
				for (int i = 0; i < pojo.getProductos().size(); i++) {
					pst.setInt(1, numeroActualPedido);
					pst.setDate(2,  java.sql.Date.valueOf(java.time.LocalDate.now()));
					pst.setLong(3, pojo.getIdUsuario());
					pst.setLong(4, pojo.getProductos().get(i).getId());
					pst.setInt(5, pojo.getProductos().get(i).getCantidad());
					System.out.println(pojo.getProductos().get(i).getPrecio());
					pst.setDouble(6, pojo.getProductos().get(i).getCantidad()*pojo.getProductos().get(i).getPrecio());
					pst.executeUpdate();
				}
				return true;
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("error insert editorial", e);
		}
	}
	public Carrito detallePedido(int idPedido) {
		ArrayList<Producto> misProductos = new ArrayList<Producto>();
		String sql = "SELECT fechaPedido,pe.id,pe.usuarios_id as idUsu,rutaImagen,pro.nombre,pro.descripcion as descr,pro.precio as impArt,pe.cantidadProducto as cant"
				+ " FROM pedidos pe,productos pro"
				+ " where pe.productos_id=pro.id "
				+ "and pe.numeroPedido=? ";
		Double precio=0.0;
		long idUsu=0,idPe=0;
		Date fecha = null;
		Carrito ca=null;
		ArrayList<Producto> productos=new ArrayList<Producto>();
		try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, passwordBD)) {			
			try (CallableStatement pst = (CallableStatement) conn.prepareCall(sql)) {
				pst.setInt(1,idPedido );
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						String rutaImg=rs.getString("rutaImagen");
						String nombre=rs.getString("nombre");
						String desc=rs.getString("descr");
						int cant=rs.getInt("cant");
						idUsu=rs.getLong("idUsu");
						precio=rs.getDouble("impArt");
						fecha=rs.getDate("fechaPedido");
						
						productos.add(new Producto(nombre, desc, rutaImg, idPe, precio, null,cant));
					}	
					ca=new Carrito(productos, idUsu, fecha);
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
		
		return ca ;
	}
}
