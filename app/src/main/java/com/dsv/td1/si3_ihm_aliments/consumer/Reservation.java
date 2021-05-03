package com.dsv.td1.si3_ihm_aliments.consumer;

import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.util.Date;
import java.util.UUID;

public class Reservation {
    private String id;
    private Consumer consumer;
    private Producer producer;
    private Product product;
    private int quantity;
    private Date date;
    private PickupPoint pickupPoint;

    public Reservation(Consumer consumer, Producer producer, Product product, int quantity, PickupPoint pickupPoint) {
        this.id = UUID.randomUUID().toString().substring(5);
        this.consumer = consumer;
        this.producer = producer;
        this.product = product;
        this.quantity = quantity;
        this.date = new Date();
        this.pickupPoint = pickupPoint;
    }


    public String getId() {
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

    public PickupPoint getPickupPoint() {
        return pickupPoint;
    }

    public Producer getProducer() {
        return producer;
    }
}
