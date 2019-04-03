package com.ipartek.formacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.accesoAdatos.ProductoSqlDAo;
import com.ipartek.formacion.pojo.Producto;



@RestController
public class PrudctoRest {
	@Autowired
	private ProductoSqlDAo daoProdcuto;
	@GetMapping("/api/productos")
	public List<Producto> getAllProductos(@RequestParam ("pagina") int pagina){
		return daoProdcuto.getAll(6*pagina);
	}
	@GetMapping("/api/productosCat")
	public List<Producto> getProductosCat(@RequestParam("categoria")int cat, @RequestParam("pagina") int pag){
		if(cat==1)
		return daoProdcuto.getAllPorCat(6*pag, "portatil");
		if(cat==2)
		return daoProdcuto.getAllPorCat(6*pag, "movil");
		
		return daoProdcuto.getAll(6*pag);
		
	}
	@GetMapping("/api/productosTexto")
	public List<Producto> getProductosPorText(@RequestParam("pagina") int pag,@RequestParam("busqueda") String texto){
		return daoProdcuto.getPorTexto(texto,6*pag);
		
	}
	@GetMapping("/api/detallesProd")
	public Producto getProductosPorId(@RequestParam("id") Long id){
		return daoProdcuto.getById(id);
		
	}
}
