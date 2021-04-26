package com.dsv.td1.si3_ihm_aliments.model;

import android.util.Log;

import com.dsv.td1.si3_ihm_aliments.consumer.Consumer;
import com.dsv.td1.si3_ihm_aliments.consumer.Reservation;
import com.dsv.td1.si3_ihm_aliments.factory.MaraicheFactory;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Poisson;
import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model_Consumer extends Observable {

    private static List<Consumer> consumerList = new ArrayList<>();
    private static Model_Consumer instance = null;


    public static Model_Consumer getInstance() {
        if (instance == null)
            instance = new Model_Consumer();
        return instance;
    }

    private Model_Consumer() {
        super();
        consumerList.clear();

        add(new Consumer("Albert"));

    }

    public void add(Consumer consumer) {
        consumerList.add(consumer);
        setChanged();
        notifyObservers(consumer);
    }

    public void addProductForReservation(Consumer consumer, Reservation reservation) {
        consumer.addReservation(reservation);
        setChanged();
        notifyObservers(consumer);
    }


    public List<Consumer> getConsumerList() {
        return consumerList;
    }

    public void setConsumerList(List<Consumer> consumerList) {
        Model_Consumer.consumerList = consumerList;
    }
}
