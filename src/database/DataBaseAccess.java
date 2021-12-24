package database;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DataBaseAccess {
    public static SessionFactory sessionFactory;

    public static SessionFactory builderSession() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure("config/hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }
}
