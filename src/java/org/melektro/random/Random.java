/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.melektro.random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melektro.Tools.RandomEMSet;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * REST Web Service
 *
 * @author Marius
 */
@Path("random")
public class Random {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Random
     */
    public Random() {
    }

    /**
     * Retrieves representation of an instance of org.melektro.random.Random
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<String> ThisSet;
        ThisSet = RandomEMSet.makeARandomEMSet();
        return new GsonBuilder().setPrettyPrinting().create().toJson(ThisSet);
    }

    /**
     * PUT method for updating or creating an instance of Random
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
