package com.dsv.td1.si3_ihm_aliments.producer;

import com.dsv.td1.si3_ihm_aliments.User;
import com.dsv.td1.si3_ihm_aliments.consumer.PickupPoint;
import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class Producer extends User {
    private String place;
    private String pNumber;
    private boolean isBio =false;
    private double averagePrice;
    private int iconNumber;
    private List<Product> proposedProducts = new ArrayList<>();
    private List<PickupPoint> pickupPoints = new ArrayList<>();


    public Producer(String name, String place, String pNumber, boolean isBio) {
        super(name);
        this.place = place;
        this.pNumber = pNumber;
        this.isBio = isBio;
        this.averagePrice = 0.0;
        updateIconNumber();
    }

    public Producer(String name, String place, String pNumber, boolean isBio, List<Product> proposedProducts) {
        super(name);
        this.place = place;
        this.pNumber = pNumber;
        this.isBio = isBio;
        this.proposedProducts = proposedProducts;
        this.averagePrice = 0.0;
        updateIconNumber();
    }

    public Producer(String name, String place, String pNumber, boolean isBio, List<Product> proposedProducts, List<PickupPoint> pickupPoints) {
        super(name);
        this.place = place;
        this.pNumber = pNumber;
        this.isBio = isBio;
        this.proposedProducts = proposedProducts;
        this.pickupPoints = pickupPoints;
        this.averagePrice = 0.0;
        updateIconNumber();
    }

    public List<Product> getProposedProducts() {
        return proposedProducts;
    }

    public void addProducts(Product product) {
        proposedProducts.add(product);
    }

    public void addPickUpPoint(PickupPoint pickupPoint) {
        pickupPoints.add(pickupPoint);
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

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double price) {
        this.averagePrice = price;
    }

    public int getIconNumber() {
        return this.iconNumber;
    }

    public List<PickupPoint> getPickupPoints() {
        return pickupPoints;
    }

    public void updateAveragePrice() {
        double newAveragePrice = 0.0;
        int i = 0;

        if(this.proposedProducts.isEmpty()) return;

        for(Product p : this.proposedProducts) {
            newAveragePrice += Double.parseDouble(p.getPricePerKg());
            i++;
        }
        newAveragePrice /= i;
        this.averagePrice = newAveragePrice;
        this.updateIconNumber();
    }

    public void updateIconNumber() {
        if(this.averagePrice <= 2.5) {
            this.iconNumber = 1;
        } else if(this.averagePrice <= 5.0) {
            this.iconNumber = 2;
        } else {
            this.iconNumber = 3;
        }
    }


}
