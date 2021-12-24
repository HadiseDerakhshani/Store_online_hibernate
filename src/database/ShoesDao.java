package database;

import models.Shoes;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class ShoesDao extends DataBaseAccess {
    private Session session;


    public int setShoes(Shoes shoes) {
        session = builderSession().openSession();
        session.beginTransaction();
        Serializable save = session.save(shoes);
        int i = (int) save;
        session.getTransaction().commit();
        session.close();
        if (i != 0)
            return i;
        return -1;

    }

    public int getShoes(Shoes shoes) {
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Shoes where idProduct=:id");
        query.setParameter("id", shoes.getIdProduct());
        List list = query.list();
        int i = (int) list.get(0);
        session.getTransaction().commit();
        session.close();
        return i;
    }
}
