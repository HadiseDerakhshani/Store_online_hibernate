package database;

import models.BuyStatus;
import models.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends DataBaseAccess {
    private Session session;
    private Transaction transaction;

    public OrderDao() {
        super();
    }

    public List<Orders> getListOrders(int id) {
        List<Orders> ordersList = new ArrayList<>();
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery(" from Orders where userId=:id and status=:s");
        query.setParameter("id", id);
        query.setParameter("s", "waiting");
        List<Orders> list = query.list();
        for (Orders item : list)
            ordersList.add(item);
        transaction.commit();
        session.close();
        return ordersList;
    }

    public int setOrder(Orders orders) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Serializable save = session.save(orders);
        int i = (int) save;
        transaction.commit();
        session.close();
        if (i != 0)
            return i;
        return -1;

    }

    public int getOrderById(int id) {

        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery(" from Orders where id=:id ");
        query.setParameter("id", id);
        List list = query.list();
        int i = (int) list.get(0);
        transaction.commit();
        session.close();
        return i;


    }

    public int deleteOrder(int id) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Orders  where id=:id");
        query.setParameter("id", id);
        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        if (i != 0)
            return i;

        return -1;

    }

    public int UpdateOrders(int id) throws SQLException {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Orders set status=:s where userId=:id");
        query.setParameter("s", BuyStatus.END.getTitle());
        query.setParameter("id", id);
        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        if (i != 0)
            return i;

        return -1;
    }


}
