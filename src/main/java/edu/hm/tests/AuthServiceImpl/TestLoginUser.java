package edu.hm.tests.AuthServiceImpl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hm.data.User;
import edu.hm.logic.AuthService;
import edu.hm.logic.AuthServiceImpl;
import edu.hm.logic.AuthServiceResult;

public class TestLoginUser {
    AuthService auth;
    AuthServiceResult result;
    
    @Before
    public void setupVariables(){
        auth = new AuthServiceImpl();
        result = null;
    }

    @Test
    public void testSuccessfullLogin() {
        User user1 = new User("Basti", "asdfasdf");
        
        result = auth.addUser(user1);
        assertEquals(AuthServiceResult.Created , result);
        assertEquals("User created.", result.getMessage());
        
        result = auth.loginUser(user1);
        assertEquals(AuthServiceResult.OK, result);
        assertEquals("Successfully logged in.", result.getMessage());
    }

    @Test
    public void testLoginWithUnknownUser(){
        User user1 = new User("Basti", "asdfasdf");
        User user2 = new User("Peter", "asdfasdf");
        
        auth.addUser(user1);
        
        result = auth.loginUser(user2);
        assertEquals(AuthServiceResult.Not_Found, result);
        assertEquals("Username was not found.", result.getMessage());
    }
    
    @Test
    public void testTokenGeneration(){
        User user1 = new User("Basti", "asdfasdf");
        
        auth.addUser(user1);
        
        result = auth.loginUser(user1);
        assertNotEquals(null, result.getToken());
    }
    
    @Test
    public void testMultiLogin(){
        User user = new User("Basti", "asdfasdf");
        
        auth.addUser(user);
        
        result = auth.loginUser(user);
        assertEquals(AuthServiceResult.OK, result);
        assertEquals("Successfully logged in.", result.getMessage());

        result = auth.loginUser(user);
        assertEquals(AuthServiceResult.Conflict, result);
        assertEquals("User already logged in.", result.getMessage());
    }
}
