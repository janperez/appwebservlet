<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.ipartek.formacion.egunon.controller.AlumnoServlet"%>
<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<%

   Alumno a=(Alumno)request.getAttribute("alumno");
   if (a==null)
   {
	   a=new Alumno();
   }
%>
<%@include file="mensaje.jsp" %>
<a href="alumno">Volver al listado</a>
  <form action="alumno" method="post">
    
    <label name="eid">ID</label>
    <input type="text" name="id_oculto" disabled value="<%=a.getId()%>"><br/>
    
    <label name="ename">Nombre</label>
    <input type="text" name="name" value="<%=a.getNombre()%>"><br/>
    
    <label name="eapellido">Apellido</label>
    <input type="text" name="apellido" value="<%=a.getApellido()%>"><br/>
    
    <label name="eapellido">Dni</label>
    <input type="text" name="dni" value="<%=a.getDni()%>"><br/>
    
    <label name="eedad">Edad</label>
    <input type="text" name="edad" value="<%=a.getEdad()%>"><br/>
    
    <label name="eemail">Email</label>
    <input type="text" name="email" value="<%=a.getEmail()%>"><br/>
    
    <input type="submit" name="op" value="<%=AlumnoServlet.OP_NUEVO_ALUMNO %>">
    
    
    </form>
  
</body>
</html>