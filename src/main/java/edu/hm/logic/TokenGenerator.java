package edu.hm.logic;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenGenerator {
    private SecureRandom random = new SecureRandom();
    
    
    public String generateToken(){
        return new BigInteger(130, random).toString(32);
    }
}
