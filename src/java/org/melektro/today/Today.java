/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.melektro.today;

import static com.melektro.Tools.ExtAPIs.GetToday;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author marius
 */
@Path("today")
public class Today {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Today
     */
    public Today() {
    }

    /**
     * Retrieves representation of an instance of org.melektro.today.Today
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() throws IOException {
        //var obj = JSON.parse(GetToday("",""));
        String result = GetToday("","");
        return "From wikipedia Length=" + result.length() +" text:"+ result;
    }

    /**
     * PUT method for updating or creating an instance of Today
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
