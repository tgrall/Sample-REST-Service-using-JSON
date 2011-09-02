package com.grallandco.employee.service.rest.impl;

import com.grallandco.employee.service.converter.EmployeeConverter;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

@Provider
public class JAXBContextResolver implements ContextResolver< JAXBContext > {

    private JAXBContext context;
    private Class[] types = {EmployeeConverter.class};

    public JAXBContextResolver() throws Exception {
        this.context = new JSONJAXBContext(JSONConfiguration.mapped().arrays("employee").build(),
                types);

    }

    public JAXBContext getContext(Class<?> objectType) {
        System.out.println("TUG CALL");
        for (Class type : types) {
            if (type == objectType) {
                return context;
            }
        }
        
        
        return null;
    }
}
