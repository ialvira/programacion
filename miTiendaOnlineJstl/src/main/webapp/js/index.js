pagina=0;
paginaPedidos=0;
carroGlobal=null;
productos=[];
	if(!sessionStorage.getItem("carrito")){
	productos=[];
	sessionStorage.setItem("carrito",JSON.stringify(productos));
	}else productos=JSON.parse(sessionStorage.getItem("carrito"));
$(function(){
	$('#numProd').text(productos.length);
	eventoBusquedas();
	$('#capaCarro').hide();
	$('#capaPedidos').hide();
	$('#capaDetalle').hide();
	
	
});
function pedidos(idUsu){
	$('#capaDetalle').hide();
	$('#capaPedidos').show();
	$('#capaCarro').hide();
	$('#paginaPrincipal').hide();
	$.getJSON('/api/pedidos/'+idUsu+'?pagina=' + 0, function(pedidos) {		
		console.log(pedidos);
		mostrarPedidos(pedidos);
	});
}
function detallesPedido(idPedido){
	$.getJSON('/api/detallePedido/'+idPedido, function(detallesPedido) {		
		console.log(detallesPedido);
		cargarCarro(detallesPedido,1);
		$('#capaPedidos').hide();
		
	});
}
function mostrarPedidos(pedidos){
	$('tbody').empty();
	$(pedidos).each(
			function() {
				$('#tablaBodyPedidos').append(
						'<tr><th>' + this.numeroPedido + "</th><td>" + this.fechaPedido
								+ '</td>' + "<td>" + this.importeArticulos + '</td>'
								+ '<td><a href="javascript:detallesPedido(' + this.numeroPedido
								+ ')">detalles</a> '
								+ '</td></tr>');
			});
}
function cargarProductos(pag) {
	$.getJSON('/api/productos?pagina=' + pag, function(prod) {		
		mostrarProductos(prod);
	});
}
function pagAnt(){
	if(pagina>0){
		pagina=pagina-1;
	}
	cargarProductos(pagina);
}
function pagSig(){
	pagina=pagina+1;
	cargarProductos(pagina);
}

class Producto {
	  constructor(id, nombre,descripcion,ruta,precio) {
	    this.id = id;
	    this.nombre = nombre;
	    this.descripcion = descripcion;
	    this.ruta=ruta;
	    this.precio=precio;
	    this.categoria=categoria;
	  }
	}
function carga(idCat,pag){
	$.getJSON('/api/productosCat?categoria='+idCat+'&pagina=' + pag, function(prod) {
		mostrarProductos(prod);
	});
}
function eventoBusquedas(){
	$('#busqueda1').on('input',function (){
		console.log('a');
		$.getJSON('/api/productosTexto?pagina='+0+'&busqueda=' + $('#busqueda1').val(), function(prod) {
			mostrarProductos(prod);
		});
	});
}
function mandaCarro(idUsuario){
	$('#capaPedidos').hide();
	$.ajax({
		url: '/api/carrito',
		method: 'POST',
		data: JSON.stringify({misId: JSON.parse(sessionStorage.getItem("carrito"))}),
		contentType: 'application/json; charset=UTF-8'
	}).done(function(carro) {
		console.log(carro);
		cargarCarro(carro,0);
		carroGlobal=carro;
	});
}
function confirmarConpra(){
	console.log(carroGlobal);
	$.ajax({
		url: '/api/confirmoCompra',
		method: 'POST',
		data: JSON.stringify(carroGlobal),
		contentType: 'application/json; charset=UTF-8'
	}).done(function(carro) {
		alert('BIEN');
		$('#capaCarro').hide();
		$('#paginaPrincipal').show();
		sessionStorage.setItem("carrito",JSON.stringify([]));
		$('#numProd').text('0');
		productos=[];
	});
}
function cargarCarro(ca,oculto){
	$('#capaCarro').show();
	$('#paginaPrincipal').hide();
	$('#capaDetalle').hide();
	totales=[];
	console.log(ca.productos.length)
	pCarro=ca.productos;
	var total=0;
	$('#carroAqui').empty();
	for(i=0;i<ca.productos.length;i++){
		$('#carroAqui').append(' <div class="col-lg-4 col-md-4 mb-4" id="img" >'+
                   '<img class="card-imgg-top" id="img" src="'+pCarro[i].ruta+'" width="50px" height="50px" alt="">'+
               '</div>'+
                '<div class="col-lg-2 col-md-2 mb-2" id="nombre" >'+
                    '<p id="nombre">'+pCarro[i].nombre+'</p>'+
               '</div>'+
                '<div class="col-lg-2 col-md-2 mb-2">'+
                    '<p id="desc">'+pCarro[i].descripcion+'</p>'+
                '</div>'+
               '<div class="col-lg-2 col-md-2 mb-2">'+
                    '<p id="cant">'+pCarro[i].cantidad+'</p>'+
                '</div>'+
               '<div class="col-lg-2 col-md-2 mb-2">'+
                    '<p id="total">total:'+(pCarro[i].precio*pCarro[i].cantidad).toFixed(2)+'€</p>'+
               '</div>');
		totales[i]=pCarro[i].precio*pCarro[i].cantidad;
		if(oculto==1)
		$('.btnCompra').hide();
		else
		$('.btnCompra').show();
	}
	for(x=0;x<totales.length;x++){
		total=total+totales[x];
	}
		$('#au span').text(total.toFixed(2));
}
function volverCompra(){
	$('#capaPedidos').hide();
	$('#capaCarro').hide();
	$('#paginaPrincipal').show();
	$('#capaDetalle').hide();
}
function anadeCarrito(idProducto){
	productos.push(parseInt(idProducto));
	sessionStorage.setItem("carrito",JSON.stringify(productos));
	$('#numProd').text(productos.length);
}
function detallesProd(idProd){
	$.getJSON('/api/detallesProd?id=' + idProd, function(prod) {
		console.log(prod);
		$('#capaPedidos').hide();
		$('#capaCarro').hide();
		$('#paginaPrincipal').hide();
		$('#capaDetalle').show();
		$('#nombreDetalle').text(prod.nombre);
		$('#descripcionDetalle').text(prod.descripcion);
		$('#precioDetalle').text(prod.precio);
		$('#imgDetalle').attr("src", prod.ruta);
	});
}
function mostrarProductos(productos) {
	if(productos.length<6)
		$('#botonDerecha').hide();
	else
		$('#botonDerecha').show();
	aux=productos.length;
	for(i=0;i<6;i++){		
		$('#prod'+i).remove();
	}
	for(i=0;i<productos.length;i++){
		$('#galeria').append('<div class="col-lg-2 col-md-2 mb-2" id="prod'+i+'">'+
					'<div class="card"><img class="card-img-top" id="img'+i+'" src="'+productos[i].ruta+'"alt="">'+
							'<div class="card-body"><h4 class="card-title" id="nombre'+i+'">'+productos[i].nombre+'</h4>'+
							'<p class="card-text" id="desc'+i+'">'+productos[i].descripcion+'</p>'+
								'<h6 class="card-text" id="precio'+i+'">'+productos[i].precio+'€'+
							'i.v.a no incluido</h6></div>'+
							'<div class="card-footer">'+
								'<a href="javascript:detallesProd('+productos[i].id+')">detalles</a><a href="javascript:anadeCarrito('+productos[i].id+')" id="boton'+productos[i].id+'" class="btn btn-primary">añade'+
									' al carro</a>'+
							'</div>'+
						'</div>'+
					'</div>');
		
			
	}

}
