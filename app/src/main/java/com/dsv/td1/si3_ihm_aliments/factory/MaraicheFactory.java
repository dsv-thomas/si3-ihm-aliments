package com.dsv.td1.si3_ihm_aliments.factory;

import com.dsv.td1.si3_ihm_aliments.producer.Maraiche;
import com.dsv.td1.si3_ihm_aliments.product.Product;
import com.dsv.td1.si3_ihm_aliments.product.Vegetaux;

public class MaraicheFactory implements MarketFactory {

    @Override
    public Maraiche buildProducer(String name, String place, String pNumber, boolean isBio) {
        return new Maraiche(name, place, pNumber, isBio);
    }

    @Override
    public Product buildProduct() {
        return new Vegetaux();
    }
}
