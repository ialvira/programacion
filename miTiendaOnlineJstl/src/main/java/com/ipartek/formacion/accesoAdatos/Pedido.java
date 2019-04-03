package com.ipartek.formacion.accesoAdatos;

import java.util.Date;

public class Pedido {
private int numeroPedido;
private Date fechaPedido;
private Double importeArticulos;
public Pedido(int numeroPedido, Date fechaP, Double importeArticulos) {
	super();
	this.numeroPedido = numeroPedido;
	this.fechaPedido = fechaP;
	this.importeArticulos = importeArticulos;
}
@Override
public String toString() {
	return "Pedido [numeroPedido=" + numeroPedido + ", fechaPedido=" + fechaPedido + ", importeArticulos="
			+ importeArticulos + "]";
}
public int getNumeroPedido() {
	return numeroPedido;
}
public void setNumeroPedido(int numeroPedido) {
	this.numeroPedido = numeroPedido;
}
public Date getFechaPedido() {
	return fechaPedido;
}
public void setFechaPedido(Date fechaPedido) {
	this.fechaPedido = fechaPedido;
}
public Double getImporteArticulos() {
	return importeArticulos;
}
public void setImporteArticulos(Double importeArticulos) {
	this.importeArticulos = importeArticulos;
}
}
