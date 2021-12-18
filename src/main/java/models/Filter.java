package models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Filter {
    private int id;
    private String category;
    private String brand;
    private int totalPrice;

    public Filter(){

    }

    public Filter(int id, String category, String brand, int totalPrice) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.totalPrice = totalPrice;
    }

    public String getCategory() {
        return category;
    }

    @XmlElement
    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    @XmlElement
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @XmlElement
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId(){
        return id;
    }

    @XmlElement
    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
