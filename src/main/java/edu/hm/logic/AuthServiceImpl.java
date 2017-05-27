package edu.hm.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.hm.data.LogicUser;
import edu.hm.data.User;

public class AuthServiceImpl implements AuthService {
    private final TokenGenerator generator = new TokenGenerator();

  //  private final List<String> userNameList = new ArrayList<>();
    
    private final Map<String, User> users = new HashMap<>();

    private final Map<User, String> tokens = new HashMap<>();

    // USER-METHODEN
    @Override
    public AuthServiceResult addUser(final LogicUser user) {
        AuthServiceResult result;
        String token = null;

        result = testUser(user);

        if (result == AuthServiceResult.OK) {
            token = generator.generateToken();
          //  userNameList.add(user.getUserName());
            users.put(user.getUserName(), user);
            tokens.put(user, token);
            result = AuthServiceResult.Created;
            result.setMessage("User created.");
            result.setToken(token);
        }

        return result;
    };
    
    @Override
    public AuthServiceResult loginUser(LogicUser user) {
        AuthServiceResult result = null;
        
        result = compareCredentials(user);
        
        return result;
    }

    

    // USER-TEST-METHODEN

    /** Diese Methode testet ob ein User gültig ist.
     * 
     * @param user
     *        Der User der getestet werden soll.
     * @return AuthServiceResult mit den Informationen ob es funktioniert hat */
    private AuthServiceResult testUser(final LogicUser user) {
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

    private AuthServiceResult testUserName(final LogicUser user) {
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

    private AuthServiceResult testPassword(final LogicUser user) {
        AuthServiceResult result = null;
        String password = user.getPassword();

        if (password.length() < 8) {
            result = AuthServiceResult.Bad_Request;
            result.setMessage("Password is less than 8 characters.");
        }

        return result;
    }


    private AuthServiceResult compareCredentials(final LogicUser user){
        AuthServiceResult result = null;
        
        
        
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
