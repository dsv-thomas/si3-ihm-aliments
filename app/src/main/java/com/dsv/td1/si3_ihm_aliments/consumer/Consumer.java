package com.dsv.td1.si3_ihm_aliments.consumer;

import com.dsv.td1.si3_ihm_aliments.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Consumer extends User {

    private List<Reservation> reservations = new ArrayList<>();


    public Consumer(String name) {
        super(name);
    }

    public Consumer(String name, List<Reservation> reservation) {
        super(name);
        this.reservations = reservation;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

}
