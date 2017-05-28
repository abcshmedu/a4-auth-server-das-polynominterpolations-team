package edu.hm.logic;

import edu.hm.data.User;

public interface AuthService {
    //AuthServiceResult addAdmin(Admin admin);
    
    AuthServiceResult addUser(User user);
    
    AuthServiceResult loginUser(User user);
}
