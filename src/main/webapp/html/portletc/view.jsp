<%@page import="com.modelo.entidad.Persona"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<h1>PORTLET C</h1>

<%
String nombre="";
String direccion="";
String telefono="";

Persona persona=(Persona)renderRequest.getAttribute("PortletC");

if(persona!= null){
	nombre=persona.getNombre();
	direccion=persona.getDireccion();
	telefono=String.valueOf(persona.getTelefono());
}	
%>

<form method="post">
	<div><input type="text" value="<%=nombre%>"  /> </div>
	<div><input type="text" value="<%=direccion%>" /></div>
	<div><input type="text" value="<%=telefono%>" /></div>
</form>
<p> Rellena el PortletA y pulsa el botón de PortletC para que se escriba aquí</p>


