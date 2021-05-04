package com.dsv.td1.si3_ihm_aliments.model;

import android.os.Bundle;

import com.dsv.td1.si3_ihm_aliments.consumer.Consumer;
import com.dsv.td1.si3_ihm_aliments.consumer.Reservation;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

public class Model_Consumer extends Observable {

    private static List<Consumer> consumerList = new ArrayList<>();
    private static Model_Consumer instance = null;


    private Model_Consumer() {
        super();
        consumerList.clear();

        add(new Consumer("Albert"));

    }

    public static Model_Consumer getInstance() {
        if (instance == null)
            instance = new Model_Consumer();
        return instance;
    }

    public void add(Consumer consumer) {
        consumerList.add(consumer);
        setChanged();
        notifyObservers(consumer);
    }

    public void addProductForReservation(Consumer consumer, Reservation reservation) {
        consumer.addReservation(reservation);
        setChanged();
        notifyObservers("reservation");
    }

    public void removeProductFromReservation(Consumer consumer, Reservation reservation) {
        consumer.getReservations().remove(reservation);
        setChanged();
        notifyObservers(consumer);
    }

    public void modifyProfile(Consumer consumer, Bundle bundle) {
        consumer.setName(bundle.get("name").toString());
        setChanged();
        notifyObservers("modifyprofile");
    }

    public List<Consumer> getConsumerList() {
        return consumerList;
    }

    public List<Reservation> reservationsForProducer(Producer producer) {
        List<Reservation> reservations = new ArrayList<>();
        for (Consumer consumer : consumerList) {
            reservations.addAll(consumer.getReservations().stream().filter(reservation -> reservation.getProducer().equals(producer)).collect(Collectors.toList()));
        }
        return reservations;
    }
}
