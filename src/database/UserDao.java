package database;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends DataBaseAccess {
    Session session;
    Transaction transaction;

    public UserDao() {
        super();
    }

    public int getUser(User user) throws SQLException {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("select id from User where name=:n and password=:p");
        query.setParameter("n", user.getName());
        query.setParameter("p", user.getPassword());
        List list = query.list();
        int i = (int) list.get(0);
        transaction.commit();
        session.close();
        return i;
    }

    public int setUser(User user) throws SQLException {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Serializable save = session.save(user);
        int i = (int) save;
        transaction.commit();
        session.close();
        if (i != 0)
            return i;
        return -1;
    }
}
