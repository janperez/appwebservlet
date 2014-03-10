<%@page import="com.ipartek.pruebas.bean.Calificacion"%>
<%@page import="com.ipartek.formacion.egunon.controller.AlumnoServlet"%>
<%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@page import="com.ipartek.pruebas.bean.Calificacion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%	String title=(String) request.getAttribute("titulo");
if(title==null) {
	title="Crear Alumno | Egunon Euskadi";	
}
	%>
<title><%=title%></title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<!-- Incluir jquery y datatables -->

</head>
<body>
	<%@include file="cabecera.jsp"%>

	<%
		Alumno alumno = (Alumno) request.getAttribute("detalleAlumno");
		boolean nuevo = false;
		String method = (String) request.getAttribute("method");

		if (alumno == null) {
			nuevo = true;
			
		}
		if (method == null) {
			method = "POST";
		}
		String h1=(String) request.getAttribute("h1");
		if(h1==null){
			h1="Crear alumno";
		}
	%>
	<a id="volver" href="alumno"><img src="flecha_azul.jpg">Volver Listado</a>
	<br>
	<br>
	<h1><%=h1%>
	</h1>

	<%@include file="mensaje.jsp"%>

	<form method="<%=method%>" action="alumno">
		<!-- ID -->
	<div id="alumno">
		<label for="id">ID</label> <input class="modificar" type="text"
			name="id_show" disabled
			value="<%=(alumno != null) ? alumno.getId() : request.getAttribute("id")%>" />
		<!-- Este campo se pone como oculto ya que al poner el id disabled no viaja en el formulario -->
		<!-- De esta forma si viaja y no se puede modificar en el formulario para poder manejar el alumno que se est� mostrando. -->
		<input type="hidden" name="id"
			value="<%=(alumno != null) ? alumno.getId() : request.getAttribute("id")%>" />
		<br>
		<!-- Nombre -->
		<label for="id">Nombre</label> <input class="modificar" type="text"
			name="nombre"
			value="<%=(alumno != null) ? alumno.getNombre() : request.getAttribute("nombre")%>" />
		<br> <label for="apellido">Apellido</label> <input
			class="modificar" type="text" name="apellido"
			value="<%=(alumno != null) ? alumno.getApellido() : request.getAttribute("apellido")%>" />
		<br> <label for="dni">DNI</label> <input class="modificar"
			type="text" name="dni"
			value="<%=(alumno != null) ? alumno.getDni() : request.getAttribute("dni")%>" />
		<br> <label for="email">Email</label> <input class="modificar"
			type="text" name="email"
			value="<%=(alumno != null) ? alumno.getEmail() : request.getAttribute("email")%>" />
		<br> <label for="edad">Edad</label> <input class="modificar"
			type="text" name="edad"
			value="<%=(alumno != null) ? alumno.getEdad() : request.getAttribute("edad")%>" />
		<br>
		</div>
		<%if(alumno.getCalificaciones()!=null){ %>
			<h3>Calificaciones:</h3>
		<div class="calificaciones">
			<%for(Calificacion c: alumno.getCalificaciones() ){ %>
				<label for="id_cal">Id Calificaci�n</label>
				<input type="text" name="id_cal" value="<%=c.getId() %>" />
				<br>
				<label for="calificacion">Calificaci�n</label>
				<input type="text" name="calificacion" value="<%=c.getCalificacion() %>" />
				<br>
				<label for="fecha_creacion">Fecha creaci�n</label>
				<%
					// Arreglar el formato de la fecha que llega de la bbdd en long
					Date cDate= new Date(c.getfCreate() * 1000); 
					SimpleDateFormat sdf3= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
					String fechaCreacion= sdf3.format(cDate);
				%>
				<input type="text" name="fecha_creacion" value="<%=fechaCreacion%>" />
				<br><br>
			<%} %>
		</div>
		<%} %>
	<br>

		<%
			if (nuevo) {
		%>
			<input type="submit" name="op" value="<%=AlumnoServlet.OP_NUEVO_ALUMNO %>" />
		<%
			} else {
		%>
			 <input type="submit" name="op" value="<%=AlumnoServlet.OP_MODIFICAR_ALUMNO %>" />
			<input type="submit" name="op" value="<%=AlumnoServlet.OP_ELIMINAR_ALUMNO%>" 
				   onClick="if(!confirm('�Seguro que deseas eliminar el registro?')){return false;}">	
		<%
			}
		%>
		
	</form>
	<%@include file="footer.jsp"%>
</body>
</html>