package rest;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.auth.LoginConfig;

@ApplicationPath("/rest")
@DeclareRoles({ "test", "test2" })
@LoginConfig(authMethod = "MP-JWT", realmName = "Test")
public class RestApplication extends Application {

}
