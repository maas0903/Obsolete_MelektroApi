/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Marius
 */
@javax.ws.rs.ApplicationPath("apies")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(io.swagger.jaxrs2.SwaggerSerializers.class);
        resources.add(io.swagger.jaxrs2.integration.resources.AcceptHeaderOpenApiResource.class);
        resources.add(io.swagger.jaxrs2.integration.resources.OpenApiResource.class);
        resources.add(org.melektro.NatoAlphabet.NatoAlphabet.class);
        resources.add(org.melektro.random.Random.class);
        resources.add(org.melektro.shift.Shift.class);
        resources.add(org.melektro.today.Today.class);
    }
    
}
