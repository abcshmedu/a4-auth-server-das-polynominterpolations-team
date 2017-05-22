package edu.hm.logic;

import java.util.HashMap;
import java.util.Map;

import edu.hm.data.Admin;

public class AuthServiceImpl implements AuthService {
    private final Map<String, Admin> admins = new HashMap<>();;
    
    

    @Override
    public AuthServiceResult addAdmin(Admin admin) {
        AuthServiceResult result = AuthServiceResult.OK;
        
        if(!checkAdmin(admin))
            result = AuthServiceResult.Bad_Request;
        else if(admins.containsKey(admin.getAlias()))
            result = AuthServiceResult.Conflict;
        else
            admins.put(admin.getAlias(), admin);
        
        return result;
    }
    
    
    private boolean checkAdmin(final Admin admin){
        boolean adminIsCorrect = false;
        
        if(admin.getAlias() != null )
        
        return adminIsCorrect;
    }
}
