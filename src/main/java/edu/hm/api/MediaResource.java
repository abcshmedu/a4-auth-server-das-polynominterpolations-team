package edu.hm.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.hm.data.User;
import edu.hm.logic.AuthService;
import edu.hm.logic.AuthServiceImpl;
import edu.hm.logic.AuthServiceResult;

/** Dies ist unsere Implementierung der REST-API für den Auth-Server der ShareIT-Anwendung.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
@Path("account")
public class MediaResource {
	/**
	 * Referenz auf die Logik des Auth-Servers.
	 */
    private static AuthService service = new AuthServiceImpl();
    
    /**
     * Eine Methode zur Erstellung eines neuen Users.
     * @param user Der neu zu erstellende User
     * @return Ein Response-Objekt, welches den Status und Details der ausgeführten Anfrage enthält
     */
    @POST
    @Path("user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(final User user){
        AuthServiceResult asr = service.addUser(user);
        return Response.status(asr.getStatus()).entity(asr.getMessage()).build();
    }
    
    /**
     * Eine Methode um einen User einzuloggen.
     * @param user Der einzuloggende User
     * @return Ein Response-Objekt, welches den Status und Details der ausgeführten Anfrage enthält
     */
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(final User user){
        AuthServiceResult asr = service.loginUser(user);
        return Response.status(asr.getStatus()).entity(asr.getMessage()).header("Token", asr.getToken()).build();
    }
    
    /**
     * Diese Methode verifiziert ein Token, welches der User bekommt, nachdem er sich eingeloggt hat.
     * @param token Das Token des Users
     * @return Ein Response-Objekt, welches den Status und Details der ausgeführten Anfrage enthält
     */
    @POST
    @Path("verify")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verifyToken(@HeaderParam("Token") final String token){
        AuthServiceResult asr = service.verifyToken(token);
        return Response.status(asr.getStatus()).entity(asr.getMessage()).header("JWT", asr.getJwt()).build();
    }
}