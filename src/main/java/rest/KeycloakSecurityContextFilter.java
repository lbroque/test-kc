package rest;

import java.io.IOException;
import java.security.Principal;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;
import org.keycloak.KeycloakSecurityContext;
import org.wildfly.swarm.keycloak.deployment.KeycloakSecurityContextAssociation;

/**
 * @author lbroque
*  https://github.com/thorntail/thorntail-examples/tree/master/security/keycloak
 */
@Provider
@ApplicationScoped
public class KeycloakSecurityContextFilter implements ContainerRequestFilter {
	private Logger log = Logger.getLogger(getClass());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// KeyCloak sets a principal name to the access token subject
		// which is a unique (UUID/etc) identifier. The custom security context
		// sets the principal name to the preferred user name instead.

		log.debug("KeycloakSecurityContextFilter "+requestContext.getHeaderString("Authorization"));
        final KeycloakSecurityContext kcSecurityContext = KeycloakSecurityContextAssociation.get();
        if(kcSecurityContext == null) {
        	log.warn("Can't get KeycloakSecurityContextAssociation !!!!");
        	return;
        }
        final SecurityContext securityContext = requestContext.getSecurityContext();
		// Simplifying for the demo purposes only
		final Principal kcPrincipal = () -> kcSecurityContext.getToken().getPreferredUsername();
//
//		final Principal kcPrincipal = new Principal() {
//			
//			@Override
//			public String getName() {
//				return KeycloakSecurityContextAssociation.get().getToken().getPreferredUsername();
//			}
//		};
		
		log.debug("kcPrincipal : "+kcPrincipal.getName());

		requestContext.setSecurityContext(new SecurityContext() {

			@Override
			public Principal getUserPrincipal() {
				return kcPrincipal;
			}

			@Override
			public boolean isUserInRole(String role) {
				return securityContext.isUserInRole(role);
			}

			@Override
			public boolean isSecure() {
				return securityContext.isSecure();
			}

			@Override
			public String getAuthenticationScheme() {
				return securityContext.getAuthenticationScheme();
			}
		});
		log.debug("kcSecurityContext : "+kcSecurityContext);
		log.debug("getToken() : "+kcSecurityContext.getToken());
        requestContext.getHeaders().add("Realm", kcSecurityContext.getRealm());
	}
}
