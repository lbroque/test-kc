package rest;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

@Provider
//@Priority(Priorities.USER)
@PreMatching
public class OPTIONSRequestFilter implements ContainerRequestFilter {
	private Logger log = Logger.getLogger(getClass());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		log.debug("OPTIONSRequestFilter");
		if(requestContext.getMethod().equals(javax.ws.rs.HttpMethod.OPTIONS)) {
//			requestContext.abortWith(Response.status(Response.Status.OK).build());
			requestContext.abortWith(Response.status(Response.Status.ACCEPTED).build());
			log.debug("abortWith");
		}
	}

}
