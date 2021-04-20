package com.dsv.td1.si3_ihm_aliments.factory;

import android.util.Log;

import com.dsv.td1.si3_ihm_aliments.producer.Maraiche;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;
import com.dsv.td1.si3_ihm_aliments.product.Vegetaux;

public class MaraicheFactory extends MarketFactory {

    @Override
    public Producer buildProducer(String name, String place, String pNumber, boolean isBio) {
        Log.d(MarketFactory.LOG,"Factory_red_only");
        return new Maraiche(name, place, pNumber, isBio);
    }

    @Override
    public Product buildProduct(String name, String quantity, String pricePerKg) {
        return null;
    }
}
