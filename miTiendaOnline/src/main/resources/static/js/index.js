productos =[];
carraco =[];
$(function(){
	$( "#capaCarrito" ).hide();
	cargarProductos(0);
	btnCarrito();
});
function cargarProductos(pag) {

	$.getJSON('/api/productos?pagina=' + 0, function(prod) {		
//		if (libros.length == 0)
//			$('#navegacion').hide();
//		else
//			$('#navegacion').show();
//		if (libros.length <= 5)
//			$('#navegacion').hide();
//		else
//			$('#navegacion').show();
		mostrarProductos(prod);
		$('#botonCarrito').append('<span id="numArt">  '+productos.length+'</span>');
	});
}
function btnCarrito(){
	$('#botonCarrito').click(function(){
		$( "#capaCarrito" ).slideToggle('slow');
	});
}
function unoMas(id){
	i=parseInt($('#sumarProd'+id).text());
	i=i+1;
	
	aux=parseFloat($('#precioP'+id).text());
	$('#sumarProd'+id).text(i);
	$('#multiplicadorSpan'+id).text(i);
	$('#precioMult'+id).text(parseFloat(aux*i).toFixed(2));
	var  miCarro= {"clave":id,"mult":parseFloat(aux*i).toFixed(2)};
	if(carraco.length==0)
		carraco.push(miCarro);
	for(x=0;x<carraco.length;x++){
		if(carraco[x].clave==id){
			carraco[x].clave=miCarro;
		}
		else{
			carraco.push(miCarro);
		}
	}	
	
	
	console.log(carraco);
}
function unoMenos(id){
	i=parseInt($('#sumarProd'+id).text());
	if(i>1){
		i=i-1;
		$('#multiplicadorSpan'+id).text(i);
	}	
	$('#sumarProd'+id).text(i);
}
function anadeAlCarro(id){
	n=$('#nomH4'+id).text();
	desc=$('#descP'+id).text();
	img=$('#imgP'+id).attr('src');
	pre=$('#precioP'+id).text();
	numArt=parseInt($('#numArt').text());
	$('#numArt').text("  "+(numArt+1));
	p=new Producto(id,n,desc,img,pre);
	productos.push(p);
	console.log(productos);
	$('#capaCarrito').empty();
	for(i=0;i<productos.length;i++){
	$('#capaCarrito').append('<div class="cardD text-center">'+
			'<div class="cardD-body"><h6 class="cardD-tittle">'+
			'<img class=" cardD-img-top" src="'+productos[i].ruta+'" alt="">'+productos[i].nombre+'</h6><p>'+productos[i].precio+'  x <span id="multiplicadorSpan'+productos[i].id+'">1</span>='+
			'<span id="precioMult'+productos[i].id+'">0</span></p>'+
			 '  <div class="miBotonera"><a href="javaScript:unoMas('+productos[i].id+')"  class="miBot btn btn-success">'+
		     '<i class="fas fa-plus"></i></button></a><span value=1 id="sumarProd'+productos[i].id+'">'+
		     '<b id=miB"'+productos[i].id+'">1</b></span><a href="javaScript:unoMenos('+productos[i].id+')"  class="miBot  btn btn-danger">'+
		     '<i class="fas fa-minus"></i></button></a></div></div><div class="card-footer"><p>cantidad del producto</p></div></div>');
	}
}
class Producto {
	  constructor(id, nombre,descripcion,ruta,precio) {
	    this.id = id;
	    this.nombre = nombre;
	    this.descripcion = descripcion;
	    this.ruta=ruta;
	    this.precio=precio;
	  }
	}
function mostrarProductos(productos) {
	$('myBody').empty();
	console.log(productos);
	$(productos).each(
			function() {
			$('#myBody').append(
		          ' <div class="col-lg-3 col-md-6 mb-4"><div class="card text-center"><img class=" card-img-top" src="'+this.ruta+'" id="imgP'+this.id+'"  alt="">'+
		          '<div class="card-body">'+
		              '<h4 class="card-title" id="nomH4'+this.id+'">'+this.nombre+'</h4>'+
		              '<p id="descP'+this.id+'" class="card-text">'+this.descripcion+'</p></div>'+ 
		           			            '<p class="font-weight-bold">precio:  <span id="precioP'+this.id+'">'+this.precio+'</span> euros</p>'+
		            '<div class="card-footer">'+
		            '<a href="javascript:anadeAlCarro('+this.id+')" class="miBotCar btn btn-success"><i class="fas fa-shopping-cart "></i></a>'+
		            '</div>'+
		         '</div>'+
		       ' </div>');
			});
}