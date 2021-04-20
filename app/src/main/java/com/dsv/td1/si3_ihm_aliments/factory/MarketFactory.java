package com.dsv.td1.si3_ihm_aliments.factory;

import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;

abstract class MarketFactory {

    public static final String LOG = "abstract_factory";

    public static final int MARAICHE = 1;
    public static final int POISSONIER = 2;

    public static final int POISSON = 101;
    public static final int VIANDE = 102;
    public static final int VEGETAUX = 103;

    public abstract Producer buildProducer(String name, String place, String pNumber, boolean isBio) throws Throwable;
    public abstract Product buildProduct(String name, String quantity, String pricePerKg);
}
