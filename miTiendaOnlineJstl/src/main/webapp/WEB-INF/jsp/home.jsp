<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Heroic Features - Start Bootstrap Template</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP"
	crossorigin="anonymous">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/heroic-features.css" rel="stylesheet">

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Start Bootstrap</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="javascript:volverCompra()">inicio
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="javascript:pedidos(${miusu.id})">mis pedidos</a></li>
					<li class="nav-item"><a class="nav-link" href="#">hola ${miusu.user} </a>
					</li>
					<li>
					<a class="btn btn-success"
						id="botonCarrito" href="javascript:mandaCarro(${miusu.id})">carrito <i
							class="fas fa-shopping-bag"></i> <span id="numProd"> 0</span> </a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container-fluid" id="paginaPrincipal">

		<!-- Jumbotron Header -->
		
			<h1 class="display-6 text-center">txarkShop</h1>
		
		<div class="row text-center">
			<div class="col-lg-1 col-md-1 mb-1 "></div>
			<div class="col-lg-10 col-md-10 mb-3 ">
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="nav1"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> Categorias </a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="javascript:carga(1,0)">ordenadores</a> <a
										class="dropdown-item" href="javascript:carga(2,0)">moviles</a>
										<a class="dropdown-item" href="javascript:carga(0,0)">todo</a>
										
								</div></li>
						</ul>
						<div class=" my-2 my-lg-0">
							<input class="form-control mr-sm-2" id="busqueda1" type="search"
								placeholder="busca" aria-label="Search">
						</div>
					</div>
				</nav>
			</div>
		</div>
		<!-- Page Features -->
		<div class="row text-center">

			<div class="col-lg-1 col-md-1 mb-1 ">
				<br> <br> <br> <br> <br> <br> <br>
				<br> <a href="javascript:pagAnt()"><i
					class="botones fas fa-arrow-circle-left fa-7x"></i></a>
			</div>
				<div class="col-lg-10 col-md-10">
				<div class="row text-center" id="galeria">
				<c:forEach items="${productos}" var="prod" varStatus="i">
							<div class="col-lg-2 col-md-2 miProd" id="prod${i.index}">
								<div class="card">
									<img class="card-img-top" id="img${i.index}" src="${prod.ruta}"
										alt="">
									<div class="card-body">
										<h4 class="card-title" id="nombre${i.index}">${prod.nombre}</h4>
										<p class="card-text" id="desc${i.index}">${prod.descripcion}</p>
										<h6 class="card-text" id="precio${i.index}">${prod.precio}€
											i.v.a no incluido</h6>
									</div>
									<div class="card-footer">
										<a href="javascript:detallesProd(${prod.id})">detalles</a><a href="javascript:anadeCarrito(${prod.id})" id="boton${prod.id}" class="btn btn-primary">añade
											al carro</a>
									</div>
								</div>
							</div>
					</c:forEach>
				</div>
				</div>
			<div class="col-lg-1 col-md-1 mb- text-center" id="botonDerecha">
				<br> <br> <br> <br> <br> <br> <br>
				<br> <a href="javascript:pagSig()"><i
					class=" botones fas fa-arrow-circle-right fa-7x"></i></a>
			</div>

		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->
<div class="container" id="capaCarro">
           
            <div class="row text-center" id="carroAqui">

             
            </div>
        
        <div class="card">
            <div class="row text-center">
                <div class="col-lg-12 col-md-12 mb-12">
                    <p id="au">total de la compra = <span id="total">1111</span>€ <a class="btn btn-success btnCompra" href="javaScript:confirmarConpra()">Confirmar Compra</a>&nbsp;&nbsp;<a class="btn btn-danger btnCompra" href="">Cancelar Compra</a>&nbsp;&nbsp;<a class="btn btn-dark" href="javascript:volverCompra()">volver</a></p>
                </div>
            </div>
        </div>
        </div>
        
 <div class="container" id="capaPedidos">
 	<table class="table-responsive-md table table-bordered" border="0">
		<thead>
			<tr>
				<th class="text-center">numero de pedido</th>
				<th class="text-center">fechaPedido</th>
				<th class="text-center">importe pedido</th>
				<th class="text-center">acciones</th>
			</tr>
		</thead>
		<tbody id="tablaBodyPedidos">
		</tbody>
		<tfoot>
		</tfoot>
	</table>
 </div>
 
    <div class="container" id="capaDetalle">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-xs-6">
                <img alt="" id="imgDetalle" class="text-center" src="m6.jpg" />   
            </div>
            <div class="col-lg-6 col-md-6 col-xs-6">
               <h3 class="text-center" id="nombreDetalle">nombre blabla</h3>
               <p id="descripcionDetalle">ajsfbhasbdhsbdjas asjdbjasbd jsadbjasb asdbas basndb asbdbhasbd asbd bsndbas dbnasbdnas basdb absasjdb nasbd aj jbasdbasnb dasjdb asnbd jasbd asjbd jasbdjasbd nasb nasbd nsabdn asbdnasbdsajbd jasb jasdb ajsdb asndb asbd asnbd asnbd hasbd hsdhasbd hasbd hasdb hb hasbd sahdbashbd ashdb ashdbashdbashdbashdbashdbashdbhas bsahdbshadbsahdbhasdb ashdbahsdhasbhdasbdashdbhasbd</p>
                <p>precio: <b id="precioDetalle">1111€</b></p>
            </div>
        </div>
    </div>
	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2018</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="js/index.js"></script>
</body>

</html>
