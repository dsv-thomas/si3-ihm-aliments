package com.dsv.td1.si3_ihm_aliments.model;

import android.util.Log;

import com.dsv.td1.si3_ihm_aliments.factory.MaraicheFactory;
import com.dsv.td1.si3_ihm_aliments.producer.Maraiche;
import com.dsv.td1.si3_ihm_aliments.producer.Poissonnier;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;

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
        MaraicheFactory maraicheFactory = new MaraicheFactory();

        add(maraicheFactory.buildProducer("Michel", "Rue de l'eau", "0985758452", true));
        add(maraicheFactory.buildProducer("Alex", "Chemin du palmier", "0954268522", false));
        Log.d("MODEL", "producerList=" +producerList.size());
        Log.d("MODEL", "producerList=" + maraicheFactory.buildProducer("Michel", "Rue de l'eau", "0985758452", true).toString());
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
