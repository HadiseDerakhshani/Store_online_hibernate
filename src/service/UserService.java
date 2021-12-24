package service;

import exceptions.InvalidInputExp;
import models.Orders;
import models.Products;
import models.enums.BuyStatus;
import models.enums.Grouping;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    public int idUser;
    boolean exit = false;
    Scanner scanner = new Scanner(System.in);

    public UserService(int idUser) {
        this.idUser = idUser;
    }

    public void showMenu() {
        while (!exit) {
            System.out.println("1.Show list products\n2.show buy basket\n3.Exit");
            try {
                int selectMenu = scanner.nextInt();
                switch (selectMenu) {
                    case 1:
                        showListProducts();
                        break;
                    case 2:
                        showBuyBasket();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        throw new InvalidInputExp("==> enter 1 - 3 ! ");
                }

            } catch (InvalidInputExp | NumberFormatException | SQLException | InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();

            }
        }
    }

    public void showListProducts() throws SQLException {
        boolean exit = false;
        System.out.println("---------------- Products ---------------");
        for (Products listProduct : Shop.productsDao.getListProducts()) {
            System.out.println(listProduct.toString());
        }
        System.out.println("------------------------------------");
        while (!exit) {
            System.out.println("1.add to buy basket\n2.Show by grouping\n3.Show product's detail\n4.exit");
            int selectNum = scanner.nextInt();
            switch (selectNum) {
                case 1:
                    addToBuyBasket();
                    break;
                case 2:
                    showByGrouping();
                    break;
                case 3:
                    showProductsDetail();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    throw new InvalidInputExp("enter 1 - 3 for add to but basket or exit");
            }
        }
    }

    public void showBuyBasket() throws SQLException {
        boolean exit = false;
        long totalPrice = 0;
        while (!exit) {
            List<Orders> ordersList = Shop.orderDao.getListOrders(idUser);
            if (ordersList.isEmpty()) {
                System.out.println("-------------------------");
                System.out.println("your Buy Basket is empty . ");
                System.out.println("-------------------------");

                exit = true;
            } else {
                System.out.println("number of orders : " + ordersList.size());
                System.out.println("---------------------------");
                for (Orders orders : ordersList) {
                    Products products = Shop.productsDao.getProductById(orders.getProductId());
                    totalPrice += products.getPrice();
                    System.out.println("id order : " + orders.getId() + " " + products);
                }
                System.out.println("----------------------------");
                System.out.println("Total Price : " + totalPrice);
                System.out.println("----------------------------");

                System.out.println("1.Delete item from buy basket\n2.Confirmation buys\n3.Exit");
                int selectNum = scanner.nextInt();
                switch (selectNum) {
                    case 1:
                        deleteFromBuyBasket();
                        break;
                    case 2:
                        confirmBuyBasket(ordersList);
                        exit = true;
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        throw new InvalidInputExp("enter 1 - 3 !");

                }
            }
        }
    }

    public void addToBuyBasket() {
        try {
            List<Orders> orders = Shop.orderDao.getListOrders(idUser);
            if (orders.size() < 5) {
                System.out.println("enter id of product : ");
                int idProduct = scanner.nextInt();
                Products products = Shop.productsDao.getProductById(idProduct);
                if (products != null && products.getStock() > 0) {
                    Orders order = new Orders(idUser, idProduct, BuyStatus.WAITING.getTitle());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    order.setDate(now);
                    if (Shop.orderDao.setOrder(order) != -1) {
                        System.out.println("add to BuyBasket was successfully ");
                    } else {
                        System.out.println("Sorry Stock of this product is zero ! ");

                    }


                } else {
                    System.out.println("this product not found");
                }
            } else {
                System.out.println("your Buy Basket is full ! ");
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();

        }
    }

    public void deleteFromBuyBasket() {
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("1.Delete item\n2.Exit");
                int selectNum = scanner.nextInt();
                switch (selectNum) {
                    case 1:
                        System.out.println("Enter id of order");
                        int idProduct = scanner.nextInt();
                        if (Shop.orderDao.getOrderById(idProduct) != -1) {
                            if (Shop.orderDao.deleteOrder(idProduct) != -1) {
                                System.out.println("Delete  item was successfully");
                            }
                        } else {
                            System.out.println("Order by this id not found ! ");
                        }
                        break;
                    case 2:
                        exit = true;
                        break;
                    default:
                        throw new InvalidInputExp("enter 1 or 2 !");
                }


            } catch (NumberFormatException | InputMismatchException | SQLException | InvalidInputExp e) {
                System.out.println(e.getMessage());
                scanner.nextLine();

            }
        }

    }

    public void confirmBuyBasket(List<Orders> ordersList) throws SQLException {

        if (Shop.orderDao.UpdateOrders(idUser) != -1) {
            System.out.println("Confirmation was successfully");
            for (Orders orders : ordersList) {
                Products products = Shop.productsDao.getProductById(orders.getProductId());
                Shop.productsDao.UpdateProduct(orders.getProductId(), products.getStock() - 1);
            }
            System.out.println("Update stock of products");
            System.out.println("---------------------------------");

        } else {
            System.out.println("Confirmation was failed ! ");
        }

    }

    public void showByGrouping() throws SQLException {
        System.out.println("Grouping : \n1.Electronics\n2.Shoes\n3.Reading\n4.exit");
        String type = "";
        try {
            int selectNum = scanner.nextInt();
            switch (selectNum) {
                case 1:
                    type = Grouping.ELECTRONIC.getTitle();
                    break;
                case 2:
                    type = Grouping.SHOES.getTitle();
                    break;
                case 3:
                    type = Grouping.READING.getTitle();
                    break;
                case 4:
                default:
                    throw new InvalidInputExp("enter 1 - 4 !");

            }
        } catch (InvalidInputExp | NumberFormatException | InputMismatchException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();

        }
        System.out.println("---------------- Products ---------------");
        for (Products listProduct : Shop.productsDao.getListProducts()) {
            if (listProduct.getGrouping().equals(type)) {
                System.out.println(listProduct);
            }
        }
        System.out.println("------------------------------------");

    }

    public void showProductsDetail() throws SQLException {
        boolean exit = false;
        System.out.println("enter id of product : ");
        try {
            int idProduct = scanner.nextInt();
            Products products = Shop.productsDao.getProductById(idProduct);
            if (products != null) {
                String detail = Shop.productsDao.getDetailProduct(products.getGrouping(), idProduct);
                System.out.println("---------------  Detail  ---------------");
                System.out.println(detail);
                System.out.println("----------------------------------------");

            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();

        }


    }
}
