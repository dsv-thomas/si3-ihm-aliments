package com.dsv.td1.si3_ihm_aliments.factory;

import com.dsv.td1.si3_ihm_aliments.producer.Poissonnier;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Poisson;
import com.dsv.td1.si3_ihm_aliments.product.Product;

public class PoissionnierFactory implements MarketFactory{
    @Override
    public Producer buildProducer(String name, String place, String pNumber, boolean isBio) {
        return new Poissonnier(name, place, pNumber, isBio);
    }

    @Override
    public Product buildProduct() {
        return new Poisson();
    }
}
