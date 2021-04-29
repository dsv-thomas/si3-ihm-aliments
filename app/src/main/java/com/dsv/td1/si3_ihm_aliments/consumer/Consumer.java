package com.dsv.td1.si3_ihm_aliments.consumer;

import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Consumer {
    private String name;

    private List<Reservation> reservations = new ArrayList<>();


    public Consumer(String name) {
        this.name = name;
    }

    public Consumer(String name, List<Reservation> reservation) {
        this.name = name;
        this.reservations = reservation;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public String getName() {
        return name;
    }
}
