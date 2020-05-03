package rest;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

@Provider
@ApplicationScoped
public class CORSFilter implements ContainerResponseFilter {
	private Logger log = Logger.getLogger(getClass());

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		log.debug("CORS filter ...");
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type");
	}

}
