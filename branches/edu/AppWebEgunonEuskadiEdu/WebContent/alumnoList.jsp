<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
    <%@page import="com.ipartek.pruebas.bean.Alumno"%>
    <%@page import="java.util.ArrayList"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Incluir JQuery y data table -->
<script src="js/jquery.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="js/css/jquery.dataTables.css">
</head>
<body>
<h1>Listado de todos los Alumnos</h1>

	<table summary="An�lisis de ventas anuales" id="tabla_alumno">
	  <caption>Cabecera o Caption</caption>
	   <thead>
	    <tr>
	      <th scope="col">Nombre</th>
	      <th scope="col">Apellido</th>
	      <th scope="col">Dni</th>
	      <th scope="col">Edad</th>
	      <th scope="col">Sexo</th>
	      <th scope="col">Email</th>
	      
	    </tr>
	  </thead>
	  <tbody>
	   
	  	<%
			ArrayList <Alumno> lAlumnos = (ArrayList<Alumno>) request.getAttribute("listaAlumnos");	
	  	    Alumno a;
			for ( int i=0; i<lAlumnos.size();i++){
				a=lAlumnos.get(i);
				%>
				  <tr>
			     	 <td><%=a.getNombre()%></td>
			    	  <td><%=a.getApellido()%></td>
			    	  <td><%=a.getDni()%></td>
			    	  <td><%=a.getEdad()%></td>
			    	  <td><%=a.getSexo()%></td>
			    	  <td><%=a.getEmail()%></td>
			    	  
			    	  <td><a href="alumno?id=<%=a.getId()%>">detalle</a></td>
			       </tr>   
				<%
			
			}
			
		
		%>
	  
	  
	  
	  </tbody>
	</table>
	<script>
		//comentari de linea
		/*Esto e sun comentario bloque */
		$(document).ready(function(){
		  console.debug('Esto es un mensaje debug');
		  console.info('Esto es un mensaje info');
		  console.error('Esto es un mensaje error');
		  //cargar libreria o pluging jquery
		  $('#tabla_alumno').dataTable();
		});
	
	</script>
</body>
</html>