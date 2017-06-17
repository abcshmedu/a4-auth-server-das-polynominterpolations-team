package edu.hm.tests.AuthServiceImpl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hm.data.User;
import edu.hm.logic.AuthService;
import edu.hm.logic.AuthServiceImpl;
import edu.hm.logic.AuthServiceResult;

public class TestVerifyToken {
    AuthService auth;
    AuthServiceResult result;
    
    @Before
    public void setupVariables(){
        auth = new AuthServiceImpl();
        result = null;
    }
    
    @Test
    public void testValidToken() {
        User user = new User("Basti", "asdfasdf");
        String token;
        
        auth.addUser(user);
        result = auth.loginUser(user);
        token = result.getToken();
        result = auth.verifyToken(token);

        assertEquals(AuthServiceResult.OK, result);
        assertEquals("Token is valid.", result.getMessage());
    }
    
    @Test
    public void testInvalidToken() {
        User user = new User("Basti", "asdfasdf");
        String token = "NotAValidToken";
        
        auth.addUser(user);
        result = auth.loginUser(user);
        result = auth.verifyToken(token);

        assertEquals(AuthServiceResult.Bad_Request, result);
        assertEquals("Token is invalid.", result.getMessage());
    }

}
