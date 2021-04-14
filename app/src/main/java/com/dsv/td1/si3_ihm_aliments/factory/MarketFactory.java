package com.dsv.td1.si3_ihm_aliments.factory;

import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;

interface MarketFactory {
    Producer buildProducer(String name, String place, String pNumber, boolean isBio);
    Product buildProduct();
}
