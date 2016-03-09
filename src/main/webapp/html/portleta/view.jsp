<%@page import="com.modelo.entidad.Persona"%>
<%@page import="com.controlador.portlet.PortletA"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<portlet:actionURL name="enviarEventoAPortletB" var="urlEnviarEventoB"/>
<portlet:actionURL name="enviarEventoAPortletC" var="urlEnviarEventoC"/>

<h1>PORTLET A</h1>

<%
String nombre="";
String direccion="";
String telefono="";

Persona persona=(Persona)renderRequest.getAttribute("datos");

if(persona!= null){
	nombre=persona.getNombre();
	direccion=persona.getDireccion();
	telefono=String.valueOf(persona.getTelefono());
}

%>
<form>
	<div><input type ="text" name="nombre" value="<%=nombre%>"/></div>
	<div><input type ="text" name="direccion" value="<%=direccion%>"/></div>
	<div><input type ="text" name="telefono"value="<%=telefono%>" /></div>
	
	<input type="submit"  formaction="<%=urlEnviarEventoB%>" formmethod="post" value="PortletB" /> 
	<input type="submit"  formaction="<%=urlEnviarEventoC%>" formmethod="post" value="PortletC" /> 
</form>