package database;

import models.Electronics;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class ElectronicDao extends DataBaseAccess {
    Session session;
    Transaction transaction;

    public ElectronicDao() {
        super();
    }

    public int setElectronic(Electronics electronics) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Serializable save = session.save(electronics);
        int i = (int) save;
        transaction.commit();
        session.close();
        if (i != 0)
            return i;
        return -1;

    }

    public int getElectronic(Electronics electronics) throws SQLException {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery(" from Electronics where idProduct=:id");
        query.setParameter("id", electronics.getIdProduct());
        List list = query.list();
        int i = (int) list.get(0);
        transaction.commit();
        session.close();
        return i;
    }
}
