package database;

import models.Electronics;
import models.Products;
import models.Reading;
import models.Shoes;
import models.enums.Grouping;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao extends DataBaseAccess {
    private Session session;


    public int setProduct(Products products) {
        session = builderSession().openSession();
        session.beginTransaction();
        Serializable save = session.save(products);
        int i = (int) save;
        session.getTransaction().commit();
        session.close();
        if (i != 0)
            return i;
        return -1;
    }

    public int getProduct(Products products) {
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Products where name=:n and price=:p");
        query.setParameter("n", products.getName());
        query.setParameter("n", products.getPrice());
        List list = query.list();
        int i = (int) list.get(0);
        session.getTransaction().commit();
        session.close();

        return i;
    }

    public Products getProductById(int id) {
        Products products = new Products();
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Products where idProduct=:id ");
        query.setParameter("id", id);
        products = (Products) query.list();
        session.getTransaction().commit();
        session.close();
        return products;
    }

    public List<Products> getListProducts() {
        List<Products> productsList = new ArrayList<>();
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Products ");
        List<Products> list = query.list();
        for (Products item : list) {
            productsList.add(item);
        }
        session.getTransaction().commit();
        session.close();
        return productsList;
    }

    public int UpdateProduct(int id, int stock) {
        session = builderSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Products set stock=:s where idProduct=:id");
        query.setParameter("s", stock);
        query.setParameter("id", id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        if (i != 0) {
            return i;
        }

        return -1;
    }

    public String getDetailProduct(String type, int id) {
        session = builderSession().openSession();
        session.beginTransaction();

        Query hql = null;
        boolean electronic = false, reading = false, shoes = false;
        if (type.equals(Grouping.ELECTRONIC.getTitle())) {
            electronic = true;

            hql = session.createQuery("from  Electronics e where  e.idProduct=:id");
            hql.setParameter("id", id);
        } else if (type.equals(Grouping.READING.getTitle())) {
            reading = true;
            hql = session.createQuery("from  Reading r where  r.idProduct=:id");
            hql.setParameter("id", id);
        } else if (type.equals(Grouping.SHOES.getTitle())) {
            shoes = true;

            hql = session.createQuery("from Shoes s where  s.idProduct=:id");
            hql.setParameter("id", id);

        }
        Object o = hql.uniqueResult();
        session.getTransaction().commit();
        session.close();
        if (o != null) {
            if (electronic) {
                Electronics electronics = (Electronics) o;
                return "Id : " + electronics.getIdProduct() + ", Name : " + electronics.getName()
                        + ", Price : " + electronics.getPrice() + ", Stock : " + electronics.getStock()
                        + ", Size : " + electronics.getSize() + ", Pow : " + electronics.getPow()
                        + ", Possibilities : " + electronics.getPossibilities();
            } else if (reading) {
                Reading reading1 = (Reading) o;
                return "Id : " + reading1.getIdProduct() + ", Name : " + reading1.getName()
                        + ", Price : " + reading1.getPrice() + ", Stock : " + reading1.getStock()
                        + ", Pages : " + reading1.getPages() + ", Size : " + reading1.getSize()
                        + ", Material : " + reading1.getMaterial();


            } else if (shoes) {
                Shoes shoes1 = (Shoes) o;
                return "Id : " + shoes1.getIdProduct() + ", Name : " + shoes1.getName()
                        + ", Price : " + shoes1.getPrice() + ", Stock : " + shoes1.getStock()
                        + ", Size : " + shoes1.getSize() + ", Color : " + shoes1.getColor();
            }

        }

        return null;

    }


    public boolean removeProduct(Products products) throws SQLException {
        session = builderSession().openSession();
        session.beginTransaction();
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
        session.getTransaction().commit();
        session.close();

        return false;
    }

}
