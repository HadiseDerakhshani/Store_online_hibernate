package models;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Shoes extends Products {

    // private int idProduct;
    private String size;
    private String color;
    private String type;

    public Shoes(String name, int price, int stock, String grouping, String size, String color, String type) {
        super(name, price, stock, grouping);
        this.size = size;
        this.color = color;
        this.type = type;
        // this.idProduct = idProduct;
    }


    @Override
    public String toString() {
        return "Electronics{" +
                ", Name='" + getName() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", stock='" + getStock() + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
