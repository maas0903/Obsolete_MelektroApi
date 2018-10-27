package org.melektro.NatoAlphabet;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import static com.melektro.Tools.Nato.GetNato;

/**
 *
 * @author marius
 */
@Path("nato")
public class NatoAlphabet {

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getnato/")
    public String OutputString(@QueryParam("str") @DefaultValue("") String inputStr) {
        return GetNato(inputStr);
    }
}
