package database;

import models.*;
import models.enums.Grouping;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao extends DataBaseAccess {
    Session session;
    Transaction transaction;
    Connection connection;

    public ProductsDao() throws SQLException, ClassNotFoundException {
        super();
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/maktab58", "root",
                "SAMAseven@7");

    }


    public int setProduct(Products products) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Serializable save = session.save(products);
        int i = (int) save;
        transaction.commit();
        session.close();
        if (i != 0)
            return i;
        return -1;
    }

    public int getProduct(Products products) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery(" from Products where name=:n and price=:p");
        query.setParameter("n", products.getName());
        query.setParameter("n", products.getPrice());
        List list = query.list();
        int i = (int) list.get(0);
        transaction.commit();
        session.close();

        return i;
    }

    public Products getProductById(int id) {
        Products products = new Products();
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery(" from Products where idProduct=:id ");
        query.setParameter("id", id);
        products = (Products) query.list();
        transaction.commit();
        session.close();
        return products;
    }

    public List<Products> getListProducts() throws SQLException {
        List<Products> productsList = new ArrayList<>();
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Products ");
        List<Products> list = query.list();
        for (Products item : list) {
            productsList.add(item);
        }
        transaction.commit();
        session.close();
        return productsList;
    }

    public int UpdateProduct(int id, int stock) throws SQLException {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Products set stock=:s where idProduct=:id");
        query.setParameter("s", stock);
        query.setParameter("id", id);
        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        if (i != 0) {
            return i;
        }

        return -1;
    }

    public String getDetailProduct(String type, int id) throws SQLException {
        session = getSessionFactory().openSession();
        session.beginTransaction();

            Query hql = null;
            boolean electronic = false, reading = false, shoes = false;
            if (type.equals(Grouping.ELECTRONIC.getTitle())) {
                electronic = true;
              //  hql = String.format("SELECT * FROM online_shop.product INNER JOIN online_shop.electronic WHERE product.id=electronic.idProduct AND electronic.idProduct=%d;", id);
                hql =session.createQuery("from  Electronics  where  idProduct=:id");
                hql.setParameter("id",id);
            } else if (type.equals(Grouping.READING.getTitle())) {
                reading = true;
             //   hql = String.format("SELECT * FROM online_shop.product INNER JOIN online_shop.reading WHERE product.id=reading.id_products AND reading.id_products=%d;", id);
                hql =session.createQuery("from  Reading  where  Product=:id");
            } else if (type.equals(Grouping.SHOES.getTitle())) {
                shoes = true;
              //  hql = String.format("SELECT * FROM online_shop.product INNER JOIN online_shop.shoes WHERE product.id=shoes.id_Product AND shoes.id_Product=%d;", id);
                hql =session.createQuery("from Shoes  where  Product=:id");

            }
        Object o = hql.uniqueResult();
        session.getTransaction().commit();
            session.close();
            if (o != null){
                if (electronic) {
                    Electronics electronics=(Electronics) o;
                    return "Id : " + electronics.getIdProduct()+ ", Name : " +electronics.getName()
                            + ", Price : " + electronics.getPrice() + ", Stock : " + electronics.getStock()
                            + ", Size : " + electronics.getSize() + ", Pow : " + electronics.getPow()
                            + ", Possibilities : " + electronics.getPossibilities();
                } else if (reading) {
                    Reading reading1=(Reading) o;
                    return "Id : " + reading1.getIdProduct() + ", Name : " + reading1.getName()
                            + ", Price : " + reading1.getPrice() + ", Stock : " + reading1.getStock()
                            + ", Pages : " + reading1.getPages() + ", Size : " + reading1.getSize()
                            + ", Material : " + reading1.getMaterial();


                } else if (shoes) {
                    Shoes shoes1 = (Shoes) o;
                    return "Id : " + shoes1.getIdProduct() + ", Name : " +shoes1.getName()
                            + ", Price : " + shoes1.getPrice()+ ", Stock : " + shoes1.getStock()
                            + ", Size : " + shoes1.getSize()+ ", Color : " + shoes1.getColor();
                }

            }

        return null;

    }


    public boolean removeProduct(Products products) throws SQLException {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        int i = 0;
        if (products.getGrouping().equals(Grouping.ELECTRONIC.getTitle())) {
            Query query = session.createQuery("delete from Electronics  where idProduct=:id");
            query.setParameter("id", products.getIdProduct());
            i = query.executeUpdate();

        } else if (products.getGrouping().equals(Grouping.SHOES.getTitle())) {
            Query query = session.createQuery("delete from Shoes  where idProduct=:id");
            query.setParameter("id", products.getIdProduct());
            i = query.executeUpdate();
        } else if (products.getGrouping().equals(Grouping.READING.getTitle())) {
            Query query = session.createQuery("delete from Reading where idProduct=:id");
            query.setParameter("id", products.getIdProduct());
            i = query.executeUpdate();

        }
        if (i != 0) {
            Query query = session.createQuery("delete from Products  where idProduct=:id");
            query.setParameter("id", products.getIdProduct());
            i = query.executeUpdate();
            if (i != 0) {
                return true;
            }

        }
        transaction.commit();
        session.close();

        return false;
    }

}
