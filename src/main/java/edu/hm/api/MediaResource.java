package edu.hm.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.hm.data.UserImpl;
import edu.hm.logic.AuthService;
import edu.hm.logic.AuthServiceImpl;
import edu.hm.logic.AuthServiceResult;

/** Dies ist unsere Implementierung der REST-API.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
@Path("account")
public class MediaResource {
    private static AuthService service = new AuthServiceImpl();
    
    @POST
    @Path("user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(final UserImpl user){
        AuthServiceResult asr = service.addUser(user);
        
        return Response.status(asr.getStatus()).entity(asr.getMessage()).build();
    }
    
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(final UserImpl user){
        AuthServiceResult asr = service.loginUser(user);
        
        return Response.status(asr.getStatus()).entity(asr.getMessage()).header("Token", asr.getToken()).build();
    }
}