package edu.hm.logic;

import edu.hm.data.LogicUser;

public interface AuthService {
    //AuthServiceResult addAdmin(Admin admin);
    
    AuthServiceResult addUser(LogicUser user);
    
    AuthServiceResult loginUser(LogicUser user);
}
