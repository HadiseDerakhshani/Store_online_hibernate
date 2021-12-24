package database;

import models.Address;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Set;

public class AddressDao extends DataBaseAccess {
    private Session session;

    public int setAddress(Set<Address> addressSet) {
        session = builderSession().openSession();
        session.beginTransaction();
        Serializable save = session.save(addressSet);
        int i = (int) save;
        session.getTransaction().commit();
        session.close();
        if (i != 0)
            return i;
        return -1;

    }
}
