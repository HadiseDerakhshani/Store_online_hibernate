package database;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DataBaseAccess {
    SessionFactory sessionFactory;


    public DataBaseAccess() {
        sessionFactory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
