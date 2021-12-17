package database;

import models.Reading;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class ReadingDao extends DataBaseAccess {
    Session session;
    Transaction transaction;

    public ReadingDao() {
        super();
    }

    public int setReading(Reading reading) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Serializable save = session.save(reading);
        int i = (int) save;
        transaction.commit();
        session.close();
        if (i != 0)
            return i;
        return -1;
    }

    public int getReading(Reading reading) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery(" from Reading where idProduct=:id");
        query.setParameter("id", reading.getIdProduct());
        List list = query.list();
        int i = (int) list.get(0);
        transaction.commit();
        session.close();
        return i;
    }
}
