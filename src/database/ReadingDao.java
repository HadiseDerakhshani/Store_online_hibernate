package database;

import models.Reading;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class ReadingDao extends DataBaseAccess {
    private Session session;

    public int setReading(Reading reading) {
        session = builderSession().openSession();
        session.beginTransaction();
        Serializable save = session.save(reading);
        int i = (int) save;
        session.getTransaction().commit();
        session.close();
        if (i != 0)
            return i;
        return -1;
    }

    public int getReading(Reading reading) {
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Reading where idProduct=:id");
        query.setParameter("id", reading.getIdProduct());
        List list = query.list();
        int i = (int) list.get(0);
        session.getTransaction().commit();
        session.close();
        return i;
    }
}
