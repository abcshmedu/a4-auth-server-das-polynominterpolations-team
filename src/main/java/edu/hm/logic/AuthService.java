package edu.hm.logic;

import edu.hm.data.User;

public interface AuthService {
	/**
	 * Logik, um einen neuen User hinzuzufügen.
	 * @param user Der User, der hinzugefügt werden soll
	 * @return AuthServiceResult mit den Details zu Fehlern und Ausführung
	 */
    AuthServiceResult addUser(User user);
    
	/**
	 * Logik, um einen User einzuloggen.
	 * @param user Der User, der eingeloggt werden soll
	 * @return AuthServiceResult mit den Details zu Fehlern und Ausführung
	 */
    AuthServiceResult loginUser(User user);
    
	/**
	 * Logik, ein Token eines Users zu verifizieren.
	 * @param token Das Token, welches verifiziert werdne soll
	 * @return AuthServiceResult mit den Details zu Fehlern und Ausführung
	 */
    AuthServiceResult verifyToken(String token);
}
