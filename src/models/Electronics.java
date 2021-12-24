package models;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Electronics extends Products {

    //  private int idProduct;
    private String size;
    private String pow;
    private String possibilities;
    private String type;

    public Electronics(String name, int price, int stock, String grouping, String size, String pow, String possibilities, String type) {
        super(name, price, stock, grouping);
        this.size = size;
        this.pow = pow;
        this.possibilities = possibilities;
        this.type = type;
        //  this.idProduct = idProduct;
    }


    @Override
    public String toString() {
        return "Electronics{" +
                ", Name='" + getName() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", stock='" + getStock() + '\'' +
                ", size='" + size + '\'' +
                ", pow='" + pow + '\'' +
                ", possibilities='" + possibilities + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
