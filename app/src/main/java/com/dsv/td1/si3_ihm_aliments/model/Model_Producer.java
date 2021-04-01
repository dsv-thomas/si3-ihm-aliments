package com.dsv.td1.si3_ihm_aliments.model;

import java.util.ArrayList;
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
        producerList.add(new Producer("Michel", "Rue de l'eau", "0985758452", true));
        producerList.add(new Producer("Alex", "Chemin du palmier", "0954268522", false));
    }

    public void add(Producer producer) {
        producerList.add(producer);
        setChanged();
        notifyObservers(producer);
    }


    public List<Producer> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<Producer> producerList) {
        Model_Producer.producerList = producerList;
    }
}
