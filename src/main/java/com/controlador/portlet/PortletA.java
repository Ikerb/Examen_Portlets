package com.controlador.portlet;

import java.io.IOException;
import java.io.Serializable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessAction;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.modelo.entidad.Persona;

/**
 * Portlet implementation class PortletA
 */
public class PortletA extends GenericPortlet {

	public static final String TELEFONO = "telefono";

	public static final String DIRECCION = "direccion";

	public static final String NOMBRE = "nombre";

	protected String viewTemplate;

    private static Log _log = LogFactoryUtil.getLog(PortletA.class);
	
    public void init() {
        viewTemplate = getInitParameter("view-template");
    }

    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        include(viewTemplate, renderRequest, renderResponse);
    }

    protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
            _log.error(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }
    
    // processAction para el evento en B
    @ProcessAction(name="enviarEventoAPortletB")
    public void enviarEventoAPortletB(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
    	//1. Recogemos los parametros introducidos por el usuario
    	String nombre= actionRequest.getParameter(NOMBRE);
    	String direccion=actionRequest.getParameter(DIRECCION);
    	String telefono=actionRequest.getParameter(TELEFONO);
    	//creamos el objeto persona y cambiamos de tipo para que se ajuste a los atributos del objeto.
    	Persona persona=new Persona(nombre, direccion, Integer.parseInt(telefono));
    	
    	QName qname= new QName("http://www.ThisIsForEvents.com", "PortletB", "x");
    	//hacemos el setEvent
    	actionResponse.setEvent(qname,persona);
    	
    	actionRequest.setAttribute("datos", persona);
    	
    }
    
    // processAction para el evento en C
    @ProcessAction(name="enviarEventoAPortletC")
    public void enviarEventoAPortletC(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
    	//1. Recogemos los parametros introducidos por el usuario
    	String nombre= actionRequest.getParameter(NOMBRE);
    	String direccion=actionRequest.getParameter(DIRECCION);
    	String telefono=actionRequest.getParameter(TELEFONO);
    	//creamos el objeto persona y cambiamos de tipo para que se ajuste a los atributos del objeto.
    	Persona persona=new Persona(nombre, direccion, Integer.parseInt(telefono));
    	
    	QName qname= new QName("http://www.ThisIsForEvents.com", "PortletC", "x");
    	//hacemos el setEvent
    	actionResponse.setEvent(qname,persona);
    	
    	actionRequest.setAttribute("datos", persona);
    }
    
    // estos dos metodos son para mantener los Portlet en A y que no se pierdan a pesar de mandarlos a B o C.
    
    @ProcessEvent(qname="{http://www.ThisIsForEvents.com}PortletB")
    public void procesarMievento1(EventRequest eventRequest, EventResponse eventResponse) throws PortletException, IOException {
    	
    	Event evento= eventRequest.getEvent();
    	
    	Serializable persona= evento.getValue();
    	//Esto es así, porque aunque el valor del evento en este caso, es un string de forma generica es un Serializable
    	// es con atributos.
    	eventRequest.setAttribute("datos",persona);
    }
    
    @ProcessEvent(qname="{http://www.ThisIsForEvents.com}PortletC")
    public void procesarMievento2(EventRequest eventRequest, EventResponse eventResponse) throws PortletException, IOException {
    	
    	Event evento= eventRequest.getEvent();
    	
    	Serializable persona= evento.getValue();
    	//Esto es así, porque aunque el valor del evento en este caso, es un string de forma generica es un Serializable
    	// es con atributos.
    	eventRequest.setAttribute("datos",persona);
    	
    	
    }
 
    
}
