package database;

import models.Electronics;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class ElectronicDao extends DataBaseAccess {
    private Session session;


    public int setElectronic(Electronics electronics) {
        session = builderSession().openSession();
        session.beginTransaction();
        Serializable save = session.save(electronics);
        int i = (int) save;
        session.getTransaction().commit();
        session.close();
        if (i != 0)
            return i;
        return -1;

    }

    public int getElectronic(Electronics electronics) throws SQLException {
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Electronics where idProduct=:id");
        query.setParameter("id", electronics.getIdProduct());
        List list = query.list();
        int i = (int) list.get(0);
        session.getTransaction().commit();
        session.close();
        return i;
    }
}
