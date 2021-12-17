package service;

import database.*;
import models.Orders;
import models.Products;
import models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    public static UserDao userDao;
    public static AddressDao addressDao;
    public static ProductsDao productsDao;
    public static ElectronicDao electronicDao;
    public static ShoesDao shoesDao;
    public static ReadingDao readingDao;
    public static OrderDao orderDao;
    public List<Products> products = new ArrayList<>();
    public List<User> users = new ArrayList<>();
    public List<Orders> orders = new ArrayList<>();


    public Shop() throws SQLException, ClassNotFoundException {
        userDao = new UserDao();
        addressDao = new AddressDao();
        productsDao = new ProductsDao();
        electronicDao = new ElectronicDao();
        readingDao = new ReadingDao();
        shoesDao = new ShoesDao();
        orderDao = new OrderDao();
    }
}
