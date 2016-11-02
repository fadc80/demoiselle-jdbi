package br.gov.serpro.demoiselle.jdbi.service;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.gov.serpro.demoiselle.jdbi.business.ContactListBC;
import br.gov.serpro.demoiselle.jdbi.enity.Contact;

@Path("/contacts")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ContactListREST {

	@Inject
	private ContactListBC bc;

	@POST
	public Response add(Contact contact, @Context UriInfo uri) {
		return Response.created(contactUri(uri, bc.add(contact))).build();
	}
	
	private URI contactUri(UriInfo uri, Long id) {
		return uri.getAbsolutePathBuilder().path(String.valueOf(id)).build();
	}
	
}