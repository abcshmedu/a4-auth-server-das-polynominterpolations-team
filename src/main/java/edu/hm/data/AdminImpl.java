package edu.hm.data;

/** Dies ist die Admin-Klasse.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class AdminImpl {
    /** Name des Admins. */
    private String name;

    /** Alias-Name des Admins. */
    private String adminName;

    /** Passwort des Admins. */
    private String password;

    /** Custom-Ctor für einen neuen Admin.
     * 
     * @param adminName
     *        Der gewünschte Alias-Name
     * @param password
     *        Das gewünschte Passwort */
    public AdminImpl(final String adminName, final String password) {
        this(adminName, password, "");
    }

    /** Custom-Ctor für einen neuen Admin.
     * 
     * @param adminName
     *        Der gewünschte Alias-Name
     * @param password
     *        Das gewünschte Passwort #
     * @param name
     *        Der echte Name des Admins */
    public AdminImpl(final String adminName, final String password, final String name) {
        this.adminName = adminName;
        this.password = password;
        this.name = name;
    }
}