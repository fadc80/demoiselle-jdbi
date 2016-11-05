package coding.addicts.simple.list.service;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import coding.addicts.simple.contact.list.business.ContactListBC;
import coding.addicts.simple.list.enity.Contact;

@Path("/contacts")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ContactListREST {

	@Inject
	private ContactListBC bc;

	@POST
	public Response add(Contact contact, @Context UriInfo uri) {
		return Response.created(contactUri(uri, bc.add(contact).getId())).entity(contact).build();
	}

	@GET
	public Response finalAll(Contact contact) {
		return Response.ok().entity(bc.findAll()).build();
	}	
	
	private URI contactUri(UriInfo uri, Long id) {
		return uri.getAbsolutePathBuilder().path(String.valueOf(id)).build();
	}

}
