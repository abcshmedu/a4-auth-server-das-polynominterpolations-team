package edu.hm.tests.AuthServiceImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.hm.data.User;
import edu.hm.data.UserImpl;
import edu.hm.logic.AuthService;
import edu.hm.logic.AuthServiceImpl;
import edu.hm.logic.AuthServiceResult;

public class TestAddUser {
    AuthService auth;
    AuthServiceResult result;
    
    @Before
    public void setupVariables(){
        auth = new AuthServiceImpl();
        result = null;
    }
    
    @Test
    public void testEmptyUsername(){
        UserImpl user = new UserImpl("", "asdfasdf");
        result = auth.addUser(user);
        assertEquals(AuthServiceResult.Bad_Request, result);
        assertEquals("Username is too short.", result.getMessage());
    }
    
    @Test
    public void testDuplicateUsername(){
        UserImpl user1 = new UserImpl("Basti", "asdfasdf");
        UserImpl user2 = new UserImpl("Basti", "asdfasdf");
        
        result = auth.addUser(user1);
        assertEquals(AuthServiceResult.Created , result);
        assertEquals("User created.", result.getMessage());
        
        result = auth.addUser(user2);
        assertEquals(AuthServiceResult.Conflict, result);
        assertEquals("Username already in use.", result.getMessage());
    }

    @Test
    public void testEmptyUsernameAndPW() {
        UserImpl user = new UserImpl ("", "");
        result = auth.addUser(user);
        assertEquals(AuthServiceResult.Bad_Request, result);
        assertEquals("Username is too short.", result.getMessage());
    }
    
    @Test
    public void testEmptyPassword(){
        UserImpl user = new UserImpl("Basti", "");
        result = auth.addUser(user);
        assertEquals(AuthServiceResult.Bad_Request, result);
        assertEquals("Password is less than 8 characters.", result.getMessage());
    }
}
