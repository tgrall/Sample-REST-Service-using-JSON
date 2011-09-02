package com.grallandco.employee.service.rest.impl;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.grallandco.employee.service.converter.EmployeeConverter;
import com.grallandco.employee.service.model.Employee;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;
import javax.enterprise.inject.Default;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@Path("/hr/")
public class EmployeeRessource {
    
    private static Map<String, Employee> employeeList = new TreeMap();
    

    @GET
    @Produces({"application/json"})
    @Path("/employee/{employeeEmail}/")
    public EmployeeConverter getEmployee(@PathParam("employeeEmail") String email) {
        // no error management
        Employee emp = employeeList.get(email);
        EmployeeConverter converter = new EmployeeConverter(emp);
        return converter;
    }
    
    @GET
    @Produces({"application/json"})
    @Path("/employees/")
    public List<EmployeeConverter> getEmployees(@QueryParam("nbOfItem") @DefaultValue("999") int nbOfItem  ) {
        List employees =  new ArrayList();
        int nbOfEmp = 1;
        for (Iterator it = employeeList.values().iterator(); (it.hasNext() && nbOfEmp <= nbOfItem );) {
            
            Employee emp = (Employee)it.next();
            EmployeeConverter converter =  new EmployeeConverter(emp);
            employees.add(converter);
            nbOfEmp = nbOfEmp + 1;
        }
        
        return employees;
        
        
    }
    
    static 
    {
        Employee emp = new Employee();
        emp.setEmail("jdoe@example.com");
        emp.setFirstName("John");
        emp.setLastName("Doe");
        employeeList.put(emp.getEmail(), emp);
        
        emp = new Employee();
        emp.setEmail("rmiles@example.com");
        emp.setFirstName("Richard");
        emp.setLastName("Miles");        
        employeeList.put(emp.getEmail(), emp);

        emp = new Employee();
        emp.setEmail("mmajor@example.com");
        emp.setFirstName("Mary");
        emp.setLastName("Major");  
        employeeList.put(emp.getEmail(), emp);

    
    }
    
}