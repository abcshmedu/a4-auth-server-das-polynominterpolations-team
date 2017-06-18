package edu.hm.logic;

import java.util.HashMap;
import java.util.Map;

import edu.hm.data.ShareItAccess;
import edu.hm.data.User;
import edu.hm.data.UserInformation;

public class AuthServiceImpl implements AuthService {
    /** Referenz auf einen Token-Generator, welcher Tokens für User erzeugt. */
    private final TokenGenerator generator = new TokenGenerator();

    /** Liste für die Tokens. Map<Token, Username> */
    private final Map<String, String> tokens = new HashMap<>();

    /** Liste für die User. Map<Username, User> */
    private final Map<String, User> users = new HashMap<>();

    /** Map<User, JWT> */
    private final Map<User, UserInformation> userinfos = new HashMap<>();

    @Override
    public AuthServiceResult addUser(final User user) {
	AuthServiceResult result = null;

	if (result == null) // testen ob User gültig ist
	    result = testUser(user);

	// falls die Tests erfolgreich sind neuen User erstellen
	if (result == AuthServiceResult.OK) {
	    users.put(user.getUserName(), user);
	    userinfos.put(user, new UserInformation(ShareItAccess.USER));
	    result = AuthServiceResult.Created;
	    result.setMessage("User created.");
	}
	return result;
    }

    @Override
    public AuthServiceResult loginUser(User user) {
	AuthServiceResult result = null;
	String token = null;

	if (result == null)
	    result = compareCredentials(user);

	// Die User-Daten sind gültig
	if (result == AuthServiceResult.OK)
	    if (!tokens.containsValue(user.getUserName())) {
		token = generator.generateToken();
		tokens.put(token, user.getUserName());
		result.setToken(token);
	    }
	    else {
		result = AuthServiceResult.Conflict;
		result.setMessage("User already logged in.");
	    }

	return result;
    }

    @Override
    public AuthServiceResult verifyToken(final String token) {
	AuthServiceResult result = null;

	// Testen ob das Token schon in der Datenbank ist

	if (tokens.containsKey(token)) {
	    String username = tokens.get(token);
	    User user = users.get(username);
	    UserInformation jwt = userinfos.get(user);

	    result = AuthServiceResult.OK;
	    result.setMessage("Token is valid.");
	    result.setJwt(jwt);
	}
	else {
	    result = AuthServiceResult.Bad_Request;
	    result.setMessage("Token is invalid.");
	}
	return result;
    }

    /** Diese Methode testet ob ein User gültig ist.
     * 
     * @param user
     *            Der User der getestet werden soll.
     * @return AuthServiceResult mit den Informationen ob es funktioniert hat */
    private AuthServiceResult testUser(final User user) {
	AuthServiceResult result = null;

	if (result == null) // Testen des Username
	    result = testUserName(user);

	if (result == null) // Testen des Passwords
	    result = testPassword(user);

	if (result == null) { // if all tests were ok
	    result = AuthServiceResult.OK;
	    result.setMessage("User created.");
	}

	return result;
    }

    /** Diese Methode testet, ob der Username gültig ist.
     * 
     * @param user
     *            Der User, dessen Username getestet werden soll
     * @return AuthServiceResult mit Details zum Test */
    private AuthServiceResult testUserName(final User user) {
	AuthServiceResult result = null;

	// Testen ob Username lang genug ist
	if (result == null) {
	    if (user.getUserName().length() == 0) {
		result = AuthServiceResult.Bad_Request;
		result.setMessage("Username is too short.");
	    }
	}

	// Testen ob Username schon vorhanden
	if (result == null) {
	    if (users.containsKey(user.getUserName())) {
		result = AuthServiceResult.Conflict;
		result.setMessage("Username already in use.");
	    }
	}

	return result;
    }

    /** Diese Methode testet, ob das Passwort gültig ist.
     * 
     * @param user
     *            Der User, dessen Passwort getestet werden soll
     * @return AuthServiceResult mit Details zum Test */
    private AuthServiceResult testPassword(final User user) {
	AuthServiceResult result = null;
	String password = user.getPassword();

	if (password.length() < 8) {
	    result = AuthServiceResult.Bad_Request;
	    result.setMessage("Password is less than 8 characters.");
	}

	return result;
    }

    /** Diese Methode vergleicht die Userdaten, des einzloggenden Users mit
     * denen in der Datenbank.
     * 
     * @param user
     *            Der User, der sich anmelden will
     * @return AuthServiceResult mit den Details des Tests */
    private AuthServiceResult compareCredentials(final User user) {
	AuthServiceResult result = null;
	User userInDatabase = users.get(user.getUserName());

	// Testen, ob der User in der Datenbank vorhanden ist
	if (result == null) {
	    if (userInDatabase == null) {
		result = AuthServiceResult.Not_Found;
		result.setMessage("Username was not found.");
	    }
	}

	// Testen, ob das Passwort des Users korrekt ist
	if (result == null) {
	    if (!user.getPassword().equals(userInDatabase.getPassword())) {
		result = AuthServiceResult.Bad_Request;
		result.setMessage("Password is incorrect.");
	    }
	}

	// Falls kein Test fehlschlägt, Erfolgs-Nachricht erstellen
	if (result == null) {
	    result = AuthServiceResult.Correct_User_Credentials;
	    result.setMessage("Credentials are OK.");
	}

	return result;
    }
}
