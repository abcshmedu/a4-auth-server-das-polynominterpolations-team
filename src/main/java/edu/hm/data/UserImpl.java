package edu.hm.data;

/** Dies ist die User-Klasse.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class UserImpl implements LogicUser {
    /** Alias-Name des Users. */
    private String userName;

    /** Passwort des Users. */
    private String password;

    public UserImpl(){
    }
    
    /** Custom-Ctor für einen neuen User.
     * 
     * @param userName
     *        Der gewünschte Alias-Name
     * @param password
     *        Das gewünschte Passwort */
    public UserImpl(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return userName;
    }
}
