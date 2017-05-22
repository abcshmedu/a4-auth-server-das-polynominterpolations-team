package edu.hm.data;

/** Dies ist die User-Klasse.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class User implements ShareItParticipant {
    /** Name des Users. */
    private String name;

    /** Alias-Name des Users. */
    private String userName;

    /** Passwort des Users. */
    private String password;

    /** Custom-Ctor für einen neuen Users.
     * 
     * @param userName
     *        Der gewünschte Alias-Name
     * @param password
     *        Das gewünschte Passwort */
    public User(final String userName, final String password) {
        this(userName, password, "");
    }

    /** Custom-Ctor für einen neuen User.
     * 
     * @param userName
     *        Der gewünschte Alias-Name
     * @param password
     *        Das gewünschte Passwort
     * @param name
     *        Der echte Name des Users */
    public User(final String userName, final String password, final String name) {
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    @Override
    public boolean checkPassword(final String passwordToTest) {
        boolean passwordIsOkay = false;
        
        if(passwordToTest.equals(password))
            passwordIsOkay = true;
        
        return passwordIsOkay;
    }
}
