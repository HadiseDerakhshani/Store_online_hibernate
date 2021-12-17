package database;

import models.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.Set;

public class AddressDao extends DataBaseAccess {
    Session session;
    Transaction transaction;

    public AddressDao() {
        super();
    }

    public int setAddress(Set<Address> addressSet) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Serializable save = session.save(addressSet);
        int i = (int) save;
        transaction.commit();
        session.close();
        if (i != 0)
            return i;
        return -1;

    }
}
