package kc;

import javax.annotation.security.RolesAllowed;
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
@Path("/secured")
@Produces("application/json")
public class SecuredApi2 {
	Logger log = Logger.getLogger(getClass());

	@GET
	@OPTIONS
	@Path("/api2")
	@RolesAllowed("test2")
	public Response test(@Context SecurityContext securityContext) {
		final String authScheme = (securityContext == null ? "" : securityContext.getAuthenticationScheme());
		final boolean isUserInRole = (securityContext == null ? false : securityContext.isUserInRole("test"));
		log.debug("Get TEST ... " + authScheme + "; " + isUserInRole);
		final String test = "{\"log\":\"Accessed test secured resource by test2 role :)\", \"authScheme\":\""
				+ authScheme + "\", \"isUserInRole\":" 
				+ isUserInRole + "}";
		return Response.ok().entity(test).build();
	}

}
