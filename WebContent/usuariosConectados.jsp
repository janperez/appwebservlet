<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuarios conectados</title>

	<!--  Incluir librerias jsQuery -->
	<script src="js/jquery.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	
	<link type="text/css" rel="stylesheet" href="js/css/jquery.dataTables.css">
	
</head>
<body>
	<a href="index.jsp">Volver</a>
	<table id="tabla_usuarios">
		<caption>Usuarios conectados</caption>
		<thead>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Nombre</th>
			<th scope="col">DNI</th>
			<th scope="col">F. Conexion</th>
			<th scope="col">T. Expiracion</th>
			<th scope="col">Ultima Conexion</th>
		</tr>
		</thead>
		
		<tbody>	
			<% HashMap usuarios = (HashMap)application.getAttribute("listaUsuarios"); %>
			<c:forEach var="usuario" items="<%=usuarios %>">
				<tr>
					<td>${usuario.value.id}</td>
					<td>${usuario.value.nombre}</td>	
					<td>${usuario.value.password}</td>	
					<td>${usuario.value.conexionString}</td>	
					<td>${usuario.value.expireTime}</td>	
					<td>${usuario.value.anteriorConexionString}</td>	
				</tr>
			</c:forEach>		
		</tbody>
	</table>
		
	<script type="text/JavaScript">
		$(document).ready(function() {
			console.debug('Estoy funcionando');
		    $('#tabla_usuarios').dataTable();
		} );
	</script>
</body>

</html>