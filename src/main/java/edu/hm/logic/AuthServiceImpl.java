package edu.hm.logic;

import java.util.HashMap;
import java.util.Map;

import edu.hm.data.User;

public class AuthServiceImpl implements AuthService {
    private final TokenGenerator generator = new TokenGenerator();
    
    private final Map<String, User> users = new HashMap<>();

    private final Map<String, String> tokens = new HashMap<>();

    // USER-METHODEN
    @Override
    public AuthServiceResult addUser(final User user) {
        AuthServiceResult result;
        

        result = testUser(user);

        if (result == AuthServiceResult.OK) {
            users.put(user.getUserName(), user);
            result = AuthServiceResult.Created;
            result.setMessage("User created.");
        }

        return result;
    }
    
    @Override
    public AuthServiceResult loginUser(User user) {
        AuthServiceResult result = null;
        String token = null;
        result = compareCredentials(user);
        
        if(result == AuthServiceResult.OK){     //User hat sich korrekt eingeloggt, schicke ihm sein token
            token = generator.generateToken();
            
            tokens.put(user.getUserName(), token);
            result.setToken(token);
        }
        
        return result;
    }

    

    // USER-TEST-METHODEN

    /** Diese Methode testet ob ein User gültig ist.
     * 
     * @param user
     *        Der User der getestet werden soll.
     * @return AuthServiceResult mit den Informationen ob es funktioniert hat */
    private AuthServiceResult testUser(final User user) {
        AuthServiceResult result = null;

        result = testUserName(user);

        if (result == null) // if the username is okay test the password
            result = testPassword(user);

        if (result == null) { // all tests were ok
            result = AuthServiceResult.OK;
            result.setMessage("User created.");
        }

        return result;
    }

    private AuthServiceResult testUserName(final User user) {
        AuthServiceResult result = null;

        if(user.getUserName().length() == 0){
            result = AuthServiceResult.Bad_Request;
            result.setMessage("Username is too short.");
        }
        
        if (users.containsKey(user.getUserName())) { // User schon vorhanden
            result = AuthServiceResult.Conflict;
            result.setMessage("Username already in use.");
        }

        return result;
    }

    private AuthServiceResult testPassword(final User user) {
        AuthServiceResult result = null;
        String password = user.getPassword();

        if (password.length() < 8) {
            result = AuthServiceResult.Bad_Request;
            result.setMessage("Password is less than 8 characters.");
        }

        return result;
    }


    private AuthServiceResult compareCredentials(final User user){
        AuthServiceResult result = null;
        User userInDatabase;
        userInDatabase = users.get(user.getUserName());
        
        if(userInDatabase == null){
            result = AuthServiceResult.Not_Found;
            result.setMessage("Username was not found.");
        }
        else if(userInDatabase != null){
            if(!user.getPassword().equals(userInDatabase.getPassword())){
                result = AuthServiceResult.Bad_Request;
                result.setMessage("Password is incorrect.");
            }
        }
        
        if(result == null){
            result = AuthServiceResult.OK;
            result.setMessage("Successfully logged in.");
        }
        
        return result;
    }



    // ADMIN-METHODEN

    // @Override
    // public AuthServiceResult addAdmin(Admin admin) {
    // AuthServiceResult result = AuthServiceResult.OK;
    //
    // if(!checkAdmin(admin))
    // result = AuthServiceResult.Bad_Request;
    // else if(admins.containsKey(admin.getAlias()))
    // result = AuthServiceResult.Conflict;
    // else
    // admins.put(admin.getAlias(), admin);
    //
    // return result;
    // }
    //
    //
    // private boolean checkAdmin(final Admin admin){
    // boolean adminIsCorrect = false;
    //
    // if(admin.getAlias() != null )
    //
    // return adminIsCorrect;
    // }
}
