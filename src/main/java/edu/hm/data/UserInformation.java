package edu.hm.data;

public class UserInformation {
    private ShareItAccess access;
    
    public UserInformation(final ShareItAccess access){
        this.access = access;
    }
    
    public ShareItAccess getAccess() {
        return access;
    }
}
