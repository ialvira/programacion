package com.ipartek.tienda.pojo;

import java.math.BigInteger;

public class Producto {
private String nombre,descripcion,ruta;
private Long id;
private Double precio;

@Override
public String toString() {
	return "Producto [nombre=" + nombre + ", descripcion=" + descripcion + ", ruta=" + ruta + ", id=" + id + ", precio="
			+ precio + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());

	result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
	result = prime * result + ((ruta == null) ? 0 : ruta.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Producto other = (Producto) obj;
	if (descripcion == null) {
		if (other.descripcion != null)
			return false;
	} else if (!descripcion.equals(other.descripcion))
		return false;
	if (id != other.id)
		return false;
	if (nombre == null) {
		if (other.nombre != null)
			return false;
	} else if (!nombre.equals(other.nombre))
		return false;
	if (ruta == null) {
		if (other.ruta != null)
			return false;
	} else if (!ruta.equals(other.ruta))
		return false;
	return true;
}
public Producto(String nombre, String descripcion, String ruta, long id,Double precio) {
	super();
	this.nombre = nombre;
	this.descripcion = descripcion;
	this.ruta = ruta;
	this.id = id;
	this.precio =  precio;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public String getRuta() {
	return ruta;
}
public void setRuta(String ruta) {
	this.ruta = ruta;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public Double getPrecio() {
	return precio;
}
public void setPrecio(Double precio) {
	this.precio = precio;
}
}
