package database;

import models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class UserDao extends DataBaseAccess {
    private Session session;


    public UserDao() {
        super();
    }

    public int getUser(User user) {
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select id from User where name=:n and password=:p");
        query.setParameter("n", user.getName());
        query.setParameter("p", user.getPassword());
        List list = query.list();
        int i = (int) list.get(0);
        session.getTransaction().commit();
        session.close();
        return i;
    }

    public int setUser(User user) {
        session = builderSession().openSession();
        session.beginTransaction();
        Serializable save = session.save(user);
        int i = (int) save;
        session.getTransaction().commit();
        session.close();
        if (i != 0)
            return i;
        return -1;
    }
}
