package edu.hm.data;

/** Dies ist die User-Klasse.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class User {
    /** Alias-Name des Users. */
    private String userName;

    /** Passwort des Users. */
    private String password;

    public User() {}

    /** Custom-Ctor für einen neuen User.
     * 
     * @param userName
     *            Der gewünschte Alias-Name
     * @param password
     *            Das gewünschte Passwort */
    public User(final String userName, final String password) {
	this.userName = userName;
	this.password = password;
    }

    /** Diese Methode liefert den Username zurück.
     * 
     * @return Username des Users */
    public String getUserName() {
	return userName;
    }

    /** Diese Methode liefert das Passwort zurück.
     * 
     * @return Passwort des User */
    public String getPassword() {
	return password;
    }

    @Override
    public String toString() {
	return userName;
    }
}