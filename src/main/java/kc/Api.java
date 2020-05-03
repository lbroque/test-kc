package kc;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.logging.Logger;

@RequestScoped
@Produces("application/json")
@Path("/")
public class Api {
	Logger log = Logger.getLogger(getClass());

	@GET
	@OPTIONS
	@Path("/open")
	public Response test(@Context SecurityContext securityContext) {
		final String authScheme = (securityContext == null ? "" : securityContext.getAuthenticationScheme());
		log.debug("Get TEST ... " + authScheme);
		final String test = "{\"log\":\"Accessed test open resource :)\", \"authScheme\":\""+authScheme+"\"}";
		return Response.ok().entity(test).build();
	}

}
