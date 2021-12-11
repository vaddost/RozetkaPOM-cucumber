package utils;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Filter {
    private String category;
    private String brand;
    private int totalPrice;

    public Filter(){

    }

    public Filter(String category, String brand, int totalPrice) {
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
}
