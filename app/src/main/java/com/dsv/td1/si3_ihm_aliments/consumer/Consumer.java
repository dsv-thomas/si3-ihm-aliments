package com.dsv.td1.si3_ihm_aliments.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Consumer {
    private String name;
    private UUID uuid;

    private List<Reservation> reservations = new ArrayList<>();


    public Consumer(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public Consumer(String name, List<Reservation> reservation) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.reservations = reservation;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
