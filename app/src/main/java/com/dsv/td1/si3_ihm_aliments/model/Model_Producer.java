package com.dsv.td1.si3_ihm_aliments.model;

import android.os.Bundle;
import android.util.Log;

import com.dsv.td1.si3_ihm_aliments.consumer.PickupPoint;
import com.dsv.td1.si3_ihm_aliments.factory.MaraicheFactory;
import com.dsv.td1.si3_ihm_aliments.producer.Maraiche;
import com.dsv.td1.si3_ihm_aliments.producer.Poissonnier;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Poisson;
import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class Model_Producer extends Observable {

    private static List<Producer> producerList = new ArrayList<>();
    private static Model_Producer instance = null;


    public static Model_Producer getInstance() {
        if (instance == null)
            instance = new Model_Producer();
        return instance;
    }

    private Model_Producer() {
        super();
        producerList.clear();
        MaraicheFactory maraicheFactory = new MaraicheFactory();

        addProducer(maraicheFactory.buildProducer("Michel", "Rue de l'eau", "0985758452", true));
        addProducer(maraicheFactory.buildProducer("Alex", "Chemin du palmier", "0954268522", false));
        Log.d("MODEL", "producerList=" +producerList.size());
        Log.d("MODEL", "producerList=" + maraicheFactory.buildProducer("Michel", "Rue de l'eau", "0985758452", true).toString());

        //producerList.get(0).getProposedProducts().add(new Poisson("Poisson4", "4", "4"));


    }

    public void addPickupPoint(Producer producer, PickupPoint pickupPoint) {
        producer.addPickUpPoint(pickupPoint);
        setChanged();
        notifyObservers(producer);
    }

    public void addProducer(Producer producer) {
        producerList.add(producer);
        setChanged();
        notifyObservers(producer);
    }

    public void addProduct(Producer producer, Product product) {
        producer.addProducts(product);
        setChanged();
        notifyObservers(producer);
    }

    public void modifyProfile(Producer producer, Bundle bundle) {
        producer.setName(bundle.get("name").toString());
        producer.setpNumber(bundle.get("number").toString());
        producer.setPlace(bundle.get("location").toString());
        setChanged();
        notifyObservers(producer);
        //producer.setBio(bundle.get("isBio").toString());
    }


    public List<Producer> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<Producer> producerList) {
        Model_Producer.producerList = producerList;
    }
}
