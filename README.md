
# Use case

Here is the use case :

A GUI client (the client, ie. a simple vuejs page) get a TOKEN from a keycloak server.

With this TOKEN we want to access a secured resource (the service, ie. a simple Thorntail jax-rs app).

Why it doesn't work ????

# Launch

$ `docker-compose build && docker-compose up -d`

Launch a browser at <http://localhost:8282/>

Click "get token" to authenticate

click "test service" to see if the token make you access the secured service.

## keycloak

Launch a browser at <http://localhost:8181/>
Log as `admin` ; password is `keycloak` (see file docker-compose.yml).

Configure a realm (see keycloak doc <https://www.keycloak.org/docs/latest/server_admin/index.html):>

- realm : "Test"
- client : "IHM" (client Protocol = openid-connect ; acces-type = public ; root url = <http://localhost:8282> ; redirect URI = *; web origins = \*)
- client : "service" (client Protocol = openid-connect ; acces-type = bearer-only )
- user : "tester" (credential => set passwd ; not temporary)
- role : "test" (go to user and role Mapping ; add role.)

## secured-service

Eventually replace keycloak.json in src/main/resource/ with the one you have in keycloak admin (clients/service/Installation).
