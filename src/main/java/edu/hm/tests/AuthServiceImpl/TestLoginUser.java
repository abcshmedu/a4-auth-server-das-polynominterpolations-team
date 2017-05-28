package edu.hm.tests.AuthServiceImpl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hm.data.UserImpl;
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
        UserImpl user1 = new UserImpl("Basti", "asdfasdf");
        
        result = auth.addUser(user1);
        assertEquals(AuthServiceResult.Created , result);
        assertEquals("User created.", result.getMessage());
        
        result = auth.loginUser(user1);
        assertEquals(AuthServiceResult.OK, result);
        assertEquals("Successfully logged in.", result.getMessage());
    }

    @Test
    public void testLoginWithUnknownUser(){
        UserImpl user1 = new UserImpl("Basti", "asdfasdf");
        UserImpl user2 = new UserImpl("Peter", "asdfasdf");
        
        auth.addUser(user1);
        
        result = auth.loginUser(user2);
        assertEquals(AuthServiceResult.Not_Found, result);
        assertEquals("Username was not found.", result.getMessage());
    }
    
    @Test
    public void testTokenGeneration(){
        UserImpl user1 = new UserImpl("Basti", "asdfasdf");
        
        auth.addUser(user1);
        
        result = auth.loginUser(user1);
        assertNotEquals(null, result.getToken());
        System.out.println(result.getToken());
    }
}
