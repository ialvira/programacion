package com.ipartek.tienda.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.tienda.accesoAdatos.ProductoSqlDAo;
import com.ipartek.tienda.pojo.Producto;

@RestController
public class PrudctoRest {
	@Autowired
	private ProductoSqlDAo daoProdcuto;
	@GetMapping("/api/productos")
	public List<Producto> getAllProductos(@RequestParam ("pagina") int pagina){
		return daoProdcuto.getAll(8*pagina);
	}
}
