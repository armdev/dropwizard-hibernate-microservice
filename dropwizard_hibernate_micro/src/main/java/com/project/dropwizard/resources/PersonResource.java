/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.dropwizard.resources;

import com.project.dropwizard.dao.PersonDAO;
import com.project.dropwizard.model.Person;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/people/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
//access url http://localhost:9000/people/1
    private final PersonDAO peopleDAO;

    public PersonResource(PersonDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GET
    @UnitOfWork
    public Person getPerson(@PathParam("personId") LongParam personId) {
        return findSafely(personId.get());
    }

    private Person findSafely(Long personId) {
        return peopleDAO.findById(personId).orElseThrow(() -> new NotFoundException("No such user."));
    }

}
