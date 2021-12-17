package models;

import javax.persistence.*;

@Entity
@MappedSuperclass
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    private String name;
    private int price;
    private int stock;
    private String grouping;

    public Products() {
    }

    public Products(String name, int price, int stock, String grouping) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.grouping = grouping;

    }

    public Products(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }


    @Override
    public String toString() {
        return "Products{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", grouping='" + grouping + '\'' +
                '}';
    }
}
