package kc;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.logging.Logger;

@RequestScoped
@Path("/secured")
@Produces("application/json")
public class SecuredApi {
	Logger log = Logger.getLogger(getClass());

	@GET
	@Path("/api")
//	@RolesAllowed("test")
	public Response test(@Context SecurityContext securityContext) {
		final String authScheme = (securityContext == null ? "" : securityContext.getAuthenticationScheme());
		log.debug("Get TEST ... " + authScheme);
		final String test = "{\"log\":\"Accessed test secured resource by test role :)\", \"authScheme\":\""+authScheme+"\"}";
		return Response.ok().entity(test).build();
	}

}
