package com.dsv.td1.si3_ihm_aliments.consumer;

import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Consumer {
    private String name;

    private List<Product> reservation = new ArrayList<>();

    public Consumer(String name) {
        this.name = name;
    }

    public Consumer(String name, List<Product> reservation) {
        this.name = name;
        this.reservation = reservation;
    }

    public void addProduct(Product product) {
        reservation.add(product);
    }

    public List<Product> getReservation() {
        return reservation;
    }

    public String getName() {
        return name;
    }
}
