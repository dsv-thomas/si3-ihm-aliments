package com.dsv.td1.si3_ihm_aliments.consumer;

import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.util.Date;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private Consumer consumer;
    private Product product;
    private int quantity;
    private Date date;
    private String pickupPoint;

    public Reservation(Consumer consumer, Product product, int quantity, String pickupPoint) {
        this.id = UUID.randomUUID();
        this.consumer = consumer;
        this.product = product;
        this.quantity = quantity;
        this.date = new Date();
        this.pickupPoint = pickupPoint;
    }


    public UUID getId() {
        return id;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }
}
