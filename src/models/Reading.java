package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reading extends Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idProduct;
    private int pages;
    private String size;
    private String material;
    private String type;


    public Reading(String name, int price, int stock, String grouping, int pages, String size, String material, String type1, int idProduct) {
        super(name, price, stock, grouping);
        this.pages = pages;
        this.size = size;
        this.material = material;
        this.type = type1;
        this.idProduct = idProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.id = idProduct;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Electronics{" +
                ", Name='" + getName() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", stock='" + getStock() + '\'' +
                ", idProduct=" + idProduct +
                ", size='" + size + '\'' +
                ", pages='" + pages + '\'' +
                ", material='" + material + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
