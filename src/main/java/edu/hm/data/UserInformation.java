package edu.hm.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserInformation {
    private List<ShareItAccess> accesses;
    
    public UserInformation(final ShareItAccess... information){
        accesses = new ArrayList<>();
        for(ShareItAccess acc : information)
            accesses.add(acc);
    }
    
    public ShareItAccess[] getAccess() {
        return accesses.toArray(new ShareItAccess[0]);
    }
    
    @Override
    public String toString() {
        String string = "";
        
        Iterator<ShareItAccess> it = accesses.iterator();
        
        while(it.hasNext()){
            string += it.next().toString() + "\n";
        }
        
        return string;
    }
}
