package edu.hm.data;

/** Dies ist das Interface für die User und Admins.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public interface ShareItParticipant {
    /** Diese Methode checkt ob das Passwort mit dem gespeichertem Passwort
     * übereinstimmt.
     * 
     * @param password
     * @return */
    boolean checkPassword(String password);

    /** Diese Methode gibt den Alias-Name zurück.
     * 
     * @return Der Alias-Name */
    String getAlias();
}