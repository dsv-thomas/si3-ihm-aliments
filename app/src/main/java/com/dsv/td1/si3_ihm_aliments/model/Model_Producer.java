package com.dsv.td1.si3_ihm_aliments.model;

import android.os.Bundle;

import com.dsv.td1.si3_ihm_aliments.consumer.PickupPoint;
import com.dsv.td1.si3_ihm_aliments.factory.MaraicheFactory;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model_Producer extends Observable {

    private static List<Producer> producerList = new ArrayList<>();
    private static Model_Producer instance = null;


    private Model_Producer() {
        super();
        producerList.clear();
        MaraicheFactory maraicheFactory = new MaraicheFactory();

        addProducer(maraicheFactory.buildProducer("Michel", "Rue de l'eau", "0985758452", true));
        addProducer(maraicheFactory.buildProducer("Alex", "Chemin du palmier", "0954268522", false));
    }

    public static Model_Producer getInstance() {
        if (instance == null)
            instance = new Model_Producer();
        return instance;
    }

    public void addPickupPoint(Producer producer, PickupPoint pickupPoint) {
        producer.addPickUpPoint(pickupPoint);
        setChanged();
        notifyObservers("addpickupoint");
    }

    public void addProducer(Producer producer) {
        producerList.add(producer);
        setChanged();
        notifyObservers(producer);
    }

    public void addProduct(Producer producer, Product product) {
        producer.addProducts(product);
        setChanged();
        notifyObservers("addproduct");
    }

    public void modifyProfile(Producer producer, Bundle bundle) {
        producer.setName(bundle.get("name").toString());
        producer.setpNumber(bundle.get("number").toString());
        producer.setPlace(bundle.get("location").toString());
        setChanged();
        notifyObservers("profile");
        //producer.setBio(bundle.get("isBio").toString());
    }

    public List<Producer> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<Producer> producerList) {
        Model_Producer.producerList = producerList;
    }

    public List<PickupPoint> getPickupPoints() {
        List<PickupPoint> pickupPoints = new ArrayList<>();
        for(Producer producer: producerList) {
            pickupPoints.addAll(producer.getPickupPoints());
        }
        return pickupPoints;
    }
}
