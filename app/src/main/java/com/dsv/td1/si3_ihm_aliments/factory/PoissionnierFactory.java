package com.dsv.td1.si3_ihm_aliments.factory;

import android.util.Log;

import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.dsv.td1.si3_ihm_aliments.producer.Poissonnier;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Poisson;
import com.dsv.td1.si3_ihm_aliments.product.Product;

public class PoissionnierFactory extends MarketFactory{

    Poissonnier poissonnier;
    Poisson poisson;

    public PoissionnierFactory(Poissonnier poissonnier, Poisson poisson) {
        this.poissonnier = poissonnier;
        this.poisson = poisson;
    }

    @Override
    public Producer buildProducer(String name, String place, String pNumber, boolean isBio) throws Throwable {
        Log.d(MarketFactory.LOG,"Factory_red_only");

        return new Poissonnier(name, place, pNumber, isBio);

    }

    @Override
    public Product buildProduct(String name, String quantity, String pricePerKg) {
        return new Poisson(name,quantity, pricePerKg);
    }
}
