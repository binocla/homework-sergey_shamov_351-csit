package com.orioninc.resources;

import com.orioninc.models.Person;
import io.smallrye.common.constraint.NotNull;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Path("/api/people")
public class MainResource {

    // Map for storing people. This is NOT FOR PRODUCTION
    private final Map<Long, Person> peopleMap = new LinkedHashMap<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all people", description = "Get all people")
    @APIResponse(responseCode = "200", description = "All people", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Person.class)))
    @APIResponse(responseCode = "400", description = "Bad request")
    @APIResponse(responseCode = "500", description = "Internal server error")
    public List<Person> getAllPeople() {
        return peopleMap.values().stream().toList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get person by id", description = "Get person by id")
    @APIResponse(responseCode = "200", description = "Person", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Person.class)))
    @APIResponse(responseCode = "400", description = "Bad request")
    @APIResponse(responseCode = "404", description = "Person not found")
    @APIResponse(responseCode = "500", description = "Internal server error")
    public Person getPersonById(@NotNull @Positive @PathParam("id") Long id) {
        if (peopleMap.containsKey(id)) {
            return peopleMap.get(id);
        } else {
            throw new NotFoundException("Person not found");
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add person", description = "Add person")
    @APIResponse(responseCode = "200", description = "Added person")
    @APIResponse(responseCode = "400", description = "Bad request")
    @APIResponse(responseCode = "500", description = "Internal server error")
    public Long addPerson(@NotNull @Valid Person person) {
        peopleMap.put(1L, person);
        return 1L;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(summary = "Update person", description = "Update person")
    @APIResponse(responseCode = "200", description = "Updated person", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Person.class)))
    @APIResponse(responseCode = "400", description = "Bad request")
    @APIResponse(responseCode = "404", description = "Person not found")
    @APIResponse(responseCode = "500", description = "Internal server error")
    public Person updatePerson(@NotNull @Positive @PathParam("id") Long id, @NotNull @Valid Person person) {
        if (peopleMap.containsKey(id)) {
            peopleMap.put(id, person);
            return person;
        } else {
            throw new NotFoundException("Person not found");
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(summary = "Delete person", description = "Delete person")
    @APIResponse(responseCode = "200", description = "Deleted person")
    @APIResponse(responseCode = "400", description = "Bad request")
    @APIResponse(responseCode = "404", description = "Person not found")
    @APIResponse(responseCode = "500", description = "Internal server error")
    public Person deletePerson(@NotNull @Positive @PathParam("id") Long id) {
        if (peopleMap.containsKey(id)) {
            return peopleMap.remove(id);
        } else {
            throw new NotFoundException("Person not found");
        }
    }
}