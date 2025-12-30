package com.poly;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestRouter {

    PersonService service = new PersonServiceImpl();

    // GET : retourner toutes les personnes
    @GET
    public javax.ws.rs.core.Response getAllPersons() {
        try {
            List<Person> persons = service.getAllPersons();
            return javax.ws.rs.core.Response.ok(persons).build();
        } catch (Throwable t) {
            java.io.StringWriter sw = new java.io.StringWriter();
            t.printStackTrace(new java.io.PrintWriter(sw));
            return javax.ws.rs.core.Response.status(500).entity(sw.toString()).build();
        }
    }

    // GET : personne par ID
    @GET
    @Path("/{id}")
    public Person getPerson(@PathParam("id") int id) {
        return service.getPerson(id);
    }

    // GET : personne par nom
    @GET
    @Path("/name/{name}")
    public Person getPersonByName(@PathParam("name") String name) {
        return service.getPersonByName(name);
    }

    // PUT : ajouter une personne
    @PUT
    public String addPerson(Person person) {
        service.addPerson(person);
        return "Person added";
    }

    // POST : modifier une personne
    @POST
    public String updatePerson(Person person) {
        service.updatePerson(person);
        return "Person updated";
    }

    // DELETE : supprimer une personne
    @DELETE
    @Path("/{id}")
    public String deletePerson(@PathParam("id") int id) {
        service.deletePerson(id);
        return "Person deleted";
    }
}