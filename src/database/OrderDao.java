package database;

import models.Orders;
import models.enums.BuyStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends DataBaseAccess {
    private Session session;

    public List<Orders> getListOrders(int id) {
        List<Orders> ordersList = new ArrayList<>();
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Orders where userId=:id and status=:s");
        query.setParameter("id", id);
        query.setParameter("s", "waiting");
        List<Orders> list = query.list();
        for (Orders item : list)
            ordersList.add(item);
        session.getTransaction().commit();
        session.close();
        return ordersList;
    }

    public int setOrder(Orders orders) {
        session = builderSession().openSession();
        session.beginTransaction();
        Serializable save = session.save(orders);
        int i = (int) save;
        session.getTransaction().commit();
        session.close();
        if (i != 0)
            return i;
        return -1;

    }

    public int getOrderById(int id) {

        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Orders where id=:id ");
        query.setParameter("id", id);
        List list = query.list();
        int i = (int) list.get(0);
        session.getTransaction().commit();
        session.close();
        return i;


    }

    public int deleteOrder(int id) {
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Orders  where id=:id");
        query.setParameter("id", id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        if (i != 0)
            return i;

        return -1;

    }

    public int UpdateOrders(int id) throws SQLException {
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Orders set status=:s where userId=:id");
        query.setParameter("s", BuyStatus.END.getTitle());
        query.setParameter("id", id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        if (i != 0)
            return i;

        return -1;
    }


}
