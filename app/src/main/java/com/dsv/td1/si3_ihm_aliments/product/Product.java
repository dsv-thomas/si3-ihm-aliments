package com.dsv.td1.si3_ihm_aliments.product;

public abstract class Product {

    private String name;
    private String quantity;
    private String pricePerKg;
    private String imageName;

    public Product(String name, String quantity, String pricePerKg, String imageName) {
        this.name = name;
        this.quantity = quantity;
        this.pricePerKg = pricePerKg;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(String pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
