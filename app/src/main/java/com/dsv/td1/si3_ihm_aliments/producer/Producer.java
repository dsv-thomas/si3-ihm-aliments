package com.dsv.td1.si3_ihm_aliments.producer;

import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class Producer {
    private String name;
    private String place;
    private String pNumber;
    private boolean isBio;
    private List<Product> proposedProducts = new ArrayList<>();


    public Producer(String name, String place, String pNumber, boolean isBio) {
        this.name = name;
        this.place = place;
        this.pNumber = pNumber;
        this.isBio = isBio;
    }

    public Producer(String name, String place, String pNumber, boolean isBio, List<Product> proposedProducts) {
        this.name = name;
        this.place = place;
        this.pNumber = pNumber;
        this.isBio = isBio;
        this.proposedProducts = proposedProducts;
    }

    public List<Product> getProposedProducts() {
        return proposedProducts;
    }

    public void addProducts(Product product) {
        proposedProducts.add(product);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isBio() {
        return isBio;
    }

    public void setBio(boolean bio) {
        isBio = bio;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }
}
