package com.ipartek.formacion.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.accesoAdatos.Pedido;
import com.ipartek.formacion.accesoAdatos.PedidoSqlDao;
import com.ipartek.formacion.accesoAdatos.ProductoSqlDAo;
import com.ipartek.formacion.pojo.Carrito;
import com.ipartek.formacion.pojo.CarritoDto;
import com.ipartek.formacion.pojo.Producto;
import com.ipartek.formacion.pojo.Usuario;

@RestController
public class ControllerCarrito {
	@Autowired
	ProductoSqlDAo prodDao;
	@Autowired
	PedidoSqlDao pedidosDao;
	@PostMapping("/api/carrito")
	public Carrito procesarCompra(@RequestBody CarritoDto dto,HttpSession session) {
		System.out.println(dto);
		Usuario user=(Usuario)session.getAttribute("miusu");
		System.out.println("holaa");
		System.out.println(user.getId()+"holaa");
		ArrayList<Producto> producs=new ArrayList<>();
		for(int i=0;i<dto.getMisId().size();i++) {
			for(int u=0;u<producs.size();u++) {
				if(producs.get(u).getId()==dto.getMisId().get(i)) {
					producs.get(u).setCantidad();
					break;
				}	
				else {
					if(u==producs.size()-1) {
					producs.add(prodDao.getById(dto.getMisId().get(i)));
					break;
					}									
				}	
			}		
			if(producs.size()==0)
				producs.add(prodDao.getById(dto.getMisId().get(i)));
		}
		for(int x=0;x<producs.size();x++) {
			System.out.println(producs.get(x).getCantidad());
		}
		Carrito carro=new Carrito(producs,user.getId(), new Date());
		return carro;
	}
	@PostMapping("/api/confirmoCompra")
	public void confCompra(@RequestBody Carrito carro) {
		System.out.println(carro);
		pedidosDao.insert(carro);
		
	}
	@GetMapping("/api/pedidos/{idUsu}")
	public ArrayList<Pedido> cargarPedidos(@PathVariable("idUsu")int idUsu,@RequestParam("pagina") int pagina) {
		return pedidosDao.cargarPedidos(idUsu, pagina);
	}
	@GetMapping("/api/detallePedido/{idPedido}")
	public Carrito cargarDetallePedidos(@PathVariable("idPedido")int idUsu) {
		return pedidosDao.detallePedido(idUsu); 
	}

	
}
