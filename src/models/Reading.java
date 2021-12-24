package models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class Reading extends Products {
   // private int idProduct;
    private int pages;
    private String size;
    private String material;
    private String type;


    public Reading(String name, int price, int stock, String grouping, int pages, String size, String material, String type1) {
        super(name, price, stock, grouping);
        this.pages = pages;
        this.size = size;
        this.material = material;
        this.type = type1;
       // this.idProduct = idProduct;
    }


    @Override
    public String toString() {
        return "Electronics{" +
                ", Name='" + getName() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", stock='" + getStock() + '\'' +
                ", size='" + size + '\'' +
                ", pages='" + pages + '\'' +
                ", material='" + material + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
