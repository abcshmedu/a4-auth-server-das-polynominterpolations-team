package edu.hm.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Dies ist eine Klasse um User-Informationen zu speichern.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class UserInformation {
    /** Eine Liste von Zugriffsrechten. */
    private List<ShareItAccess> accesses;

    /** Ctor für ein neues UserInformation-Object.
     * 
     * @param accessList
     *            Liste von Zugriffsrechten */
    public UserInformation(final ShareItAccess... accessList) {
	accesses = new ArrayList<>();
	for (ShareItAccess acc : accessList)
	    accesses.add(acc);
    }

    /** Getter für die Zugriffsrechte des Users.
     * 
     * @return Liste aller Zugriffsrechte */
    public ShareItAccess[] getAccess() {
	return accesses.toArray(new ShareItAccess[0]);
    }

    @Override
    public String toString() {
	String string = "";

	Iterator<ShareItAccess> it = accesses.iterator();

	while (it.hasNext()) {
	    string += it.next().toString() + "\n";
	}

	return string;
    }
}
