package com.controlador.portlet;

import java.io.IOException;
import java.io.Serializable;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Portlet implementation class PortletC
 */
public class PortletC extends GenericPortlet {

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
 
    protected String viewTemplate;

    private static Log _log = LogFactoryUtil.getLog(PortletC.class);
    
    
    @ProcessEvent(qname="{http://www.ThisIsForEvents.com}PortletC")
    public void procesarMievento(EventRequest eventRequest, EventResponse eventResponse) throws PortletException, IOException {
    	
    	Event evento= eventRequest.getEvent();
    	
    	Serializable persona= evento.getValue();
    	//Esto es así, porque aunque el valor del evento en este caso, es un string de forma generica es un Serializable
    	// es con atributos.
    	eventRequest.setAttribute("PortletC",persona);
    	
    	
    }

}
