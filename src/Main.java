import exceptions.InvalidEmailExp;
import exceptions.InvalidInputExp;
import exceptions.InvalidNameExp;
import models.Address;
import models.User;
import service.AdminService;
import service.CheckInputValidation;
import service.Shop;
import service.UserService;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean exit = false;
    static Shop shop;

    static {
        try {
            shop = new Shop();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Main() throws SQLException, ClassNotFoundException {
    }

    public static void main(String[] args) {
        System.out.println("---------- Welcome to Online Shop ----------");
        while (!exit) {
            System.out.println("1.Register\n2.Log in\n3.Exit");
            try {
                int selectMenu = scanner.nextInt();
                switch (selectMenu) {
                    case 1:
                        getInputForRegister();
                        break;
                    case 2:
                        getInputForLogIn();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        throw new InvalidInputExp("==> enter 1 - 3 ");
                }


            } catch (NumberFormatException | InputMismatchException | InvalidInputExp e) {
                System.out.println(e.getMessage());
                scanner.nextLine();

            }
        }
    }

    public static void getInputForRegister() {
        try {
            System.out.println("name :");
            String name = scanner.next();
            System.out.println("email :");
            String email = scanner.next();
            System.out.println("password:");
            String password = scanner.next();
            CheckInputValidation.checkName(name);
            CheckInputValidation.checkEmail(email);
            User user = new User(name, password, email);
            if (Shop.userDao.getUser(user) != -1) {
                System.out.println("** this user was exist ");
            } else {
                int id = Shop.userDao.setUser(user);
                if (id != -1) {
                    user.setId(id);
                }
                System.out.println("enter number of address : ");
                int number = scanner.nextInt();
                Set<Address> addresses = new HashSet<>();
                for (int i = 0; i < number; i++) {
                    System.out.println("city :");
                    String city = scanner.next();
                    System.out.println("street :");
                    String street = scanner.next();
                    System.out.println("postalCode :");
                    String postalCode = scanner.next();
                    System.out.println("tag :");
                    String tag = scanner.next();
                    CheckInputValidation.checkName(city);
                    addresses.add(new Address(city, postalCode, street, tag, user.getId()));
                }
                if (!addresses.isEmpty()) {
                    if (Shop.addressDao.setAddress(addresses) != -1) {
                        System.out.println("register was successfully .  ");
                    }
                }
            }

        } catch (NumberFormatException | InputMismatchException | InvalidEmailExp | InvalidNameExp | SQLException | InvalidInputExp e) {
            System.out.println(e.getMessage());
            scanner.nextLine();

        }

    }

    public static void getInputForLogIn() {
        try {
            System.out.println("name :");
            String name = scanner.next();
            System.out.println("password:");
            String password = scanner.next();
            User user = new User(name, password);
            if (name.equals("admin") && password.equals("admin")) {
                AdminService adminService = new AdminService();
                adminService.showMenu();
            } else {
                int id = Shop.userDao.getUser(user);
                if (id != -1) {
                    UserService userService = new UserService(id);
                    userService.showMenu();
                } else {
                    System.out.println("this user not exist ! ");
                }

            }


        } catch (InvalidNameExp | SQLException | InputMismatchException | InvalidInputExp | NumberFormatException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();

        }
    }
}
