package edu.hm.tests.AuthServiceImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.hm.data.User;
import edu.hm.data.User;
import edu.hm.logic.AuthService;
import edu.hm.logic.AuthServiceImpl;
import edu.hm.logic.AuthServiceResult;

public class TestAddUser {
    AuthService auth;
    AuthServiceResult result;

    @Before
    public void setupVariables() {
	auth = new AuthServiceImpl();
	result = null;
    }

    @Test
    public void testEmptyUsername() {
	User user = new User("", "asdfasdf");
	result = auth.addUser(user);
	assertEquals(AuthServiceResult.Bad_Request, result);
	assertEquals("Username is too short.", result.getMessage());
    }

    @Test
    public void testDuplicateUsername() {
	User user1 = new User("Basti", "asdfasdf");
	User user2 = new User("Basti", "asdfasdf");

	result = auth.addUser(user1);
	assertEquals(AuthServiceResult.Created, result);
	assertEquals("User created.", result.getMessage());

	result = auth.addUser(user2);
	assertEquals(AuthServiceResult.Conflict, result);
	assertEquals("Username already in use.", result.getMessage());
    }

    @Test
    public void testEmptyUsernameAndPW() {
	User user = new User("", "");
	result = auth.addUser(user);
	assertEquals(AuthServiceResult.Bad_Request, result);
	assertEquals("Username is too short.", result.getMessage());
    }

    @Test
    public void testEmptyPassword() {
	User user = new User("Basti", "");
	result = auth.addUser(user);
	assertEquals(AuthServiceResult.Bad_Request, result);
	assertEquals("Password is less than 8 characters.", result.getMessage());
    }
}
