package com.ipartek.formacion.pojo;

import java.util.ArrayList;
import java.util.Date;

public class Carrito {
	private ArrayList<Producto> productos;
	private long id,idUsuario;
	private Date fechaPedido;
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Carrito(ArrayList<Producto> productos, long idUsuario, Date fechaPedido) {
		super();
		this.productos = productos;
		this.idUsuario = idUsuario;
		this.fechaPedido = new Date();
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	@Override
	public String toString() {
		return "Carrito [productos=" + productos + ", id=" + id + ", idUsuario=" + idUsuario + ", fechaPedido="
				+ fechaPedido + "]";
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	
}
