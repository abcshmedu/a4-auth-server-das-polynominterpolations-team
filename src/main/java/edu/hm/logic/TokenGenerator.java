package edu.hm.logic;

import java.math.BigInteger;
import java.security.SecureRandom;

/** Der Token-Generator, welcher die Login-Tokens für die ShareIt-Anwendung
 * bereitstellt.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class TokenGenerator {
    /** Referenz auf ein SecureRandom-Object. */
    private SecureRandom random = new SecureRandom();

    /** Diese Methode generiert ein zufälliges Token.
     * 
     * @return Ein Token als String */
    public String generateToken() {
	return new BigInteger(130, random).toString(32);
    }
}
